package rxcore;

import flagobj.IFlags;
import flagobj.IRx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PositionalTest {
    private final IFlags[] flagNodes;
    private final IRx[] rxNodes;
    private ArrayList<StateNode> states;
    private ArrayList<StateNode> winners;
    private TestResult testResult;
    private int startIndex;
    private int idGenerator;    // debug id

    public PositionalTest(IFlags[] flagNodes, IRx[] rxNodes){
        this.flagNodes = flagNodes;
        this.rxNodes = rxNodes;

        states = new ArrayList<>();
        winners = new ArrayList<>();
        idGenerator = 0;
        testResult = null;
    }

    public void reset(int startIndex){
        this.startIndex = startIndex;
        states.clear();
        winners.clear();
        states.add(new StateNode());
        System.out.println("reset: "+startIndex);
        testResult = null;
        //idGenerator = 0;
    }

    public void test(){
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
        if(haveWinner()){
            Collections.sort(winners);
            testResult = new TestResult(startIndex, winners);
        }
    }

    public boolean haveWinner(){
        return winners.size() > 0;
    }

    public TestResult getTestResult(){
        return testResult;
    }

    public void dispWinners(){
        for(StateNode state : winners){
            System.out.println(state);
        }
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
            if(iRx >= rxNodes.length){
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
            if(currScore >= rxNodes[iRx].getHi()){// exceeded
                System.out.println("hitAndExceed: "+id);
                harvestCurr();
                incBoth();
                setWinFail();
                return !fail;
            }
            return false;
        }
        private boolean hitAndSplit(){
            if(currScore >= rxNodes[iRx].getLo()){// reached minimum
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
            if(currScore >= rxNodes[iRx].getLo()){// reached minimum
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
                    flagNodes[iTest].getObject(1).toString(),
                    rxNodes[iRx].getObj().toString(),
                    iTest, iRx
            );
            if(rxNodes[iRx].test(flagNodes[iTest])){
                currScore++;
                hits[iTest] = iRx;
                System.out.printf("%d: YES, currScore=%d, totalScore=%d, lo=%d, hi=%d\n",
                        id,
                        currScore,
                        totalScore,
                        rxNodes[iRx].getLo(),
                        rxNodes[iRx].getHi()
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

    public static class TestResult implements ITestResult{
        private final int startIndex;
        private int[] bestMap;
        private int bestScore;

        public TestResult(int startIndex, ArrayList<StateNode> winners){
            this.startIndex = startIndex;
            int bestIndex = 0, bestTotal = 0, i = 0;
            for(StateNode winner : winners){
                if(winner.totalScore > bestTotal){
                    bestTotal = winner.totalScore;
                    bestIndex = i;
                }
            }
            bestMap = winners.get(bestIndex).hits;
            bestScore = winners.get(bestIndex).totalScore;
        }

        @Override
        public int[] getBestMap() {
            return bestMap;
        }

        @Override
        public int getBestScore() {
            return bestScore;
        }

        @Override
        public int getStartIndex() {
            return startIndex;
        }

        @Override
        public int compareTo(ITestResult other) {
            if(this.bestScore > other.getBestScore()){
                return -1;
            }
            if(this.bestScore < other.getBestScore()){
                return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "TestResult{" +
                    "\n\tstartIndex=" + startIndex +
                    "\n\tbestMap=" + Arrays.toString(bestMap) +
                    "\n\tbestScore=" + bestScore +
                    "\n}";
        }
    }
}
