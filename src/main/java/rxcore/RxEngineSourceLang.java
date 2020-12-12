package rxcore;

import generated.patterns.PatternDefinition;
import generated.rxwords.RxWord;
import recursivelist.IRecursiveNode;
import rxcore.util.SourceLangTestUtil;

import java.util.ArrayList;

public class RxEngineSourceLang implements RxEngine {
    private IRecursiveNode[] flagNodes;
    //private final RxWord[] rxWords;
    private final RxEngine testUtil;
    private final ArrayList<ResultSet> testResults;
    private ResultSet bestResult;

    public RxEngineSourceLang(){
        //this.rxWords = rxWords;
        //convertHiToRemaining();
        testUtil = new SourceLangTestUtil();
        testResults = new ArrayList<>();
    }

    @Override
    public RxEngine setNodes(IRecursiveNode[] flagNodes) {
        this.flagNodes = flagNodes;
        testUtil.setNodes(flagNodes);
        return this;
    }

    @Override
    public RxEngine reset(int newIndex) {
        return this;
    }

    @Override
    public RxEngine setPattern(PatternDefinition patternDefinition) {
        testUtil.setPattern(patternDefinition);
        return this;
    }

    private void convertHiToRemaining(){
//        System.out.printf("convertHiToRemaining:  rxNodes.length = %d \n", rxWords.length);
//        for(int i = 0; i < rxWords.length; i++){
//            System.out.printf("Before %d, ", rxWords[i].getHi());
//            rxNodes[i].setHi(Math.min(rxNodes[i].getHi(), rxNodes.length - i));
//            System.out.printf("After %d \n", rxWords[i].getHi());
//        }
    }

    @Override
    public boolean test(){
        for(int i = 0; i < flagNodes.length; i++){
            testUtil.reset(i).test();
            if(testUtil.haveResult()){
                testUtil.disp();
                testResults.add(testUtil.getResult());
                break;
            }
        }
        setBestResult();
        return bestResult != null;
    }

    public void receive(){

    }
    private void setBestResult(){
        int bestIndex = 0, bestTotal = 0, i = 0;
        for(ResultSet testResult : testResults){
            if(testResult.getBestScore() > bestTotal){
                bestTotal = testResult.getBestScore();
                bestIndex = i;
            }
        }
        bestResult = (testResults.isEmpty())? null : new ResultSetSourceLang(testResults.get(bestIndex));
    }

    @Override
    public boolean haveResult(){
        return bestResult != null;
    }

    @Override
    public void disp(){
        for(ResultSet testResult : testResults){
            System.out.println(testResult);
        }
    }

    @Override
    public ResultSet getResult(){
        return bestResult;
    }

    @Override
    public ArrayList<ResultSet> getResults() {
        return null;
    }
}
