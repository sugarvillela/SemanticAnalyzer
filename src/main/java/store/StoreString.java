package store;

import commons.Commons;
import generated.lists.FlagStats;
import generated.lists.ListString;

import java.util.Arrays;

import static generated.code.DATATYPE.LIST_STRING;

public class StoreString implements IStore {
    private final String[] store;
    private final int offset, size;

    public StoreString() {
        size = FlagStats.getSize(LIST_STRING);
        offset = ListString.offset();
        store = new String[size];
    }

    @Override
    public void set(int enu) {
        throw new IllegalStateException("Dev err");
    }

    @Override
    public void set(int enu, String val) {
        store[enu - offset] = val;
    }

    @Override
    public void set(int enu, int val) {
        throw new IllegalStateException("Dev err");
        //store[enu - offset] = String.valueOf(val);
    }

    @Override
    public void drop(int enu) {
        store[enu - offset] = null;
    }

    @Override
    public boolean getBoolean(int enu) {
        return store[enu - offset] != null;
    }

    @Override
    public int getNumber(int enu) {
        throw new IllegalStateException("Dev err");
        //return 0;
    }

    @Override
    public String getString(int enu) {
        return store[enu - offset];
    }

    @Override
    public Object get(int enu) {
        return getString(enu);
    }

    @Override
    public int getState(int enu) {
        throw new IllegalStateException("Dev err");
        //return 0;
    }

    @Override
    public int numNonZero(int enu) {
        int count = 0;
        for(int i = 0; i < size; i++){
            if(store[i] != null){
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean anyNonZero(int enu) {
        for(int i = 0; i < size; i++){
            if(store[i] != null){
                return true;
            }
        }
        return false;
    }

    @Override
    public void disp() {
        //Commons.disp(store, "StoreString");
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "StoreString{" +
                "\n\tstore=" + Arrays.toString(store) +
                "\n\toffset=" + offset +
                "\n\tsize=" + size +
                "\n}";
    }
}
