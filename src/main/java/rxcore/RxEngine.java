package rxcore;

import generated.patterns.PatternDefinition;
import recursivelist.IRecursiveNode;

import java.util.ArrayList;

/** Need a runner that can handle standard regex as well as the custom RxFx ones
 *  Need a boolean output to say whether something was matched/returned
 *  If something is returned, need a type-safe way to get that result
 *  Need a way to specify, per regex, what data to be returned, if any ie. $1, $2 etc
 */
public interface RxEngine {
    RxEngine setNodes(IRecursiveNode[] flagNodes);
    RxEngine setPattern(PatternDefinition patternDefinition);
    RxEngine reset(int newIndex);
    boolean test();
    boolean haveResult();
    ResultSet getResult();
    ArrayList<ResultSet> getResults();
    void disp();

}
