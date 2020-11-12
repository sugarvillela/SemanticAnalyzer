package listnode;

public abstract class NonRecursiveList <T> extends BaseRecursible {

    @Override
    public boolean recursible(){
        return false;
    }
    @Override
    public void pushFront(ListNode newHead){}
    @Override
    public void pushBack (ListNode newTail){}
    @Override
    public void pushIn(int index, ListNode node ){ }
    @Override
    public ListNode<T> popFront(){
        if( top >= 0 ){
            ListNode<T> victim = head;
            head = victim.next;
            head.prev = null;
            rowi = 0;
            curr = head;
            this.decSize();
            return victim;
        }
        else{
            return null;
        }
    }
    @Override
    public ListNode<T> popBack(){
        return null;
    }
    @Override
    public ListNode<T> popIn( int index) {
        return null;
    }
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public BaseRecursible sublist(int lo, int hi){
        return null;
    }

    @Override
    public boolean meetsCriteria(ListNode item, Object criteria) {
        return false;
    }

    @Override
    public BaseRecursible newList() {
        return null;
    }
}
