package rxword.impl;

import recursivelist.FlagNode;
import runstate.RunState;
import rxword.impl.RxFunRun;
import rxword.interfaces.RxWord;

// TODO put hi = remaining in rxfxParser code gen
public abstract class RxWordImpl implements RxWord{
    private int lo, hi;
    //private final RxWord rxWord;
    protected final RxFunRun f;// break naming rules for compactness in subclass impl

    public RxWordImpl(int lo, int hi) {
        this.lo = lo;
        this.hi = hi;//already corrected on code gen
        f = RunState.getRxFunRun();
    }
    public int getLo(){
        return lo;
    }

    public int getHi(){
        return hi;
    }

    public RxWord setNode(FlagNode flagNode) {
        f.setNode(flagNode);
        return this;
    }

    public abstract boolean go();
}
