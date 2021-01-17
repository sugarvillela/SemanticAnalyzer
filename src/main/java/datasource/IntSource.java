package datasource;

import java.util.Arrays;

public class IntSource {
    private final int[] data;
    private int row;

    public IntSource(int[] data) {
        this.data = data;
        row = 0;
    }

    public int getRow(){
        return this.row;
    }

    public void rewind(){
        row = 0;
    }

    public boolean hasNext(){
        return row < data.length;
    }

    public int next(){
        return (row < data.length)? data[row++] : -1;
    }

    @Override
    public String toString() {
        return "IntSource{" + Arrays.toString(data) + '}';
    }
}
