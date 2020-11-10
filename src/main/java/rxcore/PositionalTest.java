package rxcore;

import java.util.ArrayList;
import java.util.Arrays;

public class PositionalTest {
    private final TestRunner.TestNodeMock[] testNodes;
    private final TestRunner.RxNodeMock[] rxNodes;
    private ArrayList<StateNode> states;
    private ArrayList<StateNode> winners;
    private int startIndex;
    private int idGenerator;    // debug id

    public PositionalTest(TestRunner.TestNodeMock[] testNodes, TestRunner.RxNodeMock[] rxNodes){
        this.testNodes = testNodes;
        this.rxNodes = rxNodes;

        states = new ArrayList<>();
        winners = new ArrayList<>();
        idGenerator = 0;
    }

    public void reset(int startIndex){
        this.startIndex = startIndex;
        states.clear();
        winners.clear();
        states.add(new StateNode());
        System.out.println("reset: "+startIndex);
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
    }

    public boolean haveWinner(){
        return winners.size() > 0;
    }

    public ArrayList<StateNode> getWinners(){
        return winners;
    }

    public void dispWinners(){
        for(StateNode state : winners){
            System.out.println(state);
        }
    }

    private int idGen(){
        return idGenerator++;
    }

    private class StateNode{
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
            hits = new int[testNodes.length];
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
            else if(iTest >= testNodes.length){
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
            if(currScore >= rxNodes[iRx].hi){// exceeded
                System.out.println("hitAndExceed: "+id);
                harvestCurr();
                incBoth();
                setWinFail();
                return !fail;
            }
            return false;
        }
        private boolean hitAndSplit(){
            if(currScore >= rxNodes[iRx].lo){// reached minimum
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
            if(currScore >= rxNodes[iRx].lo){// reached minimum
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
                    testNodes[iTest].get(1).toString(),
                    rxNodes[iRx].payload.toString(),
                    iTest, iRx
            );
            if(rxNodes[iRx].test(testNodes[iTest])){
                currScore++;
                hits[iTest] = iRx;
                System.out.printf("%d: YES, currScore=%d, totalScore=%d, lo=%d, hi=%d\n",
                        id,
                        currScore,
                        totalScore,
                        rxNodes[iRx].lo,
                        rxNodes[iRx].hi
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
    }
}
