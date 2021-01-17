package rxfxcore.interfaces;

import recursivelist.FlagNode;

public interface FxAction {
    void go(FxWord fxWord, FlagNode[] flagNodes, int index);
}
