package rxfxcore;

import commons.Commons;
import generated.patterns.RxPatterns;
import recursivelist.FlagNode;
import runstate.RunState;
import rxword.impl.RxFunRun;
import rxword.impl.RxWordImpl;
import toktools.TokenizerSimple;

import static generated.funpatterns.RxFunPatterns.RX_FUN_PATTERN_001_00_00;
import static generated.lists.ListScopes.*;
import static generated.lists.ListString.IN;

public class CoreTests {
    private static FlagNode[] genFlagNodesS(){
        TokenizerSimple tok = new TokenizerSimple();
        String[] testList;
        testList = tok.getArray("I know I have a lovely lovely bunch of coconuts in my shoe");
        FlagNode[] out = new FlagNode[testList.length];
        for(int i = 0; i < testList.length; i++){
            out[i] = new FlagNode();
            out[i].set(IN, testList[i]);
            out[i].set(WORD);
        }
        return out;
    }
    private static FlagNode[][] genFlagNodesT(){
        String[] testList = {
                "abc/def", "ghi-klmn", "012/345", "678-910", "Schnitzel", "250,000"
        };
        FlagNode [][] out = new FlagNode[testList.length][];
        for(int i = 0; i < testList.length; i++){
            out[i] = new FlagNode[]{new FlagNode()};
            out[i][0].set(IN, testList[i]);
            out[i][0].set(WORD);
            out[i][0].dispStore();
        }
        return out;
    }
    private static RxWordImpl[] genRxSingles(){
        TokenizerSimple tok = new TokenizerSimple();
        String[] rxList = tok.getArray("I have a lovely bunch of coconuts");

        int[] rangeLo = {1, 1, 1, 1, 1, 1, 1};
        int[] rangeHi = {1, 1, 1, 3, 1, 1, 1};
        RxWordImpl[] out = new RxWordImpl[rxList.length];
        for(int i = 0; i < rxList.length; i++){
            int lo = rangeLo[i];
            int hi = rangeHi[i];
            int remaining = Math.min(hi, rxList.length - i);
            //out[i] = new RxWords.RxWord010(lo, remaining, rxList[i]);
        }
        return out;
    }

    public static void testTargLang(){
        RxFxEngine engine = RunState.getRxFxEngine();
        engine.causeEffect(CHAR, RxPatterns.RX_PATTERN_101);
    }

    public static void testSourceLang(){
        RxFxEngine engine = RunState.getRxFxEngine();
        engine.causeEffect(WORD, RxPatterns.RX_PATTERN_000);
        RunState.getRootNode().dispList();
        //engine.rxAsScope(RxPatterns.RX_PATTERN_001);
    }
    public static void testFunRun(){
        RxFunRun funRun = RunState.getRxFunRun();
        FlagNode node = new FlagNode();
        node.set(IN, "booboo");

        funRun.setNode(node);
        boolean result = funRun.run(RX_FUN_PATTERN_001_00_00);
        System.out.println("result = " + result);
    }

    private static int[] findTargetsGreedy(int[] h, int[] t){
        int hIndex = 0, tIndex = 0, outIndex = 0, hLen = h.length, tLen = t.length, count = 0;
        while(hIndex < hLen && outIndex < 20){
            System.out.printf("\n%d:%d %d:%d \n", hIndex, tIndex, h[hIndex], t[tIndex]);
            if(h[hIndex] == t[tIndex]){
                System.out.printf("%d:%d found %d \n", hIndex, tIndex, h[hIndex]);
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

    private static int[] findTargetsDynamic(int[] h, int[] t){
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

    public static void testRuntimeTargetFinder(){
        int[] h = new int[]{-1, 0, 0, 1, 2, -1, -1, 0, 1};
        int[] t = new int[]{0};
        //Commons.disp(findTargetsGreedy(h, t));
        Commons.disp(findTargetsDynamic(h, t));
    }

    public static void testGroupingAlgo(){// Split and Merge utils in RxEngineS
//        ArrayList<RxResult> allResults = new ArrayList<>();
//        allResults.add(new RxResult(1, new int[]{-1,0,0,1,2,-1,-1,-1,-1,-1,}, 4));
//        allResults.add(new RxResult(6, new int[]{-1,-1,-1,-1,-1,-1,0,0,1,2}, 4));
//        allResults.add(new RxResult(8, new int[]{-1,-1,-1,-1,-1,-1,-1,-1,1,2}, 2));
//        //Commons.disp(allResults, "before");
//        SplitUtil splitUtil = new SplitUtil();
//        splitUtil.setGroups(allResults);
//        Commons.disp(allResults, "after");
//
//        MergeUtil mergeUtil = new MergeUtil();
//        RxResult merged = mergeUtil.mergeGroups(allResults, splitUtil.getNumGroups());
//        System.out.println("merged:\n"+merged);

//        ArrayList<ArrayList<RxResult>> groups = splitUtil.splitGroups(allResults);
//        for(ArrayList<RxResult> group : groups){
//            Commons.disp(group, "group");
//        }

    }

}
