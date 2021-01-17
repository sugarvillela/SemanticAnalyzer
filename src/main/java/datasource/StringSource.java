package datasource;

import java.util.Arrays;

public class StringSource {
    private final String[] data;
    private int row;

    public StringSource(String[] data) {
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

    public String next(){
        return (row < data.length)? data[row++] : null;
    }

    @Override
    public String toString() {
        return "StringSource{" + Arrays.toString(data) + '}';
    }
}
