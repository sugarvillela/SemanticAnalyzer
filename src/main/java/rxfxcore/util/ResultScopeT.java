package rxfxcore.util;

import recursivelist.FlagNode;
import rxfxcore.interfaces.RxResult;

import java.util.ArrayList;

/** The flagNodes arg in target language implementations is a single-element array.
 *  This method is only called on matches, so it's a simple matter of copying input to output */
public class ResultScopeT implements ResultScope{
    @Override
    public void addAll(ArrayList<FlagNode[]> scopes, RxResult result, FlagNode[] flagNodes) {
        scopes.add(flagNodes);
    }
}
