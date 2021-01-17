package rxfxcore.util;

import recursivelist.FlagNode;
import rxfxcore.interfaces.RxResult;

import java.util.ArrayList;

public interface ResultScope {
    void addAll(ArrayList<FlagNode[]> scopes, RxResult result, FlagNode[] flagNodes);
}
