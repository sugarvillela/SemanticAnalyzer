package rxfxcore.rximpl_s;

import commons.Commons;
import rxfxcore.interfaces.*;
import recursivelist.FlagNode;

import java.util.ArrayList;

public class RxEngineS implements RxEngine {
    private final ArrayList<RxResult> allResults;
    private final SplitUtil splitUtil;
    private final MergeUtil mergeUtil;
    private final TargetUtil targetUtil;
    private FlagNode[] flagNodes;
    private RxPattern rxPattern;

    private RxResult result;

    public RxEngineS() {
        allResults = new ArrayList<>();
        splitUtil = new SplitUtil();
        mergeUtil = new MergeUtil();
        targetUtil = new TargetUtil();
    }

    @Override
    public RxEngine setNodes(FlagNode[] flagNodes) {
        this.flagNodes = flagNodes;
        return this;
    }

    @Override
    public RxEngine setRxPattern(RxPattern rxPattern) {
        this.rxPattern = rxPattern;
        return this;
    }

    @Override
    public RxEngine setIndex(int index) {//this is the case number, not the sequence number
        return this;
    }

    @Override
    public boolean go() {
        rxPattern.setNodes(flagNodes);
        for(int j = 0; j < flagNodes.length; j++){
            if(rxPattern.setIndex(j).go()){
                rxPattern.disp();
                allResults.add(rxPattern.getResult());
            }
        }
        if(allResults.isEmpty()){
            result = null;
            return false;
        }
        else{
            /* Optimize overlapping matches, split and merge non-overlapping */
            splitUtil.setGroups(allResults);
            int numGroups = splitUtil.getNumGroups();

            result = new RxResultMergedS();
            mergeUtil.mergeGroups(result, allResults, numGroups);

            /* Give FxWords the correct indices for the current input */
            targetUtil.setRuntimeIndices(result, rxPattern.getFxPattern());
            rxPattern.getFxPattern().disp();
            /* Clean up */
            allResults.clear();
            return true;
        }
    }



    @Override
    public RxResult getResult() {
        return result;
    }

    @Override
    public void disp() {

    }

    private static class SplitUtil {
        private int uq, start, lo, hi, numGroups;

        public void setGroups(ArrayList<RxResult> allResults){// expect non-empty ArrayList
            uq = 0;
            start = 0;
            int j = 0;
            while(setGroup(allResults) && 10 > j++){
                uq++;
            }
            numGroups = uq + 1;
        }
        private boolean setGroup(ArrayList<RxResult> allResults){
            boolean more = false;

            RxResult curr = allResults.get(start);
            lo = curr.getSeqIndex();
            hi = lo + curr.scoreDataSingle().getScore();

            for(int i = start; i < allResults.size(); i++){
                curr = allResults.get(i);
                if(curr.getSeqIndex() <= hi){
                    hi = Math.max(hi, lo + curr.scoreDataSingle().getScore());
                    curr.overlapData().setGroup(uq);
                }
                else if(!more){
                    start = i;
                    more = true;
                }
            }
            return more;
        }

        public int getNumGroups(){
            return numGroups;
        }

        public ArrayList<ArrayList<RxResult>> splitGroups(ArrayList<RxResult> allResults){//don't need this one
            ArrayList<ArrayList<RxResult>> groups = new ArrayList<>(numGroups);
            int i = -1, currGroup = -1;
            for(RxResult curr : allResults){
                if(currGroup != curr.overlapData().getGroup()){
                    i++;
                    groups.add(new ArrayList<>());
                    currGroup = curr.overlapData().getGroup();
                }
                groups.get(i).add(curr);
            }
            return groups;
        }
    }

    private static class MergeUtil{
        public void mergeGroups(RxResult mergedResult, ArrayList<RxResult> allResults, int numGroups){
            ArrayList<RxResult> groups = optimizeByGroup(allResults, numGroups);

            mergedResult.hitMapData().setHitMap(this.mergeHitMap(groups));
            mergedResult.scoreDataMerged().setSeqIndices(this.mergeIndices(groups));
            mergedResult.scoreDataMerged().setMatchLengths(this.mergeScores(groups));
        }

        private ArrayList<RxResult> optimizeByGroup(ArrayList<RxResult> allResults, int numGroups){
            ArrayList<RxResult> groups = new ArrayList<>(numGroups);
            for(int g = 0; g < numGroups; g++){
                groups.add(optimize(allResults, g));
            }
            return groups;
        }

        private RxResult optimize(ArrayList<RxResult> allResults, int g){
            int bestIndex = 0, bestScore = 0, i = 0;

            for(RxResult curr : allResults){
                if(g == curr.overlapData().getGroup() && curr.scoreDataSingle().getScore() > bestScore){
                    bestScore = curr.scoreDataSingle().getScore();
                    bestIndex = i;
                }
                i++;
            }
            return allResults.get(bestIndex);
        }

        private int[] mergeHitMap(ArrayList<RxResult> groups){
            int[] hitMap = new int[groups.get(0).hitMapData().getHitMap().length];
            for(int i = 0; i < hitMap.length; i++){
                hitMap[i] = -1;
            }
            for(RxResult curr : groups){
                int[] currHitMap = curr.hitMapData().getHitMap();
                for(int i = 0; i < hitMap.length; i++){
                    if(currHitMap[i] != -1){
                        hitMap[i] = currHitMap[i];
                    }
                }
            }
            return hitMap;
        }

        private int[] mergeIndices(ArrayList<RxResult> groups){
            int[] seqIndices = new int[groups.size()];
            int i = 0;
            for(RxResult curr : groups){
                seqIndices[i++] = curr.getSeqIndex();
            }
            return seqIndices;
        }

        private int[] mergeScores(ArrayList<RxResult> groups){
            int[] matchLengths = new int[groups.size()];
            int i = 0;
            for(RxResult curr : groups){
                matchLengths[i++] = curr.scoreDataSingle().getScore();
            }
            return matchLengths;
        }
    }

    private static class TargetUtil{

        /** Convert indices of interest (AKA getTargets() in fxPattern) to the corresponding
         *  indices in the matched portion of the runtime flagNode list.
         *  If fxPattern is interested in index 2, and rxEngine finds a match at index 4, then the
         *  matched node is at index 6.
         *  Behavior when a * or + is matched in the regex?  Multiple indices to choose from...
         *  Greedy algo lists first one and moves on, dynamic lists all of them
         *
         * @param result RxResultMergedS, returned from rxEngineS
         * @param fxPattern FxPatternS, passed to RxFxEngine */
        public void setRuntimeIndices(RxResult result, FxPattern fxPattern){
            FxWord[] fxWords = fxPattern.getFxWords();
            for(FxWord fxWord : fxWords){
                int[] runtimeIndices = this.indicesGreedy(
                    result.hitMapData().getHitMap(), fxWord.targetData().getTargets()
                );
                fxWord.targetData().setRuntimeIndices(runtimeIndices);
                Commons.disp(runtimeIndices, "TargetUtil runtimeIndices");
            }
        }

        /** Greedy and Dynamic versions have same behavior when t size = 1
         * @param h hitMap in the form [-1,-1,-1,-1,0,1,2,-1,-1]
         * @param t targetList in the form [0,1]
         * @return corresponding indices from the matched portion of the runtime flagNode list
         */
        private int[] indicesGreedy(int[] h, int[] t){
            int hIndex = 0, tIndex = 0, outIndex = 0, hLen = h.length, tLen = t.length, count = 0;
            while(hIndex < hLen){
                //System.out.printf("\n%d:%d %d:%d \n", hIndex, tIndex, h[hIndex], t[tIndex]);
                if(h[hIndex] == t[tIndex]){
                    //System.out.printf("%d:%d found %d \n", hIndex, tIndex, h[hIndex]);
                    count++;
                    tIndex++;
                }
                hIndex++;
                if(tIndex == tLen){
                    tIndex = 0;
                }
            }
            int[] out = new int[count];
            hIndex = tIndex = 0;
            while(outIndex < count){
                if(h[hIndex] == t[tIndex]){
                    out[outIndex++] = hIndex;
                    tIndex++;
                }
                if(tIndex == tLen){
                    tIndex = 0;
                }
                hIndex++;
            }
            return out;
        }

        private int[] indicesDynamic(int[] h, int[] t){
            int hIndex = 0, tIndex = 0, outIndex = 0, hLen = h.length, tLen = t.length, count = 0, lastHit = -2;
            while(hIndex < hLen){
                if(h[hIndex] == t[tIndex]){
                    lastHit = h[hIndex];
                    count++;
                    tIndex++;
                }
                else if(lastHit == h[hIndex]){
                    count++;
                }
                else{
                    lastHit = -2;
                }
                if(tIndex == tLen){
                    tIndex = 0;
                }
                hIndex++;
            }
            int[] out = new int[count];
            hIndex = tIndex = 0;
            while(outIndex < count){
                if(h[hIndex] == t[tIndex]){
                    lastHit = h[hIndex];
                    out[outIndex++] = hIndex;
                    tIndex++;
                }
                else if(lastHit == h[hIndex]){
                    out[outIndex++] = hIndex;
                }
                else{
                    lastHit = -2;
                }
                if(tIndex == tLen){
                    tIndex = 0;
                }
                hIndex++;
            }
            return out;
        }
    }
}
