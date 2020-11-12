package flagobj;

import listnode.BaseRecursible;
import listnode.ListNode;
import listnode.NonRecursiveList;

public class Flags extends NonRecursiveList implements IFlags {
    @Override
    public String toString(){
        return "Wheee!";
    }
    @Override
    public void disp(){
        System.out.println(this.toString());
    }

    @Override
    public BaseRecursible newList() {
        return new Flags();
    }
    @Override
    public boolean meetsCriteria(ListNode item, Object criteria) {
        return false;
    }

    @Override
    public Object getObject(int enu) {
        return null;
    }

    @Override
    public boolean test(IFlags testObj) {
        return false;
    }
}
