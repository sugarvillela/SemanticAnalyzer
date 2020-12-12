package recursivelist;

import store.IStore;

public interface IRecursiveNode extends IStore {

    void setNext(IRecursiveNode next);

    IRecursiveNode getNext();

    void setPrev(IRecursiveNode prev);

    IRecursiveNode getPrev();

    void setChildList(IRecursiveList childList);

    IRecursiveList getChildList();

    boolean isRecursive();

    IRecursiveNode copy();

    IRecursiveNode newNode();

    void disp();

}
