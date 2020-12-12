package recursivelist;

import recursivelist.util.SublistUtil;

public interface IRecursiveList {
    int size();

    boolean seek(int index);
    IRecursiveNode peekFront() ;
    IRecursiveNode peekBack() ;
    IRecursiveNode peekIn( int index );

    void pushFront(IRecursiveNode newHead);
    void pushBack (IRecursiveNode newTail);
    void pushIn( int index, IRecursiveNode node );

    IRecursiveNode popFront();
    IRecursiveNode popBack();
    IRecursiveNode popIn( int index) ;

    /* Iterator: go forward or back within an inclusive range */
    void setRange(int start, int end);
    void setRange(int start, int end, int increment);
    void clearRange();

    void rewind();
    int key();
    boolean hasNext();
    IRecursiveNode next();

    /* Splitter */
    IRecursiveList sublist(int lo, int hi);

    IRecursiveList[] scopeDownLists();

    /* New empty list, implemented by subclasses */
    IRecursiveList newList();
    IRecursiveNode[] toArray(IRecursiveNode[] emptyArray);

    boolean isRecursive();

    /* Debug */
    void disp();

}
