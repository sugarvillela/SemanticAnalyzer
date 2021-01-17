package rxfxcore.fximpl;

import generated.enums.ACTION;
import rxfxcore.interfaces.FxEngine;
import rxfxcore.interfaces.FxPattern;
import rxfxcore.interfaces.FxWord;
import rxfxcore.interfaces.RxResult;
import recursivelist.FlagNode;

public class FxEngineT implements FxEngine {
    protected FlagNode[] flagNodes;
    protected FxPattern fxPattern;
    protected RxResult result;

    @Override
    public FxEngine setNodes(FlagNode[] flagNodes) {
        this.flagNodes = flagNodes;
        return this;
    }

    @Override
    public FxEngine setFxPattern(FxPattern fxPattern) {
        this.fxPattern = fxPattern;
        return this;
    }

    @Override
    public FxEngine setResult(RxResult result) {
        this.result = result;
        return this;
    }

    @Override
    public void go() {
        fxPattern.setResult(result);
        ACTION action;
        FxWord[] fxWords = fxPattern.getFxWords();
        for(FxWord fxWord : fxWords){
            while((action = fxWord.nextAction()) != null){
                System.out.println("action "+action);
                action.component.go(fxWord, flagNodes, 0);
                System.out.println("\n========"+fxPattern+"========");
                flagNodes[0].dispStore();
                System.out.println("================");
            }
        }

    }

}
