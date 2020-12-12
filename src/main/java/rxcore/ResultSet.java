package rxcore;

public interface ResultSet extends Comparable<ResultSet>{
    // source language rx
    int[] getBestMap();
    int getBestScore();
    int getStartIndex();

    // target language regex
    String[] getStringResults();
    Object[] actionsOnMatch();
    void disp();
}
