package listnode;

public class ListNode<T> {
    public ListNode<T> prev, next;
    public final T payload;

    public ListNode( T payload ) {
        this.payload = payload;
        prev = null;
        next = null;
    }

    public ListNode<T> copy(){
        return new ListNode<>(payload);
    }

    @Override
    public String toString(){
        return payload.toString();
    }


    public void disp() {
        System.out.println(this.toString());
    }
}
