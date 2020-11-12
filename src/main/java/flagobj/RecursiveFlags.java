package flagobj;

import listnode.BaseRecursible;
import listnode.ListNode;
import listnode.RecursiveList;

public class RecursiveFlags extends RecursiveList implements IFlags {
    @Override
    public String toString(){
        return "Wheee!";
    }

    @Override
    public BaseRecursible newList() {
        return new RecursiveFlags();
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
