package store;

import commons.BIT;
import generated.enums.DATATYPE;
import generated.lists.FlagStats;

import java.util.ArrayList;

public class StoreDiscrete extends StoreBase {
    protected final Store.CompositeCalculations calc;
    protected final int[] store;
    protected int seekRow, seekCol;
    protected ItrStore itrInstance;

    public StoreDiscrete(Store.CompositeCalculations calc, DATATYPE datatype) {
        super(datatype);
        this.calc = calc;
        store = calc.compositeStore;
    }

    /**Reads indexing info at left side of int word and sets seek variable(s)
     *
     * @param enu a generated integer enum: see class UqDiscreteGen */
    protected void seek(int enu){
        seekRow = (enu >> calc.rowStart) & calc.rowMask;
        seekCol = (enu >> calc.colStart) & calc.colMask;
//        System.out.println("discrete: seekRow=" + seekRow);
//        System.out.println("discrete: seekCol=" + seekCol);
    }

    @Override
    public void set(int enu){
        seek(enu);
        int posMask = calc.valMask << (seekCol * calc.wval);
//        System.out.println("posMask=" + posMask);
        store[seekRow] &= ~posMask;         // drop old value
        store[seekRow] |= (posMask & enu);  // add new
    }

    @Override
    public void set(int enu, String val) {}

    @Override
    public void set(int enu, int val) {}

    @Override
    public void drop(int enu){
        seek(enu);
        int posMask = calc.valMask << (seekCol * calc.wval);
        store[seekRow] &= ~posMask;
    }

    @Override
    public boolean getBoolean(int enu){
        seek(enu);
        int posMask = calc.valMask << (seekCol * calc.wval);
        int state = (store[seekRow] & posMask);
        return state != 0 && state == (enu & posMask);//false if not set or != enu
    }

    @Override
    public int getNumber(int enu){// value only, right shifted to true value
        seek(enu);
        int target = store[seekRow];
        int out = (target >> (seekCol * calc.wval)) & calc.valMask;
        return out;
    }

    @Override
    public String getString(int enu) {
        return null;
    }

    @Override
    public Object get(int enu) {
        return getNumber(enu);
    }

    @Override
    public int getState(int enu){   // in-place value only
        seek(enu);
        return (seekRow << calc.rowStart) | (store[seekRow] & (calc.valMask << (seekCol * calc.wval)));
    }

    @Override
    public int numNonZero(int enu) {
        return anyNonZero(enu)? 1 : 0;
    }

    @Override
    public boolean anyNonZero(int enu) {
        seek(enu);
        int posMask = calc.valMask << (seekCol * calc.wval);
        return (store[seekRow] & posMask) > 0;
    }

    @Override
    public boolean haveData() {
        ItrStore itr = this.getStoreItr();
        while(itr.hasNext()){
            if(itr.nextNumber() != 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public void dispStore(){
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
            return (itrInstance = new ItrDiscrete(startEnu, stopEnu, calc));
        }
        else{
            itrInstance.rewind(startEnu, stopEnu);
            return itrInstance;
        }
    }

    public void assertSeek(int integer){
        seekRow = (integer >> calc.rowStart);
        seekCol = (integer >> calc.colStart) & calc.colMask;
        if(seekRow >= store.length){
            throw new ArrayIndexOutOfBoundsException("Row out of range: "+ BIT.str(integer));
        }
        if(seekCol >= (calc.colStart/calc.wval)){
            throw new ArrayIndexOutOfBoundsException("Col out of range: "+ BIT.str(integer));
        }
    }

    public static class ItrDiscrete implements ItrStore {
        protected final Store.CompositeCalculations calc;
        protected final int[] store;
        protected final int numCols;
        protected int rowStop, colStop;
        protected int rowCurr, colCurr;

        public ItrDiscrete(int startEnu, int stopEnu, Store.CompositeCalculations calc) {
            this.calc = calc;
            numCols = calc.colStart/calc.wval;
            store = calc.compositeStore;
            rewind(startEnu, stopEnu);
        }

        @Override
        public void rewind(int startEnu, int stopEnu) {
            rowCurr = (startEnu >> calc.rowStart) & calc.rowMask;
            colCurr = (startEnu >> calc.colStart) & calc.colMask;
            rowStop = (stopEnu >> calc.rowStart) & calc.rowMask;
            colStop = (stopEnu >> calc.colStart) & calc.colMask;
            //System.out.printf("Rewind... start(%d:%d)  stop(%d:%d) \n", rowCurr, colCurr, rowStop, colStop);
        }

        @Override
        public int nextKey() {
            return (rowCurr << calc.rowStart) | (colCurr << calc.colStart) | (1 << (colCurr * calc.wval));
        }

        @Override
        public boolean hasNext() {
            return rowCurr < rowStop || colCurr <= colStop;
        }

        @Override
        public boolean nextBoolean() {
            return this.nextNumber() != 0;
        }

        @Override
        public int nextNumber() {
            int shift = colCurr * calc.wval;
            int valCurr = (store[rowCurr] >> shift) & calc.valMask;

            //System.out.printf("rowCurr=%d, colCurr=%d, valCurr=%d \n", rowCurr, colCurr, valCurr);
            //System.out.printf("%d:%d: %s \n", rowCurr, colCurr, BIT.str(store[rowCurr]));
            colCurr++;
            if(colCurr == numCols && rowCurr < rowStop){
                rowCurr++;
                colCurr = 0;
            }
            return valCurr;
        }

        @Override
        public String nextString() {
            int r = rowCurr, c = colCurr, val = store[rowCurr];
            return String.format("%d:%d: val %d %s \n", r, c, nextNumber(), BIT.str(val));
            //return BIT.str(nextState());
        }

        @Override
        public int nextState() {
            int shift = colCurr * calc.wval;
            int mask =  calc.valMask << shift;
            int valCurr = (store[rowCurr] & mask) | (rowCurr << calc.rowStart);
            colCurr++;
            if(colCurr == numCols && rowCurr < rowStop){
                rowCurr++;
                colCurr = 0;
            }
            return valCurr;
        }

        @Override
        public Object nextObject() {
            return nextNumber();
        }
    }
}
