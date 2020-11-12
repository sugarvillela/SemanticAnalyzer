package rxcore;

import flagobj.IFlags;
import flagobj.IRx;

import java.util.ArrayList;
import java.util.Arrays;

public class TestRunner {
    private final IFlags[] flagNodes;
    private final IRx[] rxNodes;
    private final PositionalTest positionalTest;
    private final ArrayList<ITestResult> testResults;
    private ITestResult bestResult;

    public TestRunner(IFlags[] flagNodes, IRx[] rxNodes){
        this.flagNodes = flagNodes;
        this.rxNodes = rxNodes;
        convertHiToRemaining();
        positionalTest = new PositionalTest(flagNodes, rxNodes);
        testResults = new ArrayList<>();
    }
    private void convertHiToRemaining(){
        System.out.printf("convertHiToRemaining:  rxNodes.length = %d \n", rxNodes.length);
        for(int i = 0; i < rxNodes.length; i++){
            System.out.printf("Before %d, ", rxNodes[i].getHi());
            rxNodes[i].setHi(Math.min(rxNodes[i].getHi(), rxNodes.length - i));
            System.out.printf("After %d \n", rxNodes[i].getHi());
        }
    }
    public void test(){
        for(int i = 0; i < flagNodes.length; i++){
            positionalTest.reset(i);
            positionalTest.test();
            if(positionalTest.haveWinner()){
                positionalTest.dispWinners();
                testResults.add(positionalTest.getTestResult());
                break;
            }
        }
        setBestResult();
    }
    private void setBestResult(){
        int bestIndex = 0, bestTotal = 0, i = 0;
        for(ITestResult testResult : testResults){
            if(testResult.getBestScore() > bestTotal){
                bestTotal = testResult.getBestScore();
                bestIndex = i;
            }
        }
        bestResult = new RunResult(testResults.get(bestIndex));
    }

    public void dispTestResults(){
        for(ITestResult testResult : testResults){
            System.out.println(testResult);
        }
    }
    public ITestResult getBestResult(){
        return bestResult;
    }

    public static class RunResult implements ITestResult{
        private final int startIndex;
        private final int[] bestMap;
        private final int bestScore;

        public RunResult(ITestResult result) {
            this.startIndex = result.getStartIndex();
            this.bestMap = result.getBestMap();
            this.bestScore = result.getBestScore();
        }
        public RunResult(int startIndex, int[] bestMap, int bestScore) {
            this.startIndex = startIndex;
            this.bestMap = bestMap;
            this.bestScore = bestScore;
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
            return "RunResults{" +
                    "\n\tstartIndex=" + startIndex +
                    "\n\tbestMap=" + Arrays.toString(bestMap) +
                    "\n\tbestScore=" + bestScore +
                    "\n}";
        }
    }
}
