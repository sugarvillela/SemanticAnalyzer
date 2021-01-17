package recursivelist.util;

import generated.lists.ListScopes;
import recursivelist.FlagNode;
import recursivelist.IRecursiveList;
import recursivelist.IRecursiveNode;

import static generated.lists.ListScopes.SCOPES;

/** Singleton: access instance from here or runState.getGroupDownUtil() */
public class GroupDownUtil {
    private static GroupDownUtil instance;

    public static GroupDownUtil init(){
        return (instance == null)? (instance = new GroupDownUtil()) : instance;
    }
    public static GroupDownUtil getInstance(){
        return instance;
    }

    private GroupDownUtil(){}

    public void groupDown(FlagNode[] flagArray, int lo, int hi) {
        if (lo == hi) {
            groupDownOne(flagArray, lo);
        } else {
            groupDownMany(flagArray, lo, hi);
        }
    }

    private void groupDownMany(FlagNode[] flagArray, int lo, int hi) {
        groupDownMany(flagArray[lo].getParentList(), lo, hi);
    }

    private void groupDownOne(FlagNode[] flagArray, int index) {
        groupDownOne(flagArray[index].getParentList(), index);
    }

    public void groupDown(IRecursiveList parentList, int lo, int hi) {
        if (lo == hi) {
            groupDownOne(parentList, lo);
        } else {
            groupDownMany(parentList, lo, hi);
        }
    }

    private void groupDownMany(IRecursiveList parentList, int lo, int hi) {
        IRecursiveList popped = parentList.popIn(lo, hi);
//        System.out.println("POPPED");
//        popped.dispList();
//        System.out.println("REMAINING");
//        parentList.dispList();
        FlagNode newNode = new FlagNode(parentList, parentList.getLevel());
        newNode.setChildList(popped);

        int oldScope = ((FlagNode) popped.peekFront()).getState(SCOPES);
        int newScope = ListScopes.nextScopeUp(oldScope);
        newNode.set(newScope);

        parentList.pushIn(lo, newNode);
    }

    private void groupDownOne(IRecursiveList parentList, int index) {
        IRecursiveNode popped = parentList.popIn(index);

        FlagNode newNode = new FlagNode(parentList, parentList.getLevel());
        newNode.makeRecursive();
        newNode.pushBack(popped);
        newNode.refreshTree();

        int oldScope = ((FlagNode) popped.peekFront()).getState(SCOPES);
        int newScope = ListScopes.nextScopeUp(oldScope);
        newNode.set(newScope);

        parentList.pushIn(index, newNode);
    }
}
