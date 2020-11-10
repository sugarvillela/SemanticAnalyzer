package listnode;

/** For payloads:
 * @param <T> Wrapped primitives and non-ItrList objects */
public class FlatList <T> extends ItrList{
    public FlatList(){
        super();
    }
    public FlatList(T[] array){
        super();
        for(T item : array){
            this.pushBack(new ListNode<>(item));
        }
    }

    @Override
    public boolean meetsCriteria(ListNode item, Object criteria) {
        return item.payload.equals(criteria);
    }

    @Override
    public ItrList newList() {
        return new FlatList();
    }
}
