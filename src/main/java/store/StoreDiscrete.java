package store;

import commons.BIT;

public class StoreDiscrete implements IStore {
    private final int[] store;
    private final int wrow, wcol, wval;
    private final int rowMask, colMask, dataMask, valMask;
    private final int rowStart, colStart;
    private int seekRow, seekCol;

    public StoreDiscrete(Store.CompositeCalculations calc) {
        wrow = calc.wrow;
        wcol = calc.wcol;
        wval = calc.wval;

        rowStart = calc.rowStart;
        colStart = calc.colStart;

        rowMask = calc.rowMask;
        colMask = calc.colMask;
        dataMask = calc.dataMask;
        valMask = calc.valMask;

        store = calc.compositeStore;
    }

    /**Reads indexing info at left side of int word and sets seek variable(s)
     * Implented only in the 'composite' stores: StoreBoolean and StoreDiscrete
     * @param enu a generated integer enum: see class UqDiscreteGen */
    private void seek(int enu){
        seekRow = (enu >> rowStart) & rowMask;
        seekCol = (enu >> colStart) & colMask;
//        System.out.println("discrete: seekRow=" + seekRow);
//        System.out.println("discrete: seekCol=" + seekCol);
    }

    @Override
    public void set(int enu){
        seek(enu);
        int posMask = valMask << (seekCol*wval);
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
        int posMask = valMask << (seekCol*wval);
        store[seekRow] &= ~posMask;
    }

    @Override
    public boolean getBoolean(int enu){
        seek(enu);
        int posMask = valMask << (seekCol*wval);
        return (store[seekRow] & posMask) == (enu & posMask);
    }

    @Override
    public int getNumber(int enu){// value only, right shifted to true value
        seek(enu);
        int target = store[seekRow];
        int out = (target >> (seekCol*wval)) & valMask;
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
        return store[seekRow] & (valMask << (seekCol*wval));
    }

    @Override
    public int numNonZero(int enu) {
        return anyNonZero(enu)? 1 : 0;
    }

    @Override
    public boolean anyNonZero(int enu) {
        seek(enu);
        int posMask = valMask << (seekCol*wval);
        return (store[seekRow] & posMask) > 0;
    }

    @Override
    public void disp(){
        System.out.println("Store Discrete Display");
        for(int i = 0; i < store.length; i++){
            System.out.printf("%02d: %s \n", i, BIT.str(store[i]));
        }
        System.out.println("======================");
    }

    public void assertSeek(int integer){
        seekRow = (integer >> rowStart);
        seekCol = (integer >> colStart) & colMask;
        if(seekRow >= store.length){
            throw new ArrayIndexOutOfBoundsException("Row out of range: "+ BIT.str(integer));
        }
        if(seekCol >= (colStart/wval)){
            throw new ArrayIndexOutOfBoundsException("Col out of range: "+ BIT.str(integer));
        }
    }
}
