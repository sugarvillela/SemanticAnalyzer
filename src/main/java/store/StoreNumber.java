package store;

import generated.enums.DATATYPE;
import generated.lists.FlagStats;
import generated.lists.ListNumber;

import java.util.Arrays;

import static generated.enums.DATATYPE.LIST_NUMBER;

public class StoreNumber extends StoreBase {
    private final int[] store;
    private final int offset, size;
    private ItrStore itrInstance;

    protected StoreNumber(DATATYPE datatype) {
        super(datatype);
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
        return String.valueOf(getNumber(enu));
    }

    @Override
    public Object get(int enu) {
        return (store[enu - offset] == -1)? null : store[enu - offset];
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
        return haveData();
    }

    @Override
    public boolean haveData() {
        for(int i = 0; i < size; i++){
            if(store[i] != -1){
                return true;
            }
        }
        return false;
    }

    @Override
    public void dispStore() {
        if(this.haveData()){
            System.out.println("  " + this.datatype.toString() + "{");
            for(int i = 0; i< store.length; i++){
                if(store[i] != -1){
                    System.out.printf("    %02d: %d \n", i, store[i]);
                }
            }
            System.out.println("  }");
        }
        else{
            System.out.println("  " + this.datatype.toString() + "{ empty }");
        }
    }

    @Override
    public ItrStore getStoreItr(int startEnu, int stopEnu) {
        if(itrInstance == null){
            return (itrInstance = new ItrNumber(store, offset, startEnu, stopEnu));
        }
        else{
            itrInstance.rewind(startEnu, stopEnu);
            return itrInstance;
        }
    }

    @Override
    public String toString() {
        return "StoreNumber{" +
                "\n\tstore=" + Arrays.toString(store) +
                "\n\toffset=" + offset +
                "\n\tsize=" + size +
                "\n}";
    }

    public static class ItrNumber implements ItrStore {
        private final int[] store;
        private final int offset;
        protected int rowCurr, rowStop;

        public ItrNumber(int[] store, int offset, int startEnu, int stopEnu) {
            this.store = store;
            this.offset = offset;
            this.rewind(startEnu, stopEnu);
        }

        @Override
        public void rewind(int startEnu, int stopEnu) {
            rowCurr = startEnu;
            rowStop = stopEnu;
        }

        @Override
        public int nextKey() {
            return rowCurr;
        }

        @Override
        public boolean hasNext() {
            return rowCurr <= rowStop;// && (rowCurr - offset) < store.length; needs to break on bad programming
        }

        @Override
        public boolean nextBoolean() {
            return this.nextNumber() != -1;
        }

        @Override
        public int nextNumber() {
            int value = store[rowCurr - offset];
            rowCurr++;
            return value;
        }

        @Override
        public String nextString() {
            return String.valueOf(this.nextNumber());
        }

        @Override
        public int nextState() {
            return this.nextNumber();
        }

        @Override
        public Object nextObject() {
            int n = this.nextNumber();
            return (n == -1)? null : n;
        }
    }
}
