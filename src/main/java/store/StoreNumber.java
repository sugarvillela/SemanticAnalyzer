package store;

import commons.Commons;
import generated.lists.FlagStats;
import generated.lists.ListNumber;

import java.util.Arrays;

import static generated.code.DATATYPE.LIST_NUMBER;

public class StoreNumber implements IStore {
    private final int[] store;
    private final int offset, size;

    public StoreNumber() {
        size = FlagStats.getSize(LIST_NUMBER);
        offset = ListNumber.offset();
        store = new int[size];
        for(int i = 0; i < size; i++){
            store[i] = -1;
        }
    }

    @Override
    public void set(int enu) {}

    @Override
    public void set(int enu, String val) {
        throw new IllegalStateException("Dev err");
    }

    @Override
    public void set(int enu, int val) {
        store[enu - offset] = val;
    }

    @Override
    public void drop(int enu) {
        store[enu - offset] = -1;
    }

    @Override
    public boolean getBoolean(int enu) {
        return store[enu - offset] != -1;
    }

    @Override
    public int getNumber(int enu) {
        return store[enu - offset];
    }

    @Override
    public String getString(int enu) {
        throw new IllegalStateException("Dev err");
        //return null;
    }

    @Override
    public Object get(int enu) {
        return getNumber(enu);
    }

    @Override
    public int getState(int enu) {
        return store[enu - offset];
    }

    @Override
    public int numNonZero(int enu) {
        int count = 0;
        for(int i = 0; i < size; i++){
            if(store[i] != -1){
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean anyNonZero(int enu) {
        for(int i = 0; i < size; i++){
            if(store[i] != -1){
                return true;
            }
        }
        return false;
    }

    @Override
    public void disp() {
        //Commons.disp(store, "StoreNumber");
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "StoreNumber{" +
                "\n\tstore=" + Arrays.toString(store) +
                "\n\toffset=" + offset +
                "\n\tsize=" + size +
                "\n}";
    }
}
