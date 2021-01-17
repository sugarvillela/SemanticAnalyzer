package recursivelist;

import java.util.ArrayList;

public abstract class RecursiveNode implements IRecursiveNode {
    private IRecursiveNode prev, next;
    private IRecursiveList childList;               // null until set
    protected final Object payload;                 // never null
    protected SharedExternalState sharedExternal;   // never null; enclosed parentList is null unless set

    protected RecursiveNode(SharedExternalState sharedExternal, Object payload) {
        this.sharedExternal = sharedExternal;
        this.payload = payload;
    }

    /** Copy constructor: no links to original?
     *    Since prev and next are copied, the copied node thinks it is in the list
     *    But you cannot reach the copied node from the list,
     *    and you can edit or null the prev and next fields without breaking the list
     *  Object.equals() would return false, except equals() is overridden in the class
     *    Copied nodes test equal because equals() compares payload field
     * @param sourceNode object to be copied */
    protected RecursiveNode(RecursiveNode sourceNode){
        this.childList = (sourceNode.getChildList() == null)? null : sourceNode.getChildList().copyList();
        this.sharedExternal = sourceNode.getSharedExternalState();
        this.payload = sourceNode.payload;
        prev = sourceNode.getPrev();
        next = sourceNode.getNext();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RecursiveNode && this.payload.equals(((RecursiveNode)obj).payload);
    }

    /* =====IRecursiveNode methods for in-node manipulation of prev, next and childList============================== */

    @Override
    public void setNext(IRecursiveNode next) {
        this.next = next;
    }

    @Override
    public IRecursiveNode getNext() {
        return next;
    }

    @Override
    public void setPrev(IRecursiveNode prev) {
        this.prev = prev;
    }

    @Override
    public IRecursiveNode getPrev() {
        return prev;
    }

    @Override
    public void setChildList(IRecursiveList childList){
        //System.out.print(this + ":  ");
        if(sharedExternal.haveParent()){
            System.out.println("have parent, childList "+childList.size());
            childList.setSharedExternalState(sharedExternal);
        }
        else{
            System.out.println("no parent, childList "+childList.size());
            sharedExternal = childList.getSharedExternalState();
        }
        System.out.printf("   level = %d \n",sharedExternal.getLevel());
        //childList.refreshTree();
        this.childList = childList;
    }

    @Override
    public IRecursiveList getChildList(){
        return this.childList;
    }

    @Override
    public IRecursiveList getParentList(){
        return sharedExternal.getParentList();
    }

    @Override
    public boolean isRecursive() {
        return this.childList != null;
    }

    @Override
    public void unlink(){
        next = prev = null;
        childList = null;
    }

    /* =====IRecursiveList methods delegated to childList instance: no null check==================================== */

    @Override
    public void dispList(){
        childList.dispList();
    }

    @Override
    public void dispList(int indent){
        childList.dispList(indent);
    }

    @Override
    public int size() {
        return childList.size();
    }

    @Override
    public int indexOf(IRecursiveNode target) {
        return childList.indexOf(target);
    }

    @Override
    public boolean seek(int index) {
        return childList.seek(index);
    }

    @Override
    public boolean seek(IRecursiveNode target) {
        return childList.seek(target);
    }

    @Override
    public IRecursiveNode peekFront() {
        return childList.peekFront();
    }

    @Override
    public IRecursiveNode peekBack() {
        return childList.popBack();
    }

    @Override
    public IRecursiveNode peekIn(int index) {
        return childList.peekIn(index);
    }

    @Override
    public IRecursiveList peekFront(int hi) {
        return childList.peekFront(hi);
    }

    @Override
    public IRecursiveList peekBack(int lo) {
        return childList.peekBack(lo);
    }

    @Override
    public IRecursiveList peekIn(int lo, int hi) {
        return childList.peekIn(lo, hi);
    }

    @Override
    public IRecursiveList peekFront(IRecursiveNode last) {
        return childList.peekFront(last);
    }

    @Override
    public IRecursiveList peekBack(IRecursiveNode first) {
        return childList.peekBack(first);
    }

    @Override
    public IRecursiveList peekIn(IRecursiveNode first, IRecursiveNode last) {
        return childList.peekIn(first, last);
    }

    @Override
    public void pushFront(IRecursiveNode newHead) {
        childList.pushFront(newHead);
    }

    @Override
    public void pushBack(IRecursiveNode newTail) {
        childList.pushBack(newTail);
    }

    @Override
    public void pushIn(int index, IRecursiveNode newNode) {
        childList.pushIn(index, newNode);
    }

    @Override
    public void pushFront(IRecursiveList newFront) {
        childList.pushFront(newFront);
    }

    @Override
    public void pushBack(IRecursiveList newBack) {
        childList.pushBack(newBack);
    }

    @Override
    public void pushIn(int index, IRecursiveList newIn) {
        childList.pushIn(index, newIn);
    }

    @Override
    public IRecursiveNode popFront() {
        return childList.popFront();
    }

    @Override
    public IRecursiveNode popBack() {
        return childList.popBack();
    }

    @Override
    public IRecursiveNode popIn(int index) {
        return childList.popIn(index);
    }

    @Override
    public IRecursiveList popFront(int hi) {
        return childList.popFront(hi);
    }

    @Override
    public IRecursiveList popBack(int lo) {
        return childList.popBack(lo);
    }

    @Override
    public IRecursiveList popIn(int lo, int hi) {
        return childList.popIn(lo, hi);
    }

    @Override
    public void removeFront(int hi) {
        childList.removeFront(hi);
    }

    @Override
    public void removeBack(int lo) {
        childList.removeBack(lo);
    }

    @Override
    public void removeIn(int lo, int hi) {
        childList.removeIn(lo, hi);
    }

    @Override
    public void setLevel(int level) {
        sharedExternal.setLevel(level);
    }

    @Override
    public int getLevel() {
        return sharedExternal.getLevel();
    }

    @Override
    public void pushBelow(IRecursiveList childList, int... params) {
        this.childList.pushBelow(childList, params);
    }

    @Override
    public IRecursiveList popBelow(int... params) {
        return childList.popBelow(params);
    }

    @Override
    public IRecursiveList newList() {
        return childList.newList();
    }

    @Override
    public IRecursiveList copyList() {
        return null;
    }

    @Override
    public IRecursiveList reverse() {
        return childList.reverse();
    }

    @Override
    public ArrayList<IRecursiveNode> toFlatArrayList() {
        return childList.toFlatArrayList();
    }

    @Override
    public IRecursiveNode[] toFlatArray() {
        return childList.toFlatArray();
    }

    @Override
    public IRecursiveNode[][][] toBreadthFirstArray() {
        return childList.toBreadthFirstArray();
    }

    @Override
    public int refreshLevels() {
        return refreshLevels(0);
    }

    @Override
    public final int refreshLevels(int setLevel){
        if(childList == null){
            this.setLevel(setLevel);
            return setLevel;
        }
        return childList.refreshLevels(setLevel);
    }

    @Override
    public int refreshTree() {
        //System.out.printf("C refreshTree: %s starting=%d \n", this, sharedExternal.getLevel());
        return (childList == null)? sharedExternal.getLevel() : childList.refreshTree();
    }

    @Override
    public int refreshTree(IRecursiveList parentList, int level) {
        //System.out.printf("        D refreshTree: %s starting=%d \n", this, level);
        if(childList == null){
            sharedExternal.setParentList(parentList);
            sharedExternal.setLevel(level);
            return level;
        }
        else{
            return childList.refreshTree(parentList, level);// sets shared state
        }
    }

    @Override
    public ListItr getFlatIterator() {
        return childList.getFlatIterator();
    }

    @Override
    public ListItr getDepthFirstIterator() {
        return childList.getDepthFirstIterator();
    }

    @Override
    public ListItr getBreadthFirstIterator(){
        return childList.getBreadthFirstIterator();
    }

    @Override
    public void setSharedExternalState(SharedExternalState sharedExternal) {
        this.sharedExternal = sharedExternal;
    }

    @Override
    public SharedExternalState getSharedExternalState(){
        return sharedExternal;
    }

    @Override
    public void setParentList(IRecursiveList parentList) {
        this.sharedExternal.setParentList(parentList);
    }
}
