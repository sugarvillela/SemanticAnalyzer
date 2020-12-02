package search;

import commons.BIT;

import java.util.Arrays;

public class BloomStore {
    private final BitStore bitStore;

    public BloomStore(int storeSize) {
        bitStore = new BitStore(storeSize);
    }

    public BloomStore(long[] store) {
        bitStore = new BitStore(store);
    }

    public void set(String text){
        int[] hashes = HashFactory.hashAll(text);
        for(int hash : hashes){
            bitStore.set(hash);
        }
    }

    public boolean get(String text){
        int[] hashes = HashFactory.hashAll(text);
        for(int hash : hashes){
            if(!bitStore.get(hash)){
                return false;
            }
        }
        return true;
    }

    public void set(int[] hashes){
        for(int hash : hashes){
            bitStore.set(hash);
        }
    }

    public boolean get(int[] hashes){
        for(int hash : hashes){
            if(!bitStore.get(hash)){
                return false;
            }
        }
        return true;
    }

    public long[] getStore(){
        return bitStore.getStore();
    }

    public void clear(){
        bitStore.clear();
    }

    public void disp(){
        System.out.println(bitStore);
    }

    private static class BitStore {
        private final int wordLen;
        private final int storeSize;
        private final long[] store;

        public BitStore(int storeSize) {
            wordLen = Long.SIZE;
            if(storeSize % wordLen != 0){
                throw new IllegalStateException("Make store size a multiple of " + Long.SIZE);
            }
            this.storeSize = storeSize;
            store = new long[storeSize/wordLen];
        }
        public BitStore(long[] store) {
            wordLen = Long.SIZE;
            this.store = store;
            this.storeSize = store.length * wordLen;
        }

        public void set(int index){
            index = index % storeSize;      //mod for indexes beyond storageSize; creates hash collisions
            int iStorage = index/wordLen;   //index in char array
            int iByte = index % wordLen;    //bit number in char
            //System.out.printf("set: %d: %d: %d: %X\n", index, iStorage, iByte, (1 << iByte) );
            store[iStorage] |= (1L << iByte);
        }

        public boolean get(int index){
            index = index % storeSize;      //mod for indexes beyond storageSize; creates hash collisions
            int iStorage = index/wordLen;   //index in char array
            int iByte = index % wordLen;    //bit number in char
            //System.out.printf("get: %d: %d: %d: %X\n", index, iStorage, iByte, (1 << iByte) );
            return (store[iStorage] & (1L << iByte)) != 0;
        }

        public void clear(){
            Arrays.fill(store, 0);
        }

        public long[] getStore(){
            return store;
        }

        @Override
        public String toString(){
            String[] out = new String[store.length];
            for(int i = 0; i < store.length; i++){
                out[i] = BIT.str(store[i]);
            }
            return String.join("\n", out);
        }

    }
}
