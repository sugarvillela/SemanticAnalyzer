package listnode;

import flagobj.IFlags;

public class ListNode<T> extends ItrList {
    public ListNode<T> prev, next;
    public final T payload;

    public ListNode( T payload ) {
        this.payload = payload;
        prev = null;
        next = null;
    }

    @Override
    public boolean meetsCriteria(ListNode item, Object criteria) {
        return false;
    }

    @Override
    public ItrList<T> newList() {
        return new ListNode<>(payload);
    }

    public ListNode<T> copy(){
        return new ListNode<>(payload);
    }

    @Override
    public String toString(){
        return payload.toString();
    }

    @Override
    public boolean recursible() {
        return false;
    }

    @Override
    public void disp() {
        System.out.println(this.toString());
    }
}
