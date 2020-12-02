package store;

import commons.BIT;

public class StoreBoolean implements IStore {
    private final int[] store;
    private final int wrow;
    private final int rowMask, dataMask;
    private final int rowStart;
    private int seekRow;

    public StoreBoolean(Store.CompositeCalculations calc) {
        wrow = calc.wrow;
        store = calc.compositeStore;
        rowStart = calc.rowStart;
        rowMask = calc.rowMask;
        dataMask = calc.dataMask;
    }

    /**Reads indexing info at left side of int word and sets seek variable(s)
     * Implented only in the 'composite' stores: StoreBoolean and StoreDiscrete
     * @param enu a generated integer enum: see class UqDiscreteGen */
    private void seek(int enu){
        seekRow = (enu >> rowStart) & rowMask;
//        System.out.println("boolean: seekRow=" + seekRow);
    }

    @Override
    public void set(int enu){
        seek(enu);
        store[seekRow] |= (enu & dataMask);
    }

    @Override
    public void set(int enu, String val) {}

    @Override
    public void set(int enu, int val) {}

    @Override
    public void drop(int enu){
        seek(enu);
        store[seekRow] &= ~(enu & dataMask);
    }

    @Override
    public boolean getBoolean(int enu){// most appropriate for this datatype
        seek(enu);
        //System.out.println("isSet: ")
        return (store[seekRow] & enu & dataMask) > 0;
    }

    @Override
    public int getState(int enu) {
        seek(enu);
        return store[seekRow] & enu;
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
        for(int i =  0; i < rowStart; i++){
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
        return (store[seekRow] & dataMask) > 0;
    }

    @Override
    public void disp() {
        System.out.println("Store Boolean Display");
        for(int i = 0; i < store.length; i++){
            System.out.printf("%02d: %s \n", i, BIT.str(store[i]));
        }
        System.out.println("======================");
    }
}
