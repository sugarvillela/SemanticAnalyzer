package rxcore;

import java.util.Arrays;

public class ResultSetSourceLang implements ResultSet {
    private final int startIndex;
    private final int[] bestMap;
    private final int bestScore;

    public ResultSetSourceLang(ResultSet result) {
        this.startIndex = result.getStartIndex();
        this.bestMap = result.getBestMap();
        this.bestScore = result.getBestScore();
    }

    public ResultSetSourceLang(int startIndex, int[] bestMap, int bestScore) {
        this.startIndex = startIndex;
        this.bestMap = bestMap;
        this.bestScore = bestScore;
    }


    @Override
    public int[] getBestMap() {
        return bestMap;
    }

    @Override
    public int getBestScore() {
        return bestScore;
    }

    @Override
    public int getStartIndex() {
        return startIndex;
    }

    @Override
    public int compareTo(ResultSet other) {
        if (this.bestScore > other.getBestScore()) {
            return -1;
        }
        if (this.bestScore < other.getBestScore()) {
            return 1;
        }
        return 0;
    }

    @Override
    public String[] getStringResults() {
        return new String[0];
    }

    @Override
    public Object[] actionsOnMatch() {
        return new Integer[0];
    }

    @Override
    public void disp() {
        System.out.println("Not all fields included");
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "RunResult{" +
                "\n\tstartIndex=" + startIndex +
                "\n\tbestMap=" + Arrays.toString(bestMap) +
                "\n\tbestScore=" + bestScore +
                "\n}";
    }


}
