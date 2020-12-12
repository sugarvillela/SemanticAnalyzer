package rxcore;

import commons.Commons;

import java.util.Arrays;

public class ResultSetTargLang implements ResultSet {
    private final String[] stringResults;
    private final Object[] actionsOnMatch;
    private final int index;

    public ResultSetTargLang(int index, String[] stringResults, Object[] actionsOnMatch) {
        this.index = index;
        this.stringResults = stringResults;
        this.actionsOnMatch = actionsOnMatch;
    }

    @Override
    public int[] getBestMap() {
        return new int[0];
    }

    @Override
    public int getBestScore() {
        return 0;
    }

    @Override
    public int getStartIndex() {
        return 0;
    }

    @Override
    public String[] getStringResults() {
        return stringResults;
    }

    @Override
    public Object[] actionsOnMatch() {
        return actionsOnMatch;
    }

    @Override
    public void disp(){
        Commons.disp(stringResults, "stringResults");
        //System.out.println("\nactionsOnMatch");
        if(actionsOnMatch != null){
            for(Object action : actionsOnMatch){
                System.out.printf("Action: 0x%X \n", action);
            }
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "ResultSetTargLang{" +
            "\n    index=" + index +
            "\n    stringResults=" + Arrays.toString(stringResults) +
            "\n    actionsOnMatch=" + Arrays.toString(actionsOnMatch) +
            "\n}";
    }

    @Override
    public int compareTo(ResultSet o) {
        return 0;
    }
}
