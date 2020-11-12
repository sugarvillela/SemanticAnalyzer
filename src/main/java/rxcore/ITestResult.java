package rxcore;

public interface ITestResult extends Comparable<ITestResult>{
    int[] getBestMap();
    int getBestScore();
    int getStartIndex();
}
