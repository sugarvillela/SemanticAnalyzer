package rxfxcore.fximpl;

import rxfxcore.interfaces.FxPattern;
import rxfxcore.interfaces.FxWord;
import rxfxcore.interfaces.RxResult;

public class FxPatternT implements FxPattern {
    private FxWord[] fxWords;

    /** The hook for generated subclasses to populate the fxWord list
     * @param fxWords defined in generated.patterns.FxWords */
    protected void initFxWords(FxWord... fxWords){
        this.fxWords = fxWords;
    }

    @Override
    public FxPattern setResult(RxResult result) {
        return this;
    }

    @Override
    public FxWord[] getFxWords() {
        return fxWords;
    }

    @Override
    public void disp() {
        System.out.println("FxPatternT.display");
        for(FxWord fxWord : fxWords){
            fxWord.disp();
        }
        System.out.println("end display");
    }
}
