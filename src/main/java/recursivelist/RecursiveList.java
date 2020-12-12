package recursivelist;

import recursivelist.util.SublistUtil;

public class RecursiveList implements IRecursiveList{
    protected IRecursiveNode head, tail;           // for doubly linked list role
    protected IRecursiveNode curr;                 // for accessor and iterator
    protected int top;//                        // top = length-1
    protected int start, end, inci, rowi;       // itr values
    protected boolean sizeChanged;              // trigger clear range on rewind
    //protected final SublistUtil sublistUtil;
    protected static final boolean THROW_ON_ERR = false;

    public RecursiveList(){
        curr = head = tail = null;
        top = -1;
        rowi = 0;
        inci = 1;
        start = 0;
        end = 0;
        sizeChanged = true;
        //sublistUtil = new SublistUtil(this);
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
            if(THROW_ON_ERR){
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
                curr = curr.getNext(), rowi++
        );
    }
    protected void seekBack( int index ){
        for (
                curr = tail, rowi = top;
                rowi > index;
                curr = curr.getPrev(), rowi--
        );
    }

    @Override
    public int size(){
        return top + 1;
    }
    @Override
    public boolean seek( int index ){
        index = this.fixNegIndex(index);
        //System.out.printf("seek index=%d\n", index );
        if(this.assertValidIndex(index)){
            if(index == rowi){
                // Pass; already there
                return true;
            }
            else if( index == rowi+1 ){
                rowi = index;
                curr = curr.getNext();
            }
            else if( index == rowi - 1 ){
                rowi = index;
                curr = curr.getPrev();
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
    public IRecursiveNode peekFront() {
        return head;
    }
    @Override
    public IRecursiveNode peekBack() {
        return tail;
    }
    @Override
    public IRecursiveNode peekIn( int index ) {
        return (this.seek(index))?
                curr : null;
    }

    @Override
    public void pushFront(IRecursiveNode newHead){
        newHead.setNext(head);
        if(top < 0){   // empty list
            tail = newHead;
        }
        head = newHead;
        this.incSize();
    }
    @Override
    public void pushBack (IRecursiveNode newTail){
        if(top < 0){
            this.pushFront(newTail);
        }
        else{
            newTail.setPrev(tail);
            tail.setNext(newTail);
            tail = newTail;
            this.incSize();
        }
    }
    @Override
    public void pushIn(int index, IRecursiveNode node ){
        if( !this.seek( index )){
            return;
        }
        node.setPrev(curr.getPrev());
        node.getPrev().setNext(node);
        node.setNext(curr);
        curr.setPrev(node);
        curr = node;
        rowi++;
        this.incSize();
    }

    @Override
    public IRecursiveNode popFront(){
        if( top >= 0 ){
            IRecursiveNode victim = head;
            head = victim.getNext();
            head.setPrev(null);
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
    public IRecursiveNode popBack(){
        if( top >= 0 ){
            IRecursiveNode victim = tail;
            tail = victim.getPrev();
            tail.setNext(null);
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
    public IRecursiveNode popIn( int index) {
        if( index==0 ){
            return this.popFront();
        }
        else if( index == top ){
            return this.popBack();
        }
        else if(this.seek( index )){
            IRecursiveNode victim  = curr;
            IRecursiveNode prev = curr.getPrev();
            IRecursiveNode next= curr.getNext();
            prev.setNext(next);
            next.setPrev(prev);
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
            curr = curr.getPrev();
        }
        else{
            curr = curr.getNext();
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
        //System.out.println();
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
    public IRecursiveNode next() {
        IRecursiveNode ret = curr;
        //System.out.printf("next: rowi=%d\n", rowi );
        incCur();
        return ret;
    }

    /* Splitter */
    @Override
    public IRecursiveList sublist(int lo, int hi){
        IRecursiveList out = newList();
        this.setRange(lo, hi, 1);
        this.rewind();
        while(this.hasNext()){
            out.pushBack(this.next().copy());
        }
        return out;
    }

    @Override
    public IRecursiveList[] scopeDownLists() {
        this.clearRange();
        this.rewind();
        int count = 0;
        while(this.hasNext()){
            if(this.next().isRecursive()){
                count++;
            }
        }
        IRecursiveList[] out = new IRecursiveList[count];
        this.rewind();
        int i = 0;
        while(this.hasNext()){
            IRecursiveNode next = this.next();
            if(next.isRecursive()){
                out[i++] = next.getChildList();
            }

        }
        return out;
    }

    @Override
    public IRecursiveList newList() {
        return null;
    }

    @Override
    public IRecursiveNode[] toArray(IRecursiveNode[] emptyArray) {
        this.clearRange();
        this.rewind();
        int i = 0;
        while(this.hasNext()){
            emptyArray[i++] = this.next();
        }
        return emptyArray;
    }

    public IRecursiveList copy(){
        IRecursiveList out = this.newList();
        this.clearRange();
        this.rewind();
        while(this.hasNext()){
            out.pushBack(this.next());
        }
        return out;
    }

    @Override
    public boolean isRecursive(){
        return true;
    }

    @Override
    public void disp(){
        System.out.println("\nDisplay ItrList:");
        this.clearRange();
        this.rewind();
        while(this.hasNext()){
            IRecursiveNode node = this.next();
            if(node.isRecursive()){
                System.out.print(rowi + ": ");
                node.getChildList().disp();
            }
            else{
                System.out.println(rowi + ": " + node);
            }
        }
        System.out.println("End display ItrList\n");
    }
}
