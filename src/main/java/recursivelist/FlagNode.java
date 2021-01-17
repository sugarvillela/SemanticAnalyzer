package recursivelist;

import generated.enums.ACTION;
import generated.lists.ListScopes;
import store.IStore;
import store.Store;

import java.util.ArrayList;

import static generated.lists.ListScopes.SCOPES;
import static generated.lists.ListString.IN;

public class FlagNode extends RecursiveNode implements IStore, IMark {
    private ArrayList<ACTION> marks;

    public FlagNode(){// for testing or top level only; otherwise always set a parent list reference
        super(new SharedExternalState(), new Store());
    }
    public FlagNode(IRecursiveList parentList, int level){
        super(new SharedExternalState(parentList, level), new Store());
    }
    public FlagNode(SharedExternalState sharedExternal){
        super(sharedExternal, new Store());
    }
    public FlagNode(FlagNode sourceNode){
        super(sourceNode);
    }

    @Override
    public String toString() {
//        return String.format("%s: text.in(%s), scope(%s), recursive(%b) \n",
//            this.getClass().getSimpleName(),
//            ((Store)payload).getString(IN),
//            ListScopes.nameByIndex(this.getState(SCOPES)),
//            this.isRecursive()
//        );

//        IRecursiveNode p = getPrev();
//        IRecursiveNode n = getNext();
//        String prev = (p != null && p instanceof FlagNode)? ((FlagNode)p).getString(IN) : "null";
//        String next = (n != null && n instanceof FlagNode)? ((FlagNode)n).getString(IN) : "null";
//        return String.format("(%s | %s | %s) %s",
//                prev, this.getString(IN), next, ListScopes.nameByIndex(this.getState(SCOPES))
//        );

        return String.format("level(%d) scope(%s) (%s) \t parent{%s}",
            this.getLevel(), ListScopes.nameByIndex(this.getState(SCOPES)),this.getString(IN), sharedExternal.getParentList()
        );
    }

    @Override
    public void set(int enu) {
        ((Store)payload).set(enu);
    }

    @Override
    public void set(int enu, String val) {
        ((Store)payload).set(enu, val);
    }

    @Override
    public void set(int enu, int val) {
        ((Store)payload).set(enu, val);
    }

    @Override
    public void drop(int enu) {
        ((Store)payload).drop(enu);
    }

    @Override
    public boolean getBoolean(int enu) {
        return ((Store)payload).getBoolean(enu);
    }

    @Override
    public int getNumber(int enu) {
        return ((Store)payload).getNumber(enu);
    }

    @Override
    public String getString(int enu) {
        return ((Store)payload).getString(enu);
    }

    @Override
    public Object get(int enu) {
        return ((Store)payload).get(enu);
    }

    @Override
    public int getState(int enu) {
        return ((Store)payload).getState(enu);
    }

    @Override
    public int numNonZero(int enu) {
        return ((Store)payload).numNonZero(enu);
    }

    @Override
    public boolean anyNonZero(int enu) {
        return ((Store)payload).anyNonZero(enu);
    }

    @Override
    public boolean haveData() {
        return ((Store)payload).haveData();
    }

    @Override
    public void dispStore(){
        ((Store)payload).dispStore();
    }

    @Override
    public IStore.ItrStore getStoreItr() {
        return ((Store)payload).getStoreItr();
    }

    @Override
    public IStore.ItrStore getStoreItr(int startEnu, int stopEnu) {
        return ((Store)payload).getStoreItr(startEnu, stopEnu);
    }

    /*=====IRecursiveNode methods=====================================================================================*/

    @Override
    public void makeRecursive() {
        this.setChildList(new RecursiveList(sharedExternal));
    }

    @Override
    public IRecursiveNode copyNode() {
        return new FlagNode(this);
    }

    @Override
    public IRecursiveNode newNode(SharedExternalState sharedExternal) {
        return new FlagNode(sharedExternal);
    }

    @Override
    public void dispList(){
        System.out.println(this.toString());
        IRecursiveList childList = this.getChildList();
        if(childList != null){
            childList.dispList();
        }
    }

    /*=====Markable methods=====================================================================================*/

    @Override
    public void mark(ACTION action) {
        if(marks == null){
            marks = new ArrayList<>(4);
        }
        marks.add(action);
    }

    @Override
    public ArrayList<ACTION> getMarkedActions() {
        ArrayList<ACTION> out = marks;
        marks = null;
        return out;
    }

    /*=====FlagNode (non-interface) methods===========================================================================*/

    public FlagNode[] toFlatFlagArray() {
        if(this.isRecursive() && this.size() > 0){
            FlagNode[] out = new FlagNode[this.size()];
            FlagNode s = (FlagNode)this.peekFront();
            int i = 0;
            do{
                out[i++] = s;
                s = (FlagNode)s.getNext();
            }
            while(s != null);
            return out;
        }
        return new FlagNode[0];
    }

    public FlagNode[][][] toBreadthFirstFlagArray() {
        if(this.isRecursive() && this.size() > 0){
            int maxLevel = this.refreshLevels(0);

            FlagNode[][][] mainOut = new FlagNode[maxLevel+1][][];
            mainOut[0] = new FlagNode[1][];
            toBreadthFirstFlagArray(this.getChildList(), mainOut, 0, 0);
            return mainOut;
        }
        return new FlagNode[0][][];
    }

    protected final void toBreadthFirstFlagArray(IRecursiveList mainIn, FlagNode[][][] mainOut, int i, int j) {
        mainOut[i][j] = new FlagNode[mainIn.size()];
        int k;

        ArrayList<FlagNode> recursiveNodes = new ArrayList<>();
        FlagNode s = (FlagNode)mainIn.peekFront();
        k = 0;
        do{
            mainOut[i][j][k] = s;
            if(s.isRecursive() && s.size() > 0){
                recursiveNodes.add(s);
            }
            s = (FlagNode)s.getNext();
            k++;
        }
        while(s != null);

        if(!recursiveNodes.isEmpty()){
            mainOut[i+1] = new FlagNode[recursiveNodes.size()][];
            int j0 = 0;
            for(FlagNode recursiveNode : recursiveNodes){
                IRecursiveList childList = recursiveNode.getChildList();
                this.toBreadthFirstFlagArray(childList, mainOut, i + 1, j0);
                j0++;
            }
        }
    }
}
