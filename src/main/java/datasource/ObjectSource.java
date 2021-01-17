package datasource;

import java.util.Arrays;

public class ObjectSource {
    private final Object[] data;
    private int row;

    public ObjectSource(Object[] data) {
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

    public Object next(){
        return (row < data.length)? data[row++] : null;
    }

    @Override
    public String toString() {
        return "ObjectSource{" + Arrays.toString(data) + '}';
    }
}
