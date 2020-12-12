package recursivelist;

import generated.lists.FlagStats;
import generated.lists.ListScopes;
import store.IStore;
import store.Store;

import static generated.code.DATATYPE.LIST_SCOPES;
import static generated.lists.ListScopes.SCOPES;
import static generated.lists.ListScopes.WORD;
import static generated.lists.ListString.IN;

public class FlagNode extends RecursiveNode{
    private final IStore store;

    public FlagNode(){
        store = new Store();
        //store.disp();
    }
    public FlagNode(IRecursiveList childList){
        super(childList);
        store = new Store();
    }

    @Override
    public String toString() {
        return String.format("%s: text.in(%s), scope(%s), recursive(%b) \n",
            this.getClass().getSimpleName(),
            store.getString(IN),
            ListScopes.nameByIndex(this.getState(SCOPES)),
            this.isRecursive());
    }

    @Override
    public void set(int enu) {
        store.set(enu);
    }

    @Override
    public void set(int enu, String val) {
        store.set(enu, val);
    }

    @Override
    public void set(int enu, int val) {
        store.set(enu, val);
    }

    @Override
    public void drop(int enu) {
        store.drop(enu);
    }

    @Override
    public boolean getBoolean(int enu) {
        return store.getBoolean(enu);
    }

    @Override
    public int getNumber(int enu) {
        return store.getNumber(enu);
    }

    @Override
    public String getString(int enu) {
        return store.getString(enu);
    }

    @Override
    public Object get(int enu) {
        return store.get(enu);
    }

    @Override
    public int getState(int enu) {
        return store.getState(enu);
    }

    @Override
    public int numNonZero(int enu) {
        return store.numNonZero(enu);
    }

    @Override
    public boolean anyNonZero(int enu) {
        return store.anyNonZero(enu);
    }

    @Override
    public void disp(){
        System.out.println(this.toString());
        IRecursiveList childList = this.getChildList();
        if(childList != null){
            childList.disp();
        }
    }

    @Override
    public IStore.ItrStore getItr() {
        return store.getItr();
    }

    @Override
    public IStore.ItrStore getItr(int startEnu, int stopEnu) {
        return store.getItr(startEnu, stopEnu);
    }

    /*=====IRecursiveNode methods=====================================================================================*/

    @Override
    public IRecursiveNode copy() {
        return new FlagNode(this.getChildList());
    }

    @Override
    public IRecursiveNode newNode() {
        return new FlagNode();
    }


}
