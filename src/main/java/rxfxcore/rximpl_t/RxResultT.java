package rxfxcore.rximpl_t;

import rxfxcore.interfaces.RxResult;

public class RxResultT implements RxResult {
    private int caseIndex, seqIndex;

    @Override
    public void setCaseIndex(int caseIndex) {
        this.caseIndex = caseIndex;
    }

    @Override
    public int getCaseIndex() {
        return caseIndex;
    }

    @Override
    public void setSeqIndex(int seqIndex) {
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
        throw new IllegalStateException("Dev error");
    }

    @Override
    public ScoreDataMerged scoreDataMerged() {
        throw new IllegalStateException("Dev error");
    }

    @Override
    public OverlapData overlapData() {
        throw new IllegalStateException("Dev error");
    }

    @Override
    public HitMapData hitMapData() {
        throw new IllegalStateException("Dev error");
    }

    @Override
    public void disp() {
        System.out.println(this.toString());
    }

    @Override
    public int compareTo(RxResult other) {
        return 0;
    }

    @Override
    public String toString() {
        return "RxResultT{" +
                "\n    index(" + caseIndex + ":" + seqIndex + ")" +
                "\n}";
    }
}
