package generated.patterns;

import generated.rxwords.RxWord;
import recursivelist.IRecursiveNode;
import rxcore.RxEngineSourceLang;
import rxcore.RxEngineTargLang;

import static generated.lists.ListString.IN;

public class PatternSourceLang implements PatternDefinition {
    //private final Object payload;
    private final RxWord[] rxWords;

    public PatternSourceLang(RxWord[] rxWords) {
        //this.payload = payload;
        this.rxWords = rxWords;
    }

    @Override
    public boolean test(int index, IRecursiveNode... flagNode) {
        return false;//this.payload.equals(flagNode[index].getString(IN));
    }

    @Override
    public boolean test(IRecursiveNode... flagNode) {
        return this.test(0, flagNode);
    }

    @Override
    public void getResult(RxEngineTargLang requester) {}

    @Override
    public void getResult(RxEngineSourceLang requester) {

    }

    @Override
    public Object[] actionsOnMatch() {
        return new Object[0];
    }

    @Override
    public Object getPattern() {
        return rxWords;
    }
}
