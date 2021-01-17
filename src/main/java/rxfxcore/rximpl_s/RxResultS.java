package rxfxcore.rximpl_s;

import recursivelist.FlagNode;
import rxfxcore.interfaces.RxResult;

import java.util.Arrays;

public class RxResultS implements RxResult {
    protected ScoreDataSingle scoreDataSingleImpl;
    protected ScoreDataMerged scoreDataMergedImpl;
    protected OverlapData overlapDataImpl;
    protected HitMapData hitMapDataImpl;

    protected int caseIndex, seqIndex;

    public RxResultS() {
        scoreDataSingleImpl = new ScoreDataSingleImpl();
        scoreDataMergedImpl = null;
        overlapDataImpl = new OverlapDataS();
        hitMapDataImpl =  new HitMapDataS();
    }

    public static class ScoreDataSingleImpl implements ScoreDataSingle {
        private int score;

        @Override
        public void setScore(int score) {
            this.score = score;
        }

        @Override
        public int getScore() {
            return score;
        }
    }

    public static class ScoreDataMergedImpl implements ScoreDataMerged {
        private int[] seqIndices;
        private int[] matchLengths;

        @Override
        public void setSeqIndices(int[] seqIndices) {
            this.seqIndices = seqIndices;
        }

        @Override
        public int[] getSeqIndices() {
            return seqIndices;
        }

        @Override
        public void setMatchLengths(int[] matchLengths) {
            this.matchLengths = matchLengths;
        }

        @Override
        public int[] getMatchLengths() {
            return matchLengths;
        }
    }

    public static class OverlapDataS implements OverlapData{
        private int group;

        @Override
        public void setGroup(int group) {
            this.group =group;
        }

        @Override
        public int getGroup() {
            return group;
        }
    }

    public static class HitMapDataS implements HitMapData{
        private int[] hitMap;

        @Override
        public void setHitMap(int[] hitMap) {
            this.hitMap = hitMap;
        }

        @Override
        public int[] getHitMap() {
            return hitMap;
        }

    }

    @Override
    public void setCaseIndex(int caseIndex) {
        this.caseIndex = caseIndex;
    }

    @Override
    public int getCaseIndex() {
        return caseIndex;
    }

    @Override
    public final void setSeqIndex(int seqIndex) {
        this.seqIndex = seqIndex;
    }

    @Override
    public int getSeqIndex() {
        return seqIndex;
    }

    @Override
    public boolean merged() {
        return false;
    }


    @Override
    public ScoreDataSingle scoreDataSingle() {
        return scoreDataSingleImpl;
    }

    @Override
    public ScoreDataMerged scoreDataMerged() {
        throw new IllegalStateException("Dev error");
    }

    @Override
    public OverlapData overlapData() {
        return overlapDataImpl;
    }

    @Override
    public HitMapData hitMapData() {
        return hitMapDataImpl;
    }

    @Override
    public void disp() {
        System.out.println(this.toString());
    }


    @Override
    public int compareTo(RxResult other) {
        if (this.scoreDataSingleImpl.getScore() > other.scoreDataSingle().getScore()) {
            return -1;
        }
        if (this.scoreDataSingleImpl.getScore() < other.scoreDataSingle().getScore()) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "RxResultS{" +
                "\n\tindex(" + caseIndex + ":" + seqIndex + ")" +
                "\n\thitMap=" + Arrays.toString(this.hitMapDataImpl.getHitMap()) +
                "\n\tscore/length=" + this.scoreDataSingleImpl.getScore() +
                "\n\tmerged=" + merged() +
                "\n\toverlapGroup=" + this.overlapData().getGroup() +
                "\n}";
    }
}
