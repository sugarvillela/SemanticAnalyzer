package listnode;

public abstract class RecursiveList <T extends BaseRecursible> extends BaseRecursible {// <T extends ItrList>
    public RecursiveList(){
        super();
    }

//    @Override
//    public BaseRecursible sublist(Object criteria){
////        ItrList<T> out = newList();
////        this.clearRange();
////        this.rewind();
////        while(this.hasNext()){
////            ListNode<T> next = this.next();
////            if(this.meetsCriteria(next, criteria))
////                out.pushBack(next.copy());
////        }
////        return out;
//        return null;
//    }



    @Override
    public void disp(){
        System.out.println("\nDisplay RecursiveList:");
        this.clearRange();
        this.rewind();
        while(this.hasNext()){
            IRecursible item = (IRecursible)this.next().payload;
            if(item.recursible()){
                ((RecursiveList)item).disp();
            }
            else{
                System.out.println(rowi + ": " + item);
            }
        }
        System.out.println("End display RecursiveList\n");
    }
}
