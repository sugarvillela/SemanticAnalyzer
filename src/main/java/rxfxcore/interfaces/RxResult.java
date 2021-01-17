package rxfxcore.interfaces;

import recursivelist.FlagNode;

/** A data class for returning results from rx tests */
public interface RxResult extends Comparable<RxResult>{

    /** @param caseIndex The y-index (or outer array index) of the 2-d scopes array */
    void setCaseIndex(int caseIndex);
    int getCaseIndex();

    /** @param seqIndex The x-axis (or inner array) of the 2-d scopes array, flagNodes in a linked sequence, related */
    void setSeqIndex(int seqIndex);
    int getSeqIndex();

    /** Initial results are single; overlapping results are culled by score/matchLength.
     *  Non-overlapping results should be merged sequentially.
     *  This transformation occurs in RxEngineS.go()
     *
     * @return true if S impl and RxEngineS.go() is finished */
    boolean merged();

    ScoreDataSingle scoreDataSingle();
    ScoreDataMerged scoreDataMerged();
    OverlapData overlapData();
    HitMapData hitMapData();

    void disp();


    interface ScoreDataSingle {// source language implements
        /** @param score The length of the matched sequence, for comparison */
        void setScore(int score);
        int getScore();
    }
    interface ScoreDataMerged {// source language implements
        /** The x-axis (or inner array) of the 2-d scopes array, flagNodes in a linked sequence, related.
         *  Merging non-overlapping matches, save sequential indices in an array */
        void setSeqIndices(int[] seqIndices);
        int[] getSeqIndices();

        void setMatchLengths(int[] matchLengths);
        int[] getMatchLengths();
    }

    /** If a result does not overlap any other, it can be counted as a separate, non-competitive match */
    interface OverlapData{
        void setGroup(int group);
        int getGroup();
    }

    interface HitMapData{
        void setHitMap(int[] hitMap);
        int[] getHitMap();
    }
}
