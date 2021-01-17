package rxfxcore.fximpl;

import generated.enums.ACTION;
import recursivelist.FlagNode;
import rxfxcore.interfaces.FxEngine;
import rxfxcore.interfaces.FxPattern;
import rxfxcore.interfaces.FxWord;
import rxfxcore.interfaces.RxResult;

public class FxEngineS implements FxEngine {
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
            int[] runtimeIndices = fxWord.targetData().getRuntimeIndices();
            for(int i = runtimeIndices.length -1; i >= 0; i--){
                System.out.println("index = "+ runtimeIndices[i]);
                fxWord.rewindIterators();
                while((action = fxWord.nextAction()) != null){
                    System.out.println("action "+action);
                    action.component.go(fxWord, flagNodes, runtimeIndices[i]);
                    System.out.println("\n========"+fxWord+"========");
                    flagNodes[runtimeIndices[i]].dispStore();
                    System.out.println("================");
                }
            }

        }

    }


}
