package rxcore.util;

import generated.patterns.PatternDefinition;
import generated.patterns.PatternSourceLang;
import generated.rxwords.RxWord;
import recursivelist.IRecursiveNode;
import rxcore.ResultSet;
import rxcore.ResultSetSourceLang;
import rxcore.RxEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static generated.lists.ListString.IN;

public class SourceLangTestUtil implements RxEngine {
    private IRecursiveNode[] flagNodes;
    private PatternSourceLang patternDefinition;
    private RxWord[] rxWords;
    private final ArrayList<StateNode> states;
    private final ArrayList<StateNode> winners;
    private ResultSet testResult;
    private int startIndex;
    private int idGenerator;    // debug id

    public SourceLangTestUtil(){
        //this.rxWords = rxWords;
        states = new ArrayList<>();
        winners = new ArrayList<>();
        idGenerator = 0;
        testResult = null;
    }

    @Override
    public RxEngine setNodes(IRecursiveNode[] flagNodes) {
        this.flagNodes = flagNodes;
        return this;
    }

    @Override
    public RxEngine reset(int newIndex) {
        this.startIndex = newIndex;
        states.clear();
        winners.clear();
        states.add(new StateNode());
        System.out.println("reset: "+ newIndex);
        testResult = null;
        return this;
    }

    @Override
    public RxEngine setPattern(PatternDefinition patternDefinition) {
        if(
                !(patternDefinition instanceof PatternSourceLang)
        ){
            throw new IllegalStateException("Dev err: blind cast will fail: " + patternDefinition.getClass().getSimpleName());
        }
        this.patternDefinition = (PatternSourceLang)patternDefinition;
        this.rxWords = (RxWord[])patternDefinition.getPattern();
        return this;
    }

    @Override
    public boolean test(){
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
        if(haveResult()){
            Collections.sort(winners);
            setBestResult();
            return true;
        }
        return false;
    }

    @Override
    public boolean haveResult() {
        return !winners.isEmpty();
    }

    @Override
    public ResultSet getResult() {
        return testResult;
    }

    @Override
    public ArrayList<ResultSet> getResults() {
        return null;
    }

    @Override
    public void disp() {
        for(StateNode state : winners){
            System.out.println(state);
        }
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
        testResult = new ResultSetSourceLang(startIndex, bestMap, bestScore);
    }

    private int idGen(){
        return idGenerator++;
    }

    private class StateNode implements Comparable<StateNode> {// comparable not used now
        public int[] hits;
        public boolean fail, win;
        public int iTest, iRx;
        public int totalScore, currScore;
        public int id;

        public StateNode(){
            iTest = startIndex;
            iRx = totalScore = currScore = 0;
            fail = win = false;
            initHits();
            id = idGen();
        }
        public StateNode(StateNode prevState){// to add non-deterministic states
            iTest = prevState.iTest + 1;
            iRx = prevState.iRx;
            totalScore = prevState.totalScore;
            currScore = prevState.currScore;
            setWinFail();
            initHits(prevState.hits);
            id = idGen();
            System.out.println("new nondeterministic state: "+id);
        }
        private void initHits(){
            hits = new int[flagNodes.length];
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
            if(iRx >= rxWords.length){
                win = true;
                fail = false;
            }
            else if(iTest >= flagNodes.length){
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
                System.out.println("addWinner: "+id);
                winners.add(this);
            }
        }
        private void addNonDeterministicState(){
            states.add(new StateNode(this));
        }
        private boolean hitAndExceed(){
            if(currScore >= rxWords[iRx].getHi()){// exceeded
                System.out.println("hitAndExceed: "+id);
                harvestCurr();
                incBoth();
                setWinFail();
                return !fail;
            }
            return false;
        }
        private boolean hitAndSplit(){
            if(currScore >= rxWords[iRx].getLo()){// reached minimum
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
            if(currScore >= rxWords[iRx].getLo()){// reached minimum
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
                    flagNodes[iTest].getString(IN),
                    rxWords[iRx].getPattern().toString(),
                    iTest, iRx
            );
            if(rxWords[iRx].test(flagNodes[iTest])){
                currScore++;
                hits[iTest] = iRx;
                System.out.printf("%d: YES, currScore=%d, totalScore=%d, lo=%d, hi=%d\n",
                        id,
                        currScore,
                        totalScore,
                        rxWords[iRx].getLo(),
                        rxWords[iRx].getHi()
                );
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
