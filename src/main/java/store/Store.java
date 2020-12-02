package store;


import commons.BIT;
import generated.code.DATATYPE;
import generated.lists.FlagStats;

import java.util.Arrays;

import static generated.code.DATATYPE.*;

public class Store implements IStore {
    private final IStore[] storeList;
    private boolean byRange; // TODO future or delete

    public Store() {
        storeList = new StoreInit().getStore();
    }
    public IStore getStore(DATATYPE datatype){
        return storeList[datatype.ordinal()];
    }

    private DATATYPE datatypeByRange(int enu){
        try{
            byRange = true;
            return FlagStats.flagTypeByRange(enu);
        }catch(IllegalStateException e){
            byRange = false;
            return FlagStats.flagTypeByBaseIndex(enu);
        }
    }
    private DATATYPE datatypeByBaseIndex(int enu){
        try{
            byRange = false;
            return FlagStats.flagTypeByBaseIndex(enu);
        }catch(IllegalStateException e){
            byRange = true;
            return FlagStats.flagTypeByRange(enu);
        }
    }
    @Override
    public void set(int enu) {
        DATATYPE datatype = datatypeByRange(enu);
        System.out.printf("set com: %s: %X\n", datatype, enu);
        storeList[datatype.ordinal()].set(enu);
    }

    @Override
    public void set(int enu, String val) {
        DATATYPE datatype = FlagStats.flagTypeByRange(enu);
        System.out.printf("set str: %s: %X\n", datatype, enu);
        storeList[datatype.ordinal()].set(enu, val);
    }

    @Override
    public void set(int enu, int val) {
        DATATYPE datatype = FlagStats.flagTypeByRange(enu);
        System.out.printf("set int: %s: %X\n", datatype, enu);
        storeList[datatype.ordinal()].set(enu, val);
    }

    @Override
    public void drop(int enu) {
        DATATYPE datatype = FlagStats.flagTypeByRange(enu);
        storeList[datatype.ordinal()].drop(enu);
    }

    @Override
    public boolean getBoolean(int enu){
        DATATYPE datatype = FlagStats.flagTypeByRange(enu);
        return storeList[datatype.ordinal()].getBoolean(enu);
    }

    @Override
    public int getNumber(int enu){
        DATATYPE datatype = FlagStats.flagTypeByRange(enu);
        return storeList[datatype.ordinal()].getNumber(enu);
    }

    @Override
    public String getString(int enu){
        DATATYPE datatype = FlagStats.flagTypeByRange(enu);
        return storeList[datatype.ordinal()].getString(enu);
    }

    @Override
    public Object get(int enu){
        DATATYPE datatype = FlagStats.flagTypeByRange(enu);
        return storeList[datatype.ordinal()].get(enu);
    }

    @Override
    public int getState(int enu) {
        DATATYPE datatype = FlagStats.flagTypeByRange(enu);
        return storeList[datatype.ordinal()].getState(enu);
    }

    @Override
    public int numNonZero(int enu) {
        DATATYPE datatype = this.datatypeByBaseIndex(enu);
        return storeList[datatype.ordinal()].numNonZero(enu);
    }

    @Override
    public boolean anyNonZero(int enu) {
        DATATYPE datatype = this.datatypeByBaseIndex(enu);
        return storeList[datatype.ordinal()].anyNonZero(enu);
    }

    @Override
    public void disp() {
        for(IStore iStore : storeList){
            iStore.disp();
        }
    }

    public void get(int enu, Object caller, Object visitor){
        DATATYPE datatype = FlagStats.flagTypeByRange(enu);
        switch (datatype){
            case LIST_DISCRETE:
            case LIST_NUMBER:
                int n = storeList[LIST_NUMBER.ordinal()].getNumber(enu);
                //visitor.call(n, caller)
                break;
            case LIST_BOOLEAN:
                boolean b = storeList[LIST_NUMBER.ordinal()].getBoolean(enu);
                //visitor.call(b, caller)
                break;
            case LIST_STRING:
                String s = storeList[LIST_STRING.ordinal()].getString(enu);
                //visitor.call(b, caller)
                break;
        }
    }

    public static class CompositeCalculations {// Do calc once and use for both Discrete and Boolean impl
        public final int wrow, wcol, wval;
        public final int rowMask, colMask, dataMask, valMask;
        public final int rowStart, colStart;
        public final int[] compositeStore;

        public CompositeCalculations(){
            wrow = FlagStats.getWRow();
            wcol = FlagStats.getWCol();
            wval = FlagStats.getWVal();

            rowStart = Integer.SIZE - wrow;
            colStart = Integer.SIZE - wrow - wcol;

            rowMask = (1 << wrow) - 1;
            colMask = (1 << wcol) - 1;
            dataMask = (1 << colStart) - 1;
            valMask = (1 << wval) - 1;

            int compositeSize = (FlagStats.getHighIndex(DATATYPE.LIST_BOOLEAN) >> rowStart) + 1;
            compositeStore = new int[compositeSize];
        }

        @Override
        public String toString() {
            return "CompositeStoreCalculations{" +
                    "\n\twrow=" + wrow +
                    "\n\twcol=" + wcol +
                    "\n\twval=" + wval +
                    "\n\trowStart=" + rowStart +
                    "\n\tcolStart=" + colStart +
                    "\n\trowMask=" + BIT.str(rowMask) +
                    "\n\tcolMask=" + BIT.str(colMask) +
                    "\n\tdataMask=" + BIT.str(dataMask) +
                    "\n\tvalMask=" + BIT.str(valMask) +
                    "\n\tcompositeStore=" + Arrays.toString(compositeStore) +
                    "\n}";
        }

        public void disp(){
            System.out.println(this.toString());
        }
    }

    private static class StoreInit{
        private final IStore[] storeList;

        private StoreInit() {
            final int NUM_STORES = DATATYPE.values().length;
            storeList = new IStore[NUM_STORES];

            CompositeCalculations compositeCalculations = new CompositeCalculations();
            compositeCalculations.disp();
            storeList[LIST_STRING.ordinal()] =   new StoreString();
            storeList[LIST_NUMBER.ordinal()] =   new StoreNumber();
            storeList[LIST_DISCRETE.ordinal()] = new StoreDiscrete(compositeCalculations);
            storeList[LIST_BOOLEAN.ordinal()] =  new StoreBoolean(compositeCalculations);
        }
        public IStore[] getStore(){
            return storeList;
        }
    }
}
