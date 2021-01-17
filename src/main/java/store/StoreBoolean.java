package store;

import commons.BIT;
import generated.enums.DATATYPE;

import java.util.ArrayList;

public class StoreBoolean extends StoreBase {
    private final Store.CompositeCalculations calc;
    private final int[] store;
    private int seekRow;
    private ItrStore itrInstance;

    public StoreBoolean(Store.CompositeCalculations calc, DATATYPE datatype) {
        super(datatype);
        this.calc = calc;
        store = calc.compositeStore;
    }

    /**Reads indexing info at left side of int word and sets seek variable(s)
     * Implented only in the 'composite' stores: StoreBoolean and StoreDiscrete
     * @param enu a generated integer enum: see class UqDiscreteGen */
    private void seek(int enu){
        seekRow = (enu >> calc.rowStart) & calc.rowMask;
//        System.out.println("boolean: seekRow=" + seekRow);
    }

    @Override
    public void set(int enu){
        seek(enu);
        store[seekRow] |= (enu & calc.dataMask);
    }

    @Override
    public void set(int enu, String val) {}

    @Override
    public void set(int enu, int val) {}

    @Override
    public void drop(int enu){
        seek(enu);
        store[seekRow] &= ~(enu & calc.dataMask);
    }

    @Override
    public boolean getBoolean(int enu){// most appropriate for this datatype
        seek(enu);
        //System.out.println("isSet: ")
        return (store[seekRow] & enu & calc.dataMask) > 0;
    }

    @Override
    public int getState(int enu) {
        seek(enu);
        return (seekRow << calc.rowStart) | (store[seekRow] & enu);
    }

    @Override
    public int getNumber(int enu) {
        return (getBoolean(enu))? 1 : 0;
    }

    @Override
    public String getString(int enu) {
        return null;
    }

    @Override
    public Object get(int enu) {
        return getBoolean(enu);
    }

    @Override
    public int numNonZero(int enu) {
        seek(enu);
        int data = store[seekRow], count = 0;
        for(int i =  0; i < calc.rowStart; i++){
            if((data & 1) == 1){
                count++;
            }
            data >>= 1;
        }
        return count;
    }

    @Override
    public boolean anyNonZero(int enu) {
        seek(enu);
        return (store[seekRow] & calc.dataMask) > 0;
    }

    @Override
    public boolean haveData() {
        return false;
    }

    @Override
    public void dispStore() {
        ItrStore itr = this.getStoreItr();
        ArrayList<String> out = new ArrayList<>();
        int i = 0, nextNumber;
        while(itr.hasNext()){
            if((nextNumber = itr.nextNumber()) != 0){
                out.add(String.format("    %02d: %d", i, nextNumber));
            }
            //out.add(String.format("    %02d: %X", i, itr.nextState()));
            //out.add(String.format("    %02d: %d", i, itr.nextNumber()));
            i++;
        }
        if(out.isEmpty()){
            System.out.println("  " + this.datatype.toString() + "{ empty }");
        }
        else{
            System.out.println("  " + this.datatype.toString() + "{");
            System.out.println(String.join("\n", out));
            System.out.println("  }");
        }
    }

    @Override
    public ItrStore getStoreItr(int startEnu, int stopEnu) {
        if(itrInstance == null){
            return (itrInstance = new ItrBoolean(startEnu, stopEnu, calc));
        }
        else{
            itrInstance.rewind(startEnu, stopEnu);
            return itrInstance;
        }
    }

    public static class ItrBoolean implements ItrStore {
        protected final Store.CompositeCalculations calc;
        protected final int[] store;
        protected final int numCols;
        protected int rowStop, maskStop;
        protected int rowCurr, maskCurr;

        public ItrBoolean(int startEnu, int stopEnu, Store.CompositeCalculations calc) {
            this.calc = calc;
            numCols = calc.rowStart;
            //System.out.println("ItrBoolean construct: numCols" + numCols);
            store = calc.compositeStore;
            rewind(startEnu, stopEnu);
        }

        @Override
        public void rewind(int startEnu, int stopEnu) {
            rowCurr = (startEnu >> calc.rowStart) & calc.rowMask;
            maskCurr = startEnu & calc.dataMask;
            rowStop = (stopEnu >> calc.rowStart) & calc.rowMask;
            maskStop = stopEnu & calc.dataMask;
            //System.out.printf("rowCurr=%X, maskCurr=%X, rowStop=%X, maskStop=%X\n", rowCurr, maskCurr, rowStop, maskStop);
        }

        @Override
        public int nextKey() {
            return (rowCurr << calc.rowStart) | maskCurr;
        }

        @Override
        public boolean hasNext() {
            return rowCurr < rowStop || maskCurr <= maskStop;
        }

        @Override
        public boolean nextBoolean() {
            boolean valCurr = (store[rowCurr] & maskCurr) != 0;
            //System.out.printf("rowCurr=%X, maskCurr=%X, valCurr=%b \n", rowCurr, maskCurr, valCurr);
            //BIT.disp(maskCurr);
            maskCurr <<= 1;
            if(maskCurr == (1 << numCols) && rowCurr < rowStop){
                rowCurr++;
                maskCurr = 1;
            }
            return valCurr;
        }

        @Override
        public int nextNumber() {
            return this.nextBoolean()? 1 : 0;
        }

        @Override
        public String nextString() {
            return BIT.str(nextState());
        }

        @Override
        public int nextState() {
            int stateCurr = (store[rowCurr] & maskCurr) | (rowCurr << calc.rowStart);
            maskCurr <<= 1;
            if(maskCurr == (1 << numCols) && rowCurr < rowStop){
                rowCurr++;
                maskCurr = 1;
            }
            return stateCurr;
        }

        @Override
        public Object nextObject() {
            return nextBoolean();
        }
    }
}
