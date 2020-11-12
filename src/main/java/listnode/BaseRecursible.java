package listnode;

public abstract class BaseRecursible<T> implements IRecursible {
    protected ListNode<T> head, tail;           // for doubly linked list role
    protected ListNode<T> curr;                 // for accessor and iterator
    protected int top;//                        // top = length-1
    protected int start, end, inci, rowi;       // itr vals
    protected boolean sizeChanged;              // trigger clear range on rewind
    protected boolean throwOnErr;

    BaseRecursible(){
        curr = head = tail = null;
        top = -1;
        rowi = 0;
        inci = 1;
        start = 0;
        end = 0;
        sizeChanged = true;
        throwOnErr = false;
    }

    // List functions: bookkeeping
    protected void onSizeChanged(){
        /* keep itr/access valid */
        rowi = 0;
        curr = head;
        sizeChanged = true;
    }
    protected final void incSize(){//use this to keep itrRange updated
        top++;
        onSizeChanged();
    }
    protected final void decSize(){//use this to keep itrRange updated
        top--;
        onSizeChanged();
    }

    // Allow positive and negative indexing
    protected final int fixNegIndex(int index ){
        /* For this and child classes: end is max index + 1  */
        return ( index< 0 )? top + 1 + index : index;
    }
    protected final boolean assertValidIndex(int index){
        if( index > top || 0 > index){
            if(throwOnErr){
                throw new ArrayIndexOutOfBoundsException(
                    String.format("index %d out of bounds; available index 0 through %d", index, top)
                );
            }
            return false;
        }
        return true;
    }

    // protected and public list functions: seek
    protected void seekFront( int index ){
        for (
                curr = head, rowi = 0;
                rowi < index;
                curr = curr.next, rowi++
        );
    }
    protected void seekBack( int index ){
        for (
                curr = tail, rowi = top;
                rowi > index;
                curr = curr.prev, rowi--
        );
    }

    @Override
    public int size(){
        return top + 1;
    }
    @Override
    public boolean seek( int index ){
        index = this.fixNegIndex(index);
        System.out.printf("seek index=%d\n", index );
        if(this.assertValidIndex(index)){
            if(index == rowi){
                // Pass; already there
            }
            else if( index == rowi+1 ){
                rowi = index;
                curr = curr.next;
            }
            else if( index == rowi - 1 ){
                rowi = index;
                curr = curr.prev;
            }
            else if( (top + 1 - index) < index ){
                this.seekBack(index);
            }
            else{
                this.seekFront(index);
            }
            return true;
        }
        return false;
    }

    @Override
    public ListNode<T> peekFront() {
        return head;
    }
    @Override
    public ListNode<T> peekBack() {
        return tail;
    }
    @Override
    public ListNode<T> peekIn( int index ) {
        return (this.seek(index))?
                curr : null;
    }

    @Override
    public void pushFront(ListNode newHead){
        newHead.next = head;
        if(top < 0){   // empty list
            tail = newHead;
        }
        head = newHead;
        this.incSize();
    }
    @Override
    public void pushBack (ListNode newTail){
        if(top < 0){
            this.pushFront(newTail);
        }
        else{
            newTail.prev = tail;
            tail.next = newTail;
            tail = newTail;
            this.incSize();
        }
    }
    @Override
    public void pushIn(int index, ListNode node ){
        if( !this.seek( index )){
            return;
        }
        node.prev = curr.prev;
        node.prev.next = node;
        node.next = curr;
        curr.prev = node;
        curr = node;
        rowi++;
        this.incSize();
    }

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
        if( top >= 0 ){
            ListNode<T> victim = tail;
            tail = victim.prev;
            tail.next = null;
            rowi = top+1;
            curr = tail;
            this.decSize();
            return victim;
        }
        else{
            return null;
        }
    }
    @Override
    public ListNode<T> popIn( int index) {
        if( index==0 ){
            return this.popFront();
        }
        else if( index == top ){
            return this.popBack();
        }
        else if(this.seek( index )){
            ListNode<T> victim  = curr;
            ListNode<T> prev = curr.prev;
            ListNode<T> next= curr.next;
            prev.next = next;
            next.prev = prev;
            curr = next;
            this.decSize();
            return victim;
        }
        else{
            return null;
        }
    }

    /* Iterator: go forward or back within an inclusive range */
    @Override
    public void setRange(int start, int end){
        this.setRange(start, end, 1);
    }
    @Override
    public void setRange(int start, int end, int increment){
        this.start = start;
        this.end = end;
        this.inci = increment;
        this.sizeChanged = false;// to preserve settings on rewind
        System.out.printf("setRange: top=%d, start=%d, end=%d, inci=%d", top, start, end, inci);
    }
    @Override
    public void clearRange(){
        start = 0;
        end = top;
        inci = 1;
        sizeChanged = false;// to preserve settings on rewind
    }

    /* Iterator: manage pointer */
    protected void incCur(){
        if(inci < 0){
            curr = curr.prev;
        }
        else{
            curr = curr.next;
        }
        rowi += inci;
    }

    @Override
    public void rewind(){
        if(sizeChanged){// clears iter range if size changed
            System.out.println("sizeChanged");
            clearRange();
        }
        //System.out.printf("rewind: top=%d, start=%d, end=%d, inci=%d", top, start, end, inci);
        System.out.println();
        this.seek((inci < 0)? end : start);
    }
    @Override
    public int key(){//iterator index
        return rowi - inci;//assume this is called after incCur()
    }
    @Override
    public boolean hasNext() {
        //System.out.printf("hasNext=%b\n", curr != null);
        return start <= rowi && rowi <= end;
    }
    @Override
    public ListNode<T> next() {
        ListNode<T> ret = curr;
        //System.out.printf("next: rowi=%d\n", rowi );
        incCur();
        return ret;
    }

    /* Splitter */
    @Override
    public BaseRecursible sublist(int lo, int hi){
        BaseRecursible<T> out = newList();
        this.setRange(lo, hi, 1);
        this.rewind();
        while(this.hasNext()){
            out.pushBack(this.next().copy());
        }
        return out;
    }
    @Override
    public BaseRecursible sublist(Object criteria){
        BaseRecursible<T> out = newList();
        this.clearRange();
        this.rewind();
        while(this.hasNext()){
            ListNode<T> next = this.next();
            if(this.meetsCriteria(next, criteria))
            out.pushBack(next.copy());
        }
        return out;
    }

    public BaseRecursible copy(){
        BaseRecursible out = this.newList();
        this.clearRange();
        this.rewind();
        while(this.hasNext()){
            out.pushBack(new ListNode<>(this.next()));
        }
        return out;
    }

    @Override
    public ListNode[] toArray() {
        ListNode[] out = new ListNode[this.size()];
        this.clearRange();
        this.rewind();
        int i = 0;
        while(this.hasNext()){
            out[i++] =new ListNode<>(this.next());
        }
        return out;
    }

    @Override
    public boolean recursible(){
        return true;
    }

    @Override
    public void disp(){
        System.out.println("\nDisplay ItrList:");
        this.clearRange();
        this.rewind();
        while(this.hasNext()){
            System.out.println(rowi + ": " + this.next());
        }
        System.out.println("End display ItrList\n");
    }

}