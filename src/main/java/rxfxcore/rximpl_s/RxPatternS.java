package rxfxcore.rximpl_s;

import generated.enums.PATTERN_TYPE;
import rxfxcore.interfaces.FxPattern;
import rxfxcore.interfaces.RxResult;
import rxfxcore.interfaces.RxPattern;
import recursivelist.FlagNode;
import rxword.impl.RxWordImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static generated.lists.ListString.IN;

public class RxPatternS implements RxPattern {
    private FlagNode[] flagNodes;
    private RxWordImpl[] rxWords;
    private final ArrayList<StateNode> states;
    private final ArrayList<StateNode> winners;
    private int idGenerator;    // debug id
    private int startIndex;
    private RxResult result;
    private final FxPattern fxPattern;

    public RxPatternS(FxPattern fxPattern) {
        this.fxPattern = fxPattern;
        states = new ArrayList<>();
        winners = new ArrayList<>();
    }

    protected final void initRxWords(RxWordImpl... rxWords){
        this.rxWords = rxWords;
    }

    private int nextId(){
        return idGenerator++;
    }

    @Override
    public PATTERN_TYPE getPatternType() {
        return PATTERN_TYPE.SOURCE_LANG;
    }

    @Override
    public FxPattern getFxPattern() {
        return fxPattern;
    }

    @Override
    public RxPattern setIndex(int index) {
        this.startIndex = index;
        states.clear();
        winners.clear();
        states.add(new StateNode(this));
        //System.out.println("reset: "+ index);
        result = null;
        return this;
    }

    @Override
    public RxPattern setNodes(FlagNode[] flagNodes) {
        this.flagNodes = flagNodes;
        return this;
    }

    @Override
    public boolean go() {
        boolean more;
        do{
            more = false;
            int len = states.size();
            for(int i = 0; i < len; i++){
                StateNode state = states.get(i);
                more |= !state.fail && !state.win && state.test();
                System.out.printf("\n____ PositionalTest id=%d, more=%b, fail=%b, win=%b ____\n",
                        state.id,
                        more,
                        state.fail,
                        state.win
                );
            }
        }
        while (more);
        if(!winners.isEmpty()){
            Collections.sort(winners);
            setBestResult();
            return true;
        }
        return false;
    }

    private void setBestResult(){
        int bestIndex = 0, bestTotal = 0, i = 0;
        for(StateNode winner : winners){
            if(winner.totalScore > bestTotal){
                bestTotal = winner.totalScore;
                bestIndex = i;
            }
        }
        int[] bestMap = winners.get(bestIndex).hits;
        int bestScore = winners.get(bestIndex).totalScore;
        result = new RxResultS();
        result.hitMapData().setHitMap(bestMap);
        result.setSeqIndex(startIndex);
        result.scoreDataSingle().setScore(bestScore);
    }

    @Override
    public RxResult getResult() {
        return result;
    }

    @Override
    public void disp() {

    }

    private static class StateNode implements Comparable<StateNode> {// comparable not used now
        private final RxPatternS parent;
        public int[] hits;
        public boolean fail, win;
        public int iTest, iRx;
        public int totalScore, currScore;
        public int id;

        public StateNode(RxPatternS parent){
            this.parent = parent;
            iTest = parent.startIndex;
            iRx = totalScore = currScore = 0;
            fail = win = false;
            initHits();
            id = parent.nextId();
        }
        public StateNode(StateNode prevState){// to add non-deterministic states
            this.parent = prevState.parent;
            iTest = prevState.iTest + 1;
            iRx = prevState.iRx;
            totalScore = prevState.totalScore;
            currScore = prevState.currScore;
            setWinFail();
            initHits(prevState.hits);
            id = parent.nextId();
            //System.out.println("new nondeterministic state: "+id);
        }
        private void initHits(){
            hits = new int[parent.flagNodes.length];
            for(int i = 0; i < hits.length; i++){
                hits[i] = -1;
            }
        }
        private void initHits(int[] prevHits){
            hits = new int[prevHits.length];
            for(int i = 0; i < hits.length; i++){
                hits[i] = prevHits[i];
            }
        }
        private void harvestCurr(){
            totalScore += currScore;
            currScore = 0;
        }
        private void incTest(){
            iTest++;
        }
        private void incRx(){
            iRx++;
        }
        private void incBoth(){
            iTest++;
            iRx++;
        }
        private void setWinFail(){
            if(iRx >= parent.rxWords.length){
                win = true;
                fail = false;
            }
            else if(iTest >= parent.flagNodes.length){
                win = false;
                fail = true;
            }
        }
        private boolean forceFail(){
            System.out.println("forceFail: "+id);
            return (fail = true);
        }
        private void setWinner(){
            if(win){
                //System.out.println("addWinner: "+id);
                parent.winners.add(this);
            }
        }
        private void addNonDeterministicState(){
            parent.states.add(new StateNode(this));
        }
        private boolean hitAndExceed(){
            if(currScore >= parent.rxWords[iRx].getHi()){// exceeded
                System.out.println("hitAndExceed: "+id);
                harvestCurr();
                incBoth();
                setWinFail();
                return !fail;
            }
            return false;
        }
        private boolean hitAndSplit(){
            if(currScore >= parent.rxWords[iRx].getLo()){// reached minimum
                System.out.println("hitAndSplit: "+id);
                addNonDeterministicState();
                harvestCurr();
                incBoth();
                setWinFail();
                return !fail;
            }
            return false;
        }
        private boolean hitAndWait(){
            System.out.println("hitAndWait: "+id);
            incTest();
            setWinFail();
            return !fail;
        }
        private boolean missWithPrevHit(){
            if(currScore >= parent.rxWords[iRx].getLo()){// reached minimum
                System.out.println("missWithPrevHit: "+id);
                harvestCurr();
                incBoth();
                setWinFail();
                return !fail;
            }
            return false;
        }

        public boolean test(){
            System.out.printf("\n%d: %s ?= %s, iTest=%d, iRx=%d\n",
                    id,
                    parent.flagNodes[iTest].getString(IN),
                    parent.rxWords[iRx].toString(),
                    iTest, iRx
            );
            if(parent.rxWords[iRx].setNode(parent.flagNodes[iTest]).go()){
                currScore++;
                hits[iTest] = iRx;
//                System.out.printf("%d: YES, currScore=%d, totalScore=%d, lo=%d, hi=%d\n",
//                        id,
//                        currScore,
//                        totalScore,
//                        parent.rxWords[iRx].getLo(),
//                        parent.rxWords[iRx].getHi()
//                );
                if(hitAndExceed() || hitAndSplit() || hitAndWait());
                setWinner();
            }
            else{
                if(missWithPrevHit() || forceFail());
            }
            return !win && !fail;
        }

        @Override
        public String toString() {
            return "StateNode{" +
                    "\n\thits=" + Arrays.toString(hits) +
                    "\n\tfail=" + fail +
                    "\n\twin=" + win +
                    "\n\tiTest=" + iTest +
                    "\n\tiRx=" + iRx +
                    "\n\ttotalScore=" + totalScore +
                    "\n\tcurrScore=" + currScore +
                    "\n\tid=" + id +
                    "\n}";
        }

        @Override
        public int compareTo(StateNode other) {// made to sort backward
            if(this.totalScore > other.totalScore){
                return -1;
            }
            if(this.totalScore < other.totalScore){
                return 1;
            }
            return 0;
        }
    }
}
