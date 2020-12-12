package recursivelist;

public class CharList implements IRecursiveList {
    private final String text;
    private int currIndex;

    public CharList(String text) {
        if(text.isEmpty()){
            throw new IllegalStateException("dev err: empty string in charList");
        }
        this.text = text;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean seek(int index) {
        return false;
    }

    @Override
    public IRecursiveNode peekFront() {
        return null;
    }

    @Override
    public IRecursiveNode peekBack() {
        return null;
    }

    @Override
    public IRecursiveNode peekIn(int index) {
        return null;
    }

    @Override
    public void pushFront(IRecursiveNode newHead) {}

    @Override
    public void pushBack(IRecursiveNode newTail) {}

    @Override
    public void pushIn(int index, IRecursiveNode node) {}

    @Override
    public IRecursiveNode popFront() {
        return null;
    }

    @Override
    public IRecursiveNode popBack() {
        return null;
    }

    @Override
    public IRecursiveNode popIn(int index) {
        return null;
    }

    @Override
    public void setRange(int start, int end) {}

    @Override
    public void setRange(int start, int end, int increment) {}

    @Override
    public void clearRange() {}

    @Override
    public void rewind() {}

    @Override
    public int key() {
        return 0;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public IRecursiveNode next() {
        return null;
    }

    @Override
    public IRecursiveList sublist(int lo, int hi) {
        return null;
    }

    @Override
    public IRecursiveList[] scopeDownLists() {
        return null;
    }

    @Override
    public IRecursiveList newList() {
        return null;
    }

    @Override
    public IRecursiveNode[] toArray(IRecursiveNode[] emptyArray) {
        return new IRecursiveNode[0];
    }

    @Override
    public boolean isRecursive() {
        return false;
    }

    @Override
    public void disp() {}
}
