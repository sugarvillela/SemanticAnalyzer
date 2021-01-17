package rxfxcore.interfaces;

import recursivelist.FlagNode;

public interface FxEngine {
    FxEngine setNodes(FlagNode[] flagNodes);
    FxEngine setFxPattern(FxPattern fxPattern);
    FxEngine setResult(RxResult result);
    void go();
}
