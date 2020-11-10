package listnode;

public class RecursiveList <T extends ItrList> extends ItrList{// <T extends ItrList>
    public RecursiveList(){
        super();
    }

    @Override
    public ItrList sublist(Object criteria){
//        ItrList<T> out = newList();
//        this.clearRange();
//        this.rewind();
//        while(this.hasNext()){
//            ListNode<T> next = this.next();
//            if(this.meetsCriteria(next, criteria))
//                out.pushBack(next.copy());
//        }
//        return out;
        return null;
    }
    @Override
    public boolean meetsCriteria(ListNode item, Object criteria) {
        return false;
    }

    @Override
    public ItrList newList() {
        return new RecursiveList();
    }

    @Override
    public void disp(){
        System.out.println("\nDisplay RecursiveList:");
        this.clearRange();
        this.rewind();
        while(this.hasNext()){
            IRecursible item = (IRecursible)this.next().payload;
            if(item.recursible()){
                ((ItrList)item).disp();
            }
            else{
                System.out.println(rowi + ": " + item);
            }
        }
        System.out.println("End display RecursiveList\n");
    }
}
