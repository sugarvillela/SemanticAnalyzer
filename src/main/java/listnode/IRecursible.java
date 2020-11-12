package listnode;

public interface IRecursible <T> {
    int size();

    boolean seek( int index );
    ListNode<T> peekFront() ;
    ListNode<T> peekBack() ;
    ListNode<T> peekIn( int index );

    void pushFront(ListNode<T> newHead);
    void pushBack (ListNode<T> newTail);
    void pushIn( int index, ListNode<T> node );

    ListNode<T> popFront();
    ListNode<T> popBack();
    ListNode<T> popIn( int index) ;

    /* Iterator: go forward or back within an inclusive range */
    void setRange(int start, int end);
    void setRange(int start, int end, int increment);
    void clearRange();

    void rewind();
    int key();
    boolean hasNext();
    ListNode<T> next();

    /* Splitter */
    BaseRecursible sublist(int lo, int hi);
    BaseRecursible sublist(Object criteria);
    boolean meetsCriteria(ListNode<T> item, Object criteria);

    /* New empty list, implemented by subclasses */
    BaseRecursible<T> newList();

    ListNode<T>[] toArray();

    boolean recursible();

    /* Debug */
    void disp();
}
