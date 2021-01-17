package recursivelist;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/** Linked list with front, back, middle access; forward, back, multi-step iteration.
 *  Works with IRecursiveNode to allow recursion of any list node.
 *  Each node's payload is another IRecursiveList */
public class RecursiveList implements IRecursiveList{
    protected static final int NUM_LISTENERS = 4;   // number of iterator impl + 1, for AccessUtil
    protected final SharedInternalState sharedInternal;
    protected SharedExternalState sharedExternal;
    protected final AccessUtil access;
    protected final SimpleListener[] listeners;     // internal listeners for size change
    protected FlatItr flatItr;                      // ListItr impl
    protected DepthFirstItr depthFirstItr;
    protected BreadthFirstItr breadthFirstItr;

    public RecursiveList(){
        this(new SharedExternalState());
    }

    public RecursiveList(SharedExternalState sharedExternal){
        this.sharedExternal = sharedExternal;
        sharedInternal = new SharedInternalState();
        access = new AccessUtil(sharedInternal);
        listeners = new SimpleListener[NUM_LISTENERS];
        listeners[0] = access;
        sharedInternal.setListeners(listeners);
    }

    private static class SharedInternalState {
        private IRecursiveNode head, tail;        // for doubly linked list role
        private int top;                          // top = length-1
        private SimpleListener[] listeners;       // update AccessUtil, ListItr

        private SharedInternalState(){
            head = tail = null;
            top = -1;
        }
        private void setListeners(SimpleListener[] listeners){
            this.listeners = listeners;
        }

        private void notifyListeners(){
            for(SimpleListener listener : listeners){
                if(listener != null){
                    listener.onSizeChanged();
                }
            }
        }

        protected final void incSize(){
            top++;
            notifyListeners();
        }

        protected final void decSize(){
            top--;
            notifyListeners();
        }

        protected final void incSize(int inc){
            top += inc;
            notifyListeners();
        }

        protected final void decSize(int inc){
            top -= inc;
            notifyListeners();
        }

        @Override
        public String toString() {
            int numListeners = 0;
            for(SimpleListener listener : listeners){
                if(listener != null){
                    numListeners++;
                }
            }
            return "  SharedState{" +
                    "\n    head: " + head +
                    "\n    tail: " + tail +
                    "\n    top: " + top +
                    "\n    # listeners: " + numListeners +
                    "\n  }";
        }
    }

    private static class AccessUtil implements SimpleListener{
        private static final boolean THROW_ON_ERR = true;  // assertValidIndex()
        private final SharedInternalState sharedInternal;                   // Shares head, tail, top with parent list
        private IRecursiveNode curr;                        // Access pointer
        private int row;                                    // Current index of access pointer

        private AccessUtil(SharedInternalState sharedInternal) {
            this.sharedInternal = sharedInternal;
            curr = null;
            row = 0;
        }

        // Allow positive or negative indexing
        private int fixNegIndex(int index){
            return ( index< 0 )? sharedInternal.top + 1 + index : index;
        }

        private boolean assertValidIndex(int index){
            if( index > sharedInternal.top || 0 > index){
                if(THROW_ON_ERR){
                    throw new ArrayIndexOutOfBoundsException(
                            String.format("index %d out of bounds; available index 0 through %d", index, sharedInternal.top)
                    );
                }
                return false;
            }
            return true;
        }
        private boolean assertValidRange(int lo, int hi){
            if(lo >= hi){
                if(THROW_ON_ERR){
                    throw new ArrayIndexOutOfBoundsException(
                            String.format("Low index %d >= high index %d", lo, hi)
                    );
                }
                return false;
            }
            return true;
        }

        private void seekFront( int index ){
            for (
                    curr = sharedInternal.head, row = 0;
                    row < index;
                    curr = curr.getNext(), row++
            );
        }

        private void seekBack( int index ){
            for (
                    curr = sharedInternal.tail, row = sharedInternal.top;
                    row > index;
                    curr = curr.getPrev(), row--
            );
        }

        private boolean seek( int index ){
            index = this.fixNegIndex(index);
            if(this.assertValidIndex(index)){
                if(curr == null){
                    seekFront(index);
                }
                else if(index == row){
                    // Pass; already there
                    return true;
                }
                else if( index == row +1 ){
                    row = index;
                    curr = curr.getNext();
                }
                else if( index == row - 1 ){
                    row = index;
                    curr = curr.getPrev();
                }
                else if( (sharedInternal.top + 1 - index) < index ){
                    this.seekBack(index);
                }
                else{
                    this.seekFront(index);
                }
                return true;
            }
            return false;
        }

        private boolean seek(IRecursiveNode target){
            curr = sharedInternal.head;
            row = 0;
            while(curr != null && !curr.equals(target)){
                row++;
                curr = curr.getNext();
            }
            return curr != null;
        }

        private void incCur(int inc){
            row += inc;
            if(inc < 0){
                for(int i = inc * -1; i > 0 && curr != null; i--){
                    curr = curr.getPrev();
                }
            }
            else{
                for(int i = inc; i > 0 && curr != null; i--){
                    curr = curr.getNext();
                }
            }
        }

        @Override
        public final void onSizeChanged(){
            /* keep access valid */
            row = 0;
            curr = sharedInternal.head;
        }

        @Override
        public String toString() {
            return "  AccessUtil{" +
                    "\n    THROW_ON_ERR: " + THROW_ON_ERR +
                    "\n    curr: " + curr +
                    "\n    row: " + row +
                    "\n  }";
        }
    }

    private static class KeyUtil{// generate same key, whether called before or after next()
        private final ItrBase itr;
        int offset;

        private KeyUtil(ItrBase itr) {
            this.itr = itr;
        }

        public void rewind() {
            offset = 0;
        }

        public void hasNext() {
            offset = 0;
        }

        public void next() {
            offset += itr.inc;
        }

        public int key() {
            return itr.itrAccess.row - offset;
        }

        @Override
        public String toString() {
            return "KeyUtil{offset=" + offset + '}';
        }
    }

    public static abstract class ItrBase implements ListItr, SimpleListener{
        protected final SharedInternalState sharedInternal;       // Shares head, tail, top with parent list
        protected final AccessUtil itrAccess;   // Does not share access state with parent list
        protected final KeyUtil keyUtil;
        protected int start, end, inc;
        protected boolean sizeChanged;
        protected boolean busy;

        public ItrBase(SharedInternalState sharedInternal) {
            this.sharedInternal = sharedInternal;
            this.itrAccess = new AccessUtil(sharedInternal);
            this.keyUtil = new KeyUtil(this);
            inc = 1;
            start = 0;
            end = 0;
            sizeChanged = true;
        }

        @Override
        public void setRange(int start, int end){
            this.setRange(start, end, 1);
        }

        @Override
        public void setRange(int start, int end, int increment){
            start = itrAccess.fixNegIndex(start);
            end =   itrAccess.fixNegIndex(end);
            if(end < start){
                throw new IllegalStateException(
                        String.format("end < start? parameters passed: start = %d, end = %d", start, end)
                );
            }
            this.start = Math.max(start, 0);
            this.end =   Math.min(end, sharedInternal.top);
            this.inc =  increment;
            this.sizeChanged = false;// to preserve settings on rewind
        }

        @Override
        public void clearRange(){
            start = 0;
            end = sharedInternal.top;
            inc = 1;
            sizeChanged = false;// to preserve settings on rewind
        }

        @Override
        public void iterateBack() {
            start = 0;
            end = sharedInternal.top;
            inc = -1;
            sizeChanged = false;// to preserve settings on rewind
        }

        @Override
        public void setBusy(){
            busy = true;
        }
        @Override
        public void clearBusy(){
            busy = false;
        }

        /* Iterator: manage pointer */
        protected void incCur(){
            itrAccess.incCur(inc);
        }

        @Override
        public void rewind(){
            if(sizeChanged){// clears itr range if size changed
                clearRange();
            }
            itrAccess.seek((inc < 0)? end : start);
            this.setBusy();
            this.keyUtil.rewind();
        }

        /* Iterator index: always correct */
        @Override
        public int key(){
            return this.keyUtil.key();
        }

        @Override
        public int level() {
            return 0;
        }

        @Override
        public boolean hasNext() {
            this.keyUtil.hasNext();
            if(start <= itrAccess.row && itrAccess.row <= end){
                return true;
            }
            else{
                clearBusy();
                return false;
            }
        }

        @Override
        public IRecursiveNode next() {
            this.keyUtil.next();
            IRecursiveNode out = itrAccess.curr;
            incCur();
            return out;
        }

        @Override
        public IRecursiveNode peekNext() {
            IRecursiveNode temp = itrAccess.curr;
            for (int i = 1; i < inc; i++){
                if(temp == null || (temp = temp.getNext()) == null){
                    return null;
                }
            }
            return temp;
        }

        @Override
        public void onSizeChanged(){
            if(busy){
                throw new ConcurrentModificationException(
                    "Only obtain or rewind iterator immediately before loop; never modify list while iterating");
            }
            itrAccess.onSizeChanged();
            this.sizeChanged = true;
        }

        @Override
        public String toString() {
            return "  "+ this.getClass().getSimpleName() + "{" +
                    "\n    keyUtil: " + keyUtil +
                    "\n    busy: " + busy +
                    "\n    start: " + start +
                    "\n    end: " + end +
                    "\n    inc: " + inc +
                    "\n    sizeChanged: " + sizeChanged +
                    "\n  }";
        }
    }

    public static class FlatItr extends ItrBase {
        public FlatItr(SharedInternalState shared) {
            super(shared);
        }
    }

    public static class DepthFirstItr extends ItrBase {
        private ListItr currItr;
        public DepthFirstItr(SharedInternalState sharedInternal) {
            super(sharedInternal);
            currItr = this;
        }

        @Override
        public void rewind() {
            super.rewind();
            keyUtil.rewind();
            currItr = this;
        }

        @Override
        public int key(){
            // A lot of code to guarantee key and level are always correct
            // Or, use the commented line below and only call key() and level() after next()
            if(this.equals(currItr)){
                IRecursiveNode peek;
                if(keyUtil.offset == 0 && (peek = super.peekNext()) != null && peek.isRecursive()){
                    return 0;
                }
                else{
                    return this.keyUtil.key();
                }
            }
            return currItr.key();
            //return (this.equals(currItr))? this.keyUtil.key() : currItr.key();
        }

        @Override
        public boolean hasNext() {
            keyUtil.hasNext();
            if(this.equals(currItr)){
                return (start <= itrAccess.row && itrAccess.row <= end);
            }
            else if(currItr.hasNext()){
                return true;
            }
            else{
                currItr = this;
                return (start <= itrAccess.row && itrAccess.row <= end);
            }
        }

        @Override
        public IRecursiveNode next() {
            if(this.equals(currItr)){
                IRecursiveNode out = super.next();
                if(out.isRecursive() && out.size() > 0){
                    currItr = out.getDepthFirstIterator();
                    if(inc < 0){
                        currItr.iterateBack();
                    }
                    return currItr.next();
                }
                else{
                    return out;
                }
            }
            else{
                return currItr.next();
            }
        }

        @Override
        public int level() {
            // A lot of code to guarantee key and level are always correct
            // Or, use the single line below and only call key() and level() after next()
            if(this.equals(currItr)){
                IRecursiveNode peek;
                if(keyUtil.offset == 0 && (peek = super.peekNext()) != null && peek.isRecursive()){
                    return peek.getLevel() + 1;
                }
                else{
                    return 0;// TODO fix level
                }
            }
            return currItr.level();
            //return (this.equals(currItr))? shared.level : currItr.level();
        }

        @Override
        public void onSizeChanged() {
            super.onSizeChanged();
            currItr = this;
        }
    }

    public static class BreadthFirstItr extends ItrBase {
        private final IRecursiveList parentList;
        private ArrayList<ListItr> iterators;
        private int itrPointer;
        IRecursiveNode currNode;

        public BreadthFirstItr(SharedInternalState shared, IRecursiveList parentList) {
            super(shared);
            this.parentList = parentList;
            itrPointer = 0;
        }

        @Override
        public void rewind() {
            super.rewind();
            if(itrPointer != 0){
                iterators.get(itrPointer - 1).clearBusy();
                currNode = null;
            }
            iterators = new ArrayList<>();
            iterators.add(parentList.getFlatIterator());
            itrPointer = 0;
            this.setBusy();
        }

        @Override
        public int key(){
            return iterators.get(itrPointer).key();
        }

        @Override
        public boolean hasNext() {
            if(iterators.get(itrPointer).hasNext()){
                return true;
            }
            else if(++itrPointer < iterators.size()){
                return true;
            }
            else{
                this.clearBusy();
                return false;
            }
        }

        @Override
        public IRecursiveNode next() {
            currNode = iterators.get(itrPointer).next();
            if(currNode.isRecursive() && currNode.size() > 0){
                iterators.add(currNode.getFlatIterator());
            }
            return currNode;
        }

        @Override
        public int level() {
            return (currNode == null)? 0 : currNode.getLevel();
        }
    }

    @Override
    public ListItr getFlatIterator(){
        if(flatItr == null){
            flatItr = new FlatItr(sharedInternal);
            listeners[1] = flatItr;
        }
        flatItr.clearRange();
        flatItr.rewind();
        return flatItr;
    }

    @Override
    public ListItr getDepthFirstIterator() {
        if(depthFirstItr == null){
            depthFirstItr = new DepthFirstItr(sharedInternal);
            listeners[2] = depthFirstItr;
        }
        depthFirstItr.clearRange();
        depthFirstItr.rewind();
        return depthFirstItr;
    }

    @Override
    public ListItr getBreadthFirstIterator() {
        if(breadthFirstItr == null){
            breadthFirstItr = new BreadthFirstItr(sharedInternal, this);
            listeners[3] = breadthFirstItr;
        }
        breadthFirstItr.clearRange();
        breadthFirstItr.rewind();
        return breadthFirstItr;
    }

    @Override
    public void setSharedExternalState(SharedExternalState sharedExternal) {
        this.sharedExternal = sharedExternal;
    }

    @Override
    public SharedExternalState getSharedExternalState() {
        return sharedExternal;
    }

    @Override
    public void setParentList(IRecursiveList parentList) {
        sharedExternal.setParentList(parentList);
    }

    @Override
    public IRecursiveList getParentList() {
        return sharedExternal.getParentList();
    }

    @Override
    public void dispList(){
        System.out.println("=======================\nDisplay ItrList:");
        this.dispList(0);
        System.out.println("\nEnd display ItrList\n=======================");
    }

    @Override
    public void dispList(int indent){
        String tab = indent == 0?
                "" : new String(new char[4 * indent]).replace('\0', ' ');
        this.getFlatIterator();
        while(flatItr.hasNext()){
            IRecursiveNode node = flatItr.next();
            if(node.isRecursive()){
                System.out.println(tab + flatItr.key() + ": ");
                node.getChildList().dispList(indent+1);
            }
            else{
                System.out.println(tab + flatItr.key() + ": " + node);
            }
        }
    }

    @Override
    public String toString() {
//        IRecursiveNode[] nodes = this.toFlatArray();
//        String[] out = new String[nodes.length];
//        for(int i = 0; i<nodes.length; i++){
//            out[i] = nodes[i].toString();
//        }

        return this.getClass().getSimpleName() + "{" +
                size() +
//                "\n" + sharedInternal +
//                "\n" + access +
//                "\n" + ((flatItr == null)? "    flatItr:       unused" : flatItr) +
//                "\n" + ((depthFirstItr == null)? "    depthFirstItr: unused" : depthFirstItr) +
//                "\n    Content: " +
//                "\n    " + String.join(", ", out) +
                "}";
    }

    /* =====Public: Access and change the list======================================================================= */

    @Override
    public int size(){
        return sharedInternal.top + 1;
    }

    @Override
    public int indexOf(IRecursiveNode target) {
        return (sharedInternal.top >= 0 && access.seek(target))? access.row : -1;
    }


    @Override
    public boolean seek( int index ){
        return access.seek(index);
    }

    @Override
    public boolean seek(IRecursiveNode target) {
        return sharedInternal.top >= 0 && access.seek(target);
    }


    @Override
    public IRecursiveNode peekFront() {
        return sharedInternal.head;
    }

    @Override
    public IRecursiveNode peekBack() {
        return sharedInternal.tail;
    }

    @Override
    public IRecursiveNode peekIn( int index ) {
        return (this.seek(index))?
                access.curr : null;
    }

    @Override
    public IRecursiveList peekFront(int hi) {
        IRecursiveList out = newList();
        IRecursiveNode curr = sharedInternal.head;
        while(hi >= 0 && curr != null){
            out.pushBack(curr.copyNode());
            hi--;
            curr = curr.getNext();
        }
        return out;
    }

    @Override
    public IRecursiveList peekBack(int lo) {
        IRecursiveList out = newList();
        IRecursiveNode curr = this.peekIn(lo);
        while(curr != null){
            out.pushBack(curr.copyNode());
            curr = curr.getNext();
        }
        return out;
    }

    @Override
    public IRecursiveList peekIn(int lo, int hi) {
        IRecursiveList out = newList();

        if(sharedInternal.top >= 0 && access.assertValidRange(lo, hi)){
            this.getFlatIterator();
            flatItr.setRange(lo, hi, 1);
            flatItr.rewind();
            while(flatItr.hasNext()){
                out.pushBack(flatItr.next().copyNode());
            }
            out.peekFront().setPrev(null);
            out.peekBack().setNext(null);
        }
        return out;
    }

    @Override
    public IRecursiveList peekFront(IRecursiveNode last) {
        IRecursiveList out = newList();
        IRecursiveNode first = sharedInternal.head;
        if(sharedInternal.top >= 0){
            while(first != null && !first.equals(last)){
                out.pushBack(first.copyNode());
                first = first.getNext();
            }
            if(first != null){
                out.pushBack(first.copyNode());
            }
        }
        return out;
    }

    @Override
    public IRecursiveList peekBack(IRecursiveNode first) {
        IRecursiveList out = newList();
        if(sharedInternal.top >= 0 && this.seek(first)){
            while(first != null){
                out.pushBack(first.copyNode());
                first = first.getNext();
            }
        }
        return out;
    }

    @Override
    public IRecursiveList peekIn(IRecursiveNode first, IRecursiveNode last) {
        IRecursiveList out = newList();

        if(sharedInternal.top >= 0 && this.seek(first) &&  this.seek(last)){
            do{
                out.pushBack(first.copyNode());
                first = first.getNext();
            }
            while(first != null && !first.equals(last));

            if(first != null){
                out.pushBack(first.copyNode());
            }
        }
        return out;
    }

    @Override
    public void pushFront(IRecursiveNode newHead){
        newHead.setNext(sharedInternal.head);
        newHead.setPrev(null);
        if(sharedInternal.top < 0){   // empty list
            sharedInternal.tail = newHead;
        }
        sharedInternal.head = newHead;
        sharedInternal.incSize();
    }

    @Override
    public void pushBack (IRecursiveNode newTail){
        if(sharedInternal.top < 0){
            this.pushFront(newTail);
        }
        else{
            newTail.setPrev(sharedInternal.tail);
            newTail.setNext(null);
            sharedInternal.tail.setNext(newTail);
            sharedInternal.tail = newTail;
            sharedInternal.incSize();
            //System.out.println("pushBack: top = "+ sharedInternal.top + ": "+ newTail);
        }
    }

    @Override
    public void pushIn(int index, IRecursiveNode newNode){
        if(index == 0){
            this.pushFront(newNode);
        }
        else if(index > sharedInternal.top){
            //System.out.println(index + " > " + sharedInternal.top);
            this.pushBack(newNode);
        }
        else if(this.seek(index)){
            //System.out.println("case 3");
            newNode.setPrev(access.curr.getPrev());
            newNode.getPrev().setNext(newNode);
            newNode.setNext(access.curr);
            access.curr.setPrev(newNode);
            access.curr = newNode;
            access.row++;
            sharedInternal.incSize();
        }
    }


    @Override
    public void pushFront(IRecursiveList newFront) {
        if(sharedInternal.top >= 0){
            IRecursiveNode newFrontTail = newFront.peekBack();
            newFrontTail.setNext(sharedInternal.head);
            sharedInternal.head.setPrev(newFrontTail);
            sharedInternal.head = newFront.peekFront();
            sharedInternal.incSize(newFront.size());
        }
        else{
            pushBack(newFront);
        }
    }

    @Override
    public void pushBack(IRecursiveList newBack) {
        if(sharedInternal.top >= 0){
            IRecursiveNode newBackHead = newBack.peekFront();
            newBackHead.setPrev(sharedInternal.tail);
            sharedInternal.tail.setNext(newBackHead);
        }
        else{
            sharedInternal.head = newBack.peekFront();
        }
        sharedInternal.tail = newBack.peekBack();
        sharedInternal.incSize(newBack.size());
    }

    @Override
    public void pushIn(int index, IRecursiveList newIn) {
        if(sharedInternal.top >= 0){
            index = access.fixNegIndex(index);

            if(index == 0){
                pushFront(newIn);
            }
            else if(index > sharedInternal.top){
                pushBack(newIn);
            }
            else{
                IRecursiveNode e = this.peekIn(index);
                IRecursiveNode s = e.getPrev();

                IRecursiveNode newInHead = newIn.peekFront();
                newInHead.setPrev(s);
                s.setNext(newInHead);

                IRecursiveNode newInTail = newIn.peekBack();
                newInTail.setNext(e);
                e.setPrev(newInTail);

                sharedInternal.incSize(newIn.size());
            }
        }
        else{
            pushBack(newIn);
        }
    }


    @Override
    public IRecursiveNode popFront(){
        if( sharedInternal.top >= 0 ){
            IRecursiveNode victim = sharedInternal.head;
            sharedInternal.head = victim.getNext();
            sharedInternal.head.setPrev(null);
            sharedInternal.decSize();
            return victim;
        }
        else{
            return null;
        }
    }

    @Override
    public IRecursiveNode popBack(){
        if( sharedInternal.top >= 0 ){
            IRecursiveNode victim = sharedInternal.tail;
            sharedInternal.tail = victim.getPrev();
            sharedInternal.tail.setNext(null);
            sharedInternal.decSize();
            return victim;
        }
        else{
            return null;
        }
    }

    @Override
    public IRecursiveNode popIn( int index) {
        if( index == 0 ){
            return this.popFront();
        }
        else if( index == sharedInternal.top ){
            return this.popBack();
        }
        else if(access.seek( index )){
            IRecursiveNode victim = access.curr;
            IRecursiveNode prev = access.curr.getPrev();
            IRecursiveNode next = access.curr.getNext();
            prev.setNext(next);
            next.setPrev(prev);
            access.curr = next;
            sharedInternal.decSize();
            return victim;
        }
        else{
            return null;
        }
    }


    @Override
    public IRecursiveList popFront(int hi) {
        IRecursiveList sublist = peekIn(0, hi);
        removeFront(hi);
        return sublist;
    }

    @Override
    public IRecursiveList popBack(int lo) {
        IRecursiveList sublist = peekIn(lo, sharedInternal.top);
        removeBack(lo);
        return sublist;
    }

    @Override
    public IRecursiveList popIn(int lo, int hi) {
        IRecursiveList sublist = peekIn(lo, hi);
        removeIn(lo, hi);
        return sublist;
    }


    @Override
    public void removeFront(int hi) {
        hi = access.fixNegIndex(hi);

        sharedInternal.head = peekIn(hi).getNext();
        sharedInternal.head.setPrev(null);

        sharedInternal.decSize(1 + hi);
    }

    @Override
    public void removeBack(int lo) {
        lo = access.fixNegIndex(lo);

        sharedInternal.tail = peekIn(lo).getPrev();
        sharedInternal.tail.setNext(null);

        sharedInternal.decSize(1 + sharedInternal.top - lo);
    }

    @Override
    public void removeIn(int lo, int hi) {
        lo = access.fixNegIndex(lo);
        hi = access.fixNegIndex(hi);

        if(lo == 0){
            removeFront(hi);
        }
        else if(hi == sharedInternal.top){
            removeBack(lo);
        }
        else{
            IRecursiveNode s = peekIn(lo);
            IRecursiveNode e = peekIn(hi);
            s.getPrev().setNext(e.getNext());
            e.getNext().setPrev(s.getPrev());

            sharedInternal.decSize(1 + hi - lo);
        }
    }

    @Override
    public void setLevel(int level) {
        sharedExternal.setLevel(level);
    }

    @Override
    public int getLevel() {
        return sharedExternal.getLevel();
    }

    /* =====Access to tree branches, if recursive==================================================================== */

    @Override
    public void pushBelow(IRecursiveList childList, int... params) {
        int level = sharedExternal.getLevel();
        IRecursiveNode target = this.peekIn(params[level]);
        if(level == params.length - 1){
            childList.setLevel(level + 1);
            target.setChildList(childList);
        }
        else{
            target.pushBelow(childList, params);
        }
    }

    @Override
    public IRecursiveList popBelow(int... params) {
        int level = sharedExternal.getLevel();
        IRecursiveNode target = peekIn(params[level]);
        if(level == params.length - 1){
            return target.getChildList();// may be null
        }
        else if(target.isRecursive()){
            return target.popBelow(params);
        }
        return null;
    }

    /* =====Multi-node manipulation, slicing and sub-listing: by index or by object================================== */

    @Override
    public IRecursiveList newList() {
        return new RecursiveList();
    }

    @Override
    public IRecursiveList copyList(){
        IRecursiveList out = newList();

        if(sharedInternal.top >= 0){
            IRecursiveNode s = sharedInternal.head;
            do{
                out.pushBack(s.copyNode());
                s = s.getNext();
            }
            while(s != null);
        }
        return out;
    }

    @Override
    public IRecursiveList reverse() {
        IRecursiveList out = newList();

        if(sharedInternal.top >= 0){
            IRecursiveNode s = sharedInternal.tail;
            do{
                out.pushBack(s.copyNode());
                s = s.getPrev();
            }
            while(s != null);
        }
        return out;
    }


    @Override
    public ArrayList<IRecursiveNode> toFlatArrayList() {
        ArrayList<IRecursiveNode> out = new ArrayList<>();

        if(sharedInternal.top >= 0){
            IRecursiveNode s = sharedInternal.head;
            do{
                out.add(s);
                s = s.getNext();
            }
            while(s != null);
        }
        return out;
    }

    @Override
    public IRecursiveNode[] toFlatArray() {
        IRecursiveNode[] out = new IRecursiveNode[sharedInternal.top+1];

        if(sharedInternal.top >= 0){
            IRecursiveNode s = sharedInternal.head;
            int i = 0;
            do{
                out[i++] = s;
                s = s.getNext();
            }
            while(s != null);

        }
        return out;
    }

    @Override
    public IRecursiveNode[][][] toBreadthFirstArray() {
        if(sharedInternal.top >= 0){
            int maxLevel = this.refreshLevels(0);

            IRecursiveNode[][][] mainOut = new IRecursiveNode[maxLevel+1][][];
            mainOut[0] = new IRecursiveNode[1][];
            toBreadthFirstArray(this, mainOut, 0, 0);
            return mainOut;
        }
        return new IRecursiveNode[0][][];
    }

    protected final void toBreadthFirstArray(IRecursiveList mainIn, IRecursiveNode[][][] mainOut, int i, int j) {
        mainOut[i][j] = new IRecursiveNode[mainIn.size()];
        int k;

        ArrayList<IRecursiveNode> recursiveNodes = new ArrayList<>();
        IRecursiveNode s = mainIn.peekFront();
        k = 0;
        do{
            mainOut[i][j][k] = s;
            if(s.isRecursive() && s.size() > 0){
                recursiveNodes.add(s);
            }
            s = s.getNext();
            k++;
        }
        while(s != null);

        if(!recursiveNodes.isEmpty()){
            mainOut[i+1] = new IRecursiveNode[recursiveNodes.size()][];
            int j0 = 0;
            for(IRecursiveNode recursiveNode : recursiveNodes){
                IRecursiveList childList = recursiveNode.getChildList();
                this.toBreadthFirstArray(childList, mainOut, i + 1, j0);
                j0++;
            }
        }
    }

    @Override
    public int refreshLevels() {
        return this.refreshLevels(0);
    }

    @Override
    public final int refreshLevels(int setLevel){
        this.setLevel(setLevel);
        int maxLevel = setLevel;
        if(sharedInternal.top >= 0){
            IRecursiveNode s = sharedInternal.head;
            do{
                if(s.isRecursive()){
                    maxLevel = Math.max(maxLevel, s.refreshLevels(setLevel + 1));
                }
                s.setLevel(setLevel);
                s = s.getNext();
            }
            while(s != null);
        }
        return maxLevel;
    }

    @Override
    public int refreshTree() {
        int maxLevel = sharedExternal.getLevel();
        //System.out.printf("A refreshTree: %s starting=%d \n", this, maxLevel);
        IRecursiveNode s = sharedInternal.head;
        while(s != null){
            maxLevel = Math.max(maxLevel, s.refreshTree(this, sharedExternal.getLevel() + 1));
            //System.out.printf("A %s maxLevel=%d, %s \n", this, maxLevel, s);
            s = s.getNext();
        }

        return maxLevel;
    }

    @Override
    public int refreshTree(IRecursiveList parentList, int level) {
        sharedExternal.setParentList(parentList);
        sharedExternal.setLevel(level);
        int maxLevel = level;
        //System.out.printf("        B refreshTree: %s starting=%d \n", this, maxLevel);

        IRecursiveNode s = sharedInternal.head;
        while(s != null){
            maxLevel = Math.max(maxLevel, s.refreshTree(this, level + 1));
            //System.out.printf("        B %s maxLevel=%d, %s \n", this, maxLevel, s);
            s = s.getNext();
        }

        return maxLevel;
    }
}
