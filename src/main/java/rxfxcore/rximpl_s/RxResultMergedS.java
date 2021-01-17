package rxfxcore.rximpl_s;

import java.util.Arrays;

public class RxResultMergedS extends RxResultS{
    public RxResultMergedS(){
        scoreDataSingleImpl = null;
        scoreDataMergedImpl = new ScoreDataMergedImpl();
        overlapDataImpl = null;
        hitMapDataImpl =  new HitMapDataS();
    }
    @Override
    public boolean merged() {
        return true;
    }

    @Override
    public ScoreDataMerged scoreDataMerged() {
        return scoreDataMergedImpl;
    }

    @Override
    public String toString() {
        return "RxResultS{" +
                "\n\tindex(" + caseIndex + ":" + seqIndex + ")" +
                "\n\thitMap=" + Arrays.toString(this.hitMapDataImpl.getHitMap()) +
                "\n\tscores=" + Arrays.toString(this.scoreDataMergedImpl.getMatchLengths()) +
                "\n\tindices=" + Arrays.toString(this.scoreDataMergedImpl.getSeqIndices()) +
                "\n\tmerged=" + merged() +
                "\n\toverlapGroup=NONE" +
                "\n}";
    }
}
