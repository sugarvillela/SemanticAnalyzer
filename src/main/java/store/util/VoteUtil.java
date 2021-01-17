package store.util;

import store.IStore;
import store.Store;

public class VoteUtil {
    protected final Store store;
    int bestScore, bestEnu;
    boolean tie;
    int[] ties;

    public VoteUtil(Store store) {
        this.store = store;
    }

    public boolean tallyVotes(int baseIndex){
        return false;
    }

    public boolean isTie(){
        return tie;
    }

    public int getBestScore(){
        return bestScore;
    }

    public int getBestEnu(){
        return bestEnu;
    }

    public int[] getTies(){
        return ties;
    }

    public boolean tallyVotes(int enuStart, int enuStop){
        bestScore = -1;
        tie = false;
        IStore.ItrStore itr = store.getStore(enuStart).getStoreItr(enuStart, enuStop);
        while(itr.hasNext()){
            int currEnu = itr.nextKey();
            int currVal = itr.nextNumber();

            if(currVal > bestScore){
                bestScore = currVal;
                bestEnu = currEnu;
                tie = false;
            }
            else if(currVal == bestScore && currVal != 0){
                tie = true;
            }
        }

        if(tie){
            handleTie(itr, enuStart, enuStop);
        }
        else{
            ties = null;
        }

        return tie;
    }

    private void handleTie(IStore.ItrStore itr, int enuStart, int enuStop){
        int size = 0;

        itr.rewind(enuStart, enuStop);
        while(itr.hasNext()){
            if(itr.nextNumber() == bestScore){
                size++;
            }
        }
        ties = new int[size];

        int i = 0;
        itr.rewind(enuStart, enuStop);
        while(itr.hasNext()){
            int currEnu = itr.nextKey();
            if(itr.nextNumber() == bestScore){
                ties[i++] = currEnu;
            }
        }
    }
}
