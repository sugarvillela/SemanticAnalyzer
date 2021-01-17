package rxfxcore.rximpl_t;

import rxfxcore.interfaces.RxResult;
import rxfxcore.interfaces.RxEngine;
import rxfxcore.interfaces.RxPattern;
import recursivelist.FlagNode;

public class RxEngineT implements RxEngine {
    protected RxResult result;
    protected FlagNode[] flagNodes;
    protected RxPatternT rxPattern;
    protected int caseIndex;

    public RxEngineT() { }

    @Override
    public RxEngine setNodes(FlagNode[] flagNodes) {
        this.flagNodes = flagNodes;
        return this;
    }

    @Override
    public RxEngine setRxPattern(RxPattern rxPattern) {
        this.rxPattern = (RxPatternT)rxPattern;
        return this;
    }

    @Override
    public RxEngine setIndex(int index) {
        caseIndex = index;
        return this;
    }

    @Override
    public boolean go() {
        if(rxPattern.setNodes(flagNodes).go()){
            System.out.println("RxEngine go found");
            result = rxPattern.getResult();
            result.setCaseIndex(caseIndex);
            return true;
        }
        result = null;
        //System.out.println("RxEngine go fail");
        return false;
    }

    @Override
    public RxResult getResult() {
        return result;
    }

    @Override
    public void disp() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "\nRxEngine T{" +
                "\n    rxPattern=" + rxPattern +
                "\n}";
    }
}
