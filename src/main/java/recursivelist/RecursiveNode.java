package recursivelist;

import store.IStore;

public abstract class RecursiveNode implements IRecursiveNode {
    private IRecursiveNode prev, next;
    private IRecursiveList childList;

    public RecursiveNode() {
        this(null);
    }

    public RecursiveNode(IRecursiveList childList) {
        this.childList = childList;
        prev = null;
        next = null;
    }

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
        this.childList = childList;
    }

    public IRecursiveList getChildList(){
        return this.childList;
    }

    @Override
    public boolean isRecursive() {
        return this.childList != null;
    }

}
