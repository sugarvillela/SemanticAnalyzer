package store;


import commons.BIT;
import generated.enums.DATATYPE;
import generated.lists.FlagStats;
import rxword.interfaces.RxFun;
import store.util.VoteUtil;

import java.util.Arrays;

import static generated.enums.DATATYPE.*;

public class Store implements IStore {
    private final IStore[] storeList; // list of stores by datatype ordinal

    public Store() {
        storeList = new StoreInit().getStoreList();
    }

    public IStore getStore(int enu){
        DATATYPE datatype = getDatatype(enu);
        return storeList[datatype.ordinal()];
    }

    public IStore getStore(DATATYPE datatype){
        return storeList[datatype.ordinal()];
    }

    public VoteUtil getVoteUtil(){
        return new VoteUtil(this);
    }

    private DATATYPE getDatatype(int enu){
        try{
            return FlagStats.flagTypeByRange(enu);
        }catch(IllegalStateException e0){
            try{
                return FlagStats.flagTypeByBaseIndex(enu);
            }catch(IllegalStateException e1){
                throw new IllegalStateException(e1);
            }
        }
    }

    @Override
    public void set(int enu) {
        DATATYPE datatype = getDatatype(enu);
        //System.out.printf("set com: %s: %X\n", datatype, enu);
        storeList[datatype.ordinal()].set(enu);
    }

    @Override
    public void set(int enu, String val) {
        DATATYPE datatype = this.getDatatype(enu);
        //System.out.printf("set str: %s: %X\n", datatype, enu);
        storeList[datatype.ordinal()].set(enu, val);
    }

    @Override
    public void set(int enu, int val) {
        DATATYPE datatype = this.getDatatype(enu);
        //System.out.printf("set int: %s: %X\n", datatype, enu);
        storeList[datatype.ordinal()].set(enu, val);
    }

    @Override
    public void drop(int enu) {
        DATATYPE datatype = this.getDatatype(enu);
        storeList[datatype.ordinal()].drop(enu);
    }

    @Override
    public boolean getBoolean(int enu){
        DATATYPE datatype = this.getDatatype(enu);
        return storeList[datatype.ordinal()].getBoolean(enu);
    }

    @Override
    public int getNumber(int enu){
        DATATYPE datatype = this.getDatatype(enu);
        return storeList[datatype.ordinal()].getNumber(enu);
    }

    @Override
    public String getString(int enu){
        DATATYPE datatype = this.getDatatype(enu);
        return storeList[datatype.ordinal()].getString(enu);
    }

    @Override
    public Object get(int enu){
        DATATYPE datatype = this.getDatatype(enu);
        return storeList[datatype.ordinal()].get(enu);
    }

    @Override
    public int getState(int enu) {
        DATATYPE datatype = this.getDatatype(enu);
        return storeList[datatype.ordinal()].getState(enu);
    }

    @Override
    public int numNonZero(int enu) {
        DATATYPE datatype = this.getDatatype(enu);
        return storeList[datatype.ordinal()].numNonZero(enu);
    }

    @Override
    public boolean anyNonZero(int enu) {
        DATATYPE datatype = this.getDatatype(enu);
        return storeList[datatype.ordinal()].anyNonZero(enu);
    }

    @Override
    public boolean haveData() {
        for(IStore iStore : storeList){
            if(iStore.haveData()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void dispStore() {
        System.out.println(this.getClass().getSimpleName() + "{");
//        this.getStore(LIST_STRING).dispStore();
//        this.getStore(LIST_NUMBER).dispStore();
//        this.getStore(LIST_VOTE).dispStore();
//        this.getStore(LIST_BOOLEAN).dispStore();
        for(IStore iStore : storeList){
            iStore.dispStore();
        }
        System.out.println("}");
    }

    @Override
    public ItrStore getStoreItr() {
        return null;
    }

    @Override
    public ItrStore getStoreItr(int startEnu, int stopEnu) {
        DATATYPE datatype = this.getDatatype(startEnu);
        return this.getStore(datatype).getStoreItr(startEnu, stopEnu);
    }

    public void request(int enu, RxFun visitor){//not used...
        DATATYPE datatype = this.getDatatype(enu);
        switch (datatype){
            case LIST_DISCRETE:
            case LIST_NUMBER:
            case LIST_VOTE:
                int n = storeList[LIST_NUMBER.ordinal()].getNumber(enu);
                visitor.set(n);
                break;
            case LIST_BOOLEAN:
                boolean b = storeList[LIST_NUMBER.ordinal()].getBoolean(enu);
                visitor.set(b);
                break;
            case LIST_STRING:
                String s = storeList[LIST_STRING.ordinal()].getString(enu);
                visitor.set(s);
                break;
        }
    }

    public static class CompositeCalculations {// Do calc once for all composite implementations
        public final int wrow, wcol, wval;
        public final int rowMask, colMask, dataMask, valMask;
        public final int rowStart, colStart;
        public final int[] compositeStore; // underlying array shared by composite stores

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
            // System.out.println("compositeSize: " + compositeSize);
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
            final int NUM_STORES = 6;
            storeList = new IStore[NUM_STORES];

            CompositeCalculations compositeCalculations = new CompositeCalculations();
            //compositeCalculations.disp();
            storeList[LIST_STRING.ordinal()] =   new StoreString(LIST_STRING);
            storeList[LIST_NUMBER.ordinal()] =   new StoreNumber(LIST_NUMBER);
            storeList[LIST_DISCRETE.ordinal()] = new StoreDiscrete(compositeCalculations, LIST_DISCRETE);
            storeList[LIST_SCOPES.ordinal()] =   new StoreDiscrete(compositeCalculations, LIST_SCOPES);
            storeList[LIST_VOTE.ordinal()] =     new StoreVote(compositeCalculations, LIST_VOTE);
            storeList[LIST_BOOLEAN.ordinal()] =  new StoreBoolean(compositeCalculations, LIST_BOOLEAN);

        }
        public IStore[] getStoreList(){
            return storeList;
        }
    }
}
