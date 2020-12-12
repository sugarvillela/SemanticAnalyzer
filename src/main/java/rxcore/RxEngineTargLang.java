package rxcore;

import generated.patterns.PatternDefinition;
import generated.patterns.PatternTargLang;
import recursivelist.IRecursiveNode;

import java.util.ArrayList;
import java.util.Arrays;

public class RxEngineTargLang implements RxEngine {
    //private final RxEngine positionalTest;
    private final ArrayList<ResultSet> testResults;
    protected IRecursiveNode[] flagNodes;
    protected ResultSet currResult;
    protected boolean haveResult;
    protected PatternTargLang patternDefinition;

    //private int currIndex;

    public RxEngineTargLang() {
        //positionalTest = new RxEngineTargLangPositional();
        testResults = new ArrayList<>();

    }

    @Override
    public RxEngine setNodes(IRecursiveNode[] flagNodes) {
        this.flagNodes = flagNodes;
        //positionalTest.setNodes(flagNodes);
        return this;
    }

    @Override
    public RxEngine reset(int newIndex) {
        //this.startIndex = startIndex;
        //positionalTest.setIndex(startIndex);
        return this;
    }

    @Override
    public RxEngine setPattern(PatternDefinition patternDefinition) {
        if(
            !(patternDefinition instanceof PatternTargLang)
        ){
            throw new IllegalStateException("Dev err: blind cast will fail: " + patternDefinition.getClass().getSimpleName());
        }
        this.patternDefinition = (PatternTargLang)patternDefinition;
        return this;
    }

    @Override
    public boolean test() {
        currResult = null;
        for(int i = 0; i < flagNodes.length; i++){// visitor pattern
            if(patternDefinition.test(i, flagNodes[i])){
                patternDefinition.getResult(this);
            }
        }
        return !testResults.isEmpty();
    }

    public void receive(PatternTargLang pattern, int currIndex, String[] stringResults){
        currResult = new ResultSetTargLang(currIndex, stringResults, pattern.actionsOnMatch());
        testResults.add(currResult);
    }

    @Override
    public boolean haveResult() {
        return !testResults.isEmpty();
    }

    @Override
    public ResultSet getResult() {
        return currResult;
    }

    @Override
    public ArrayList<ResultSet> getResults() {
        return testResults;
    }

    @Override
    public void disp() {

    }

    @Override
    public String toString() {
        return "RxEngineTargLang{" +
                ", flagNodes=" + Arrays.toString(flagNodes) +
                ", haveResult=" + haveResult +
                ", testResults=" + testResults +
                '}';
    }
}
