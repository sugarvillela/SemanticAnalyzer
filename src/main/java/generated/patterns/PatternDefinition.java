package generated.patterns;

import generated.code.PATTERN_TYPE;
import recursivelist.IRecursiveNode;
import rxcore.RxEngineSourceLang;
import rxcore.RxEngineTargLang;

public interface PatternDefinition {
    //PATTERN_TYPE getType();

    boolean test(int index, IRecursiveNode... flagNode);

    boolean test(IRecursiveNode... flagNode);

    void getResult(RxEngineTargLang requester);

    void getResult(RxEngineSourceLang requester);

    Object getPattern();

    Object[] actionsOnMatch();
}
