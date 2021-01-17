package rxfxcore.util;

import commons.Commons;
import recursivelist.FlagNode;
import rxfxcore.interfaces.RxResult;

import java.util.ArrayList;

public class ResultScopeS implements ResultScope{
    /** The idea of scoping is to eliminate loops, instead providing data structures with
     *  the desired elements.
     *  This one handles non-overlapping matches: they need to be split into separate cases.
     *  Further scoping occurs in RxFxEngine for the input scopes
     *
     * @param scopes ArrayList to be populated by calls to this
     * @param result RxResultMergedS, returned from rxEngineS
     * @param flagNodes The input list passed to RxFxEngine
     * @return Array list of FlagNode arrays, non-overlapping matches separated into cases */
    @Override
    public void addAll(ArrayList<FlagNode[]> scopes, RxResult result, FlagNode[] flagNodes) {
        int[] indices = result.scoreDataMerged().getSeqIndices();
        int[] scores = result.scoreDataMerged().getMatchLengths();

        ArrayList<FlagNode[]> out = new ArrayList<>(indices.length);

        for(int i = indices.length - 1; i >= 0; i--){
            int start = indices[i];
            int end = start + scores[i];
            int k = 0;
            FlagNode[] curr = new FlagNode[scores[i]];
            for(int j = start; j < end; j++){
                curr[k++] = flagNodes[j];
            }
            out.add(curr);
        }
        scopes.addAll(out);
    }
}
