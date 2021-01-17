package recursivelist.util;

import generated.lists.ListScopes;
import recursivelist.FlagNode;
import recursivelist.IRecursiveList;
import recursivelist.IRecursiveNode;

import java.util.ArrayList;

/** Singleton: access instance from here or runState.getScopeUtil()
 *
 *  The idea of scoping is to eliminate loops, instead providing data structures with
 *  the desired elements.
 *
 *  On scopes:
 *    topScope = container for all
 *    bottomScope = the flagNode payload, unreachable by list itr
 *    leafScope = a flagNode without recursive list, lowest item reachable by list itr
 *
 *  To parse a scope, find it, then get its containing list
 */
public class ScopeUtil {
    private static ScopeUtil instance;

    public static ScopeUtil init(FlagNode rootNode){
        return (instance = new ScopeUtil(rootNode));
    }
    public static ScopeUtil getInstance(){
        return instance;
    }

    private final FlagNode rootNode;
    private final int topScope, leafScope, bottomScope;

    private ScopeUtil(FlagNode rootNode) {
        this.rootNode = rootNode;
        topScope = ListScopes.topScope();
        bottomScope = ListScopes.bottomScope();
        leafScope = ListScopes.nextScopeUp(bottomScope);
    }

    public FlagNode[][] getContainers(int targetScope) {
        return (targetScope == topScope) ? getTopContainer() :
                (targetScope == leafScope) ? getLeafContainers(targetScope) :
                        (targetScope == bottomScope) ? getBottomContainers() :
                                getMiddleContainers(targetScope);
    }

    private FlagNode[][] getTopContainer() {
        System.out.println("getTopContainer");
        ArrayList<ArrayList<FlagNode>> mainList = new ArrayList<>();
        {
            ArrayList<FlagNode> currList = new ArrayList<>();
            int prevLevel = -1, prevKey = -1;

            IRecursiveList.ListItr itr = rootNode.getBreadthFirstIterator();
            while (itr.hasNext()) {
                FlagNode next = (FlagNode) itr.next();
                if (
                    (itr.level() != prevLevel || itr.key() < prevKey) && !currList.isEmpty()
                ) {
                    mainList.add(currList);
                    currList = new ArrayList<>();
                }
                currList.add(next);
                prevLevel = itr.level();
                prevKey = itr.key();
            }
            if (!currList.isEmpty()) {
                mainList.add(currList);
            }
        }

        FlagNode[][] outArray = new FlagNode[mainList.size()][];
        int i = 0, j;
        for (ArrayList<FlagNode> currList : mainList) {
            outArray[i] = new FlagNode[currList.size()];
            j = 0;
            for (FlagNode next : currList) {
                outArray[i][j] = next;
                j++;
            }
            i++;
        }
        return outArray;
    }

    private FlagNode[][] getMiddleContainers(int targetScope) {
        System.out.println("getMiddleContainers");
        ArrayList<ArrayList<FlagNode>> mainList = new ArrayList<>();
        {
            ArrayList<FlagNode> currList = new ArrayList<>();
            int prevLevel = -1, prevKey = -1;

            IRecursiveList.ListItr itr = rootNode.getBreadthFirstIterator();
            while (itr.hasNext()) {
                FlagNode next = (FlagNode) itr.next();
                if (next.getBoolean(targetScope)) {
                    if (
                            (itr.level() != prevLevel || (itr.key() - prevKey > 1) || itr.key() < prevKey)
                                    && !currList.isEmpty()
                    ) {
                        mainList.add(currList);
                        currList = new ArrayList<>();
                    }
                    currList.add(next);
                    prevLevel = itr.level();
                    prevKey = itr.key();
                }
            }
            if (!currList.isEmpty()) {
                mainList.add(currList);
            }
        }

        FlagNode[][] outArray = new FlagNode[mainList.size()][];
        int i = 0, j;
        for (ArrayList<FlagNode> currList : mainList) {
            outArray[i] = new FlagNode[currList.size()];
            j = 0;
            for (FlagNode next : currList) {
                outArray[i][j] = next;
                j++;
            }
            i++;
        }
        return outArray;
    }

    private FlagNode[][] getLeafContainers(int targetScope) {
        //System.out.println("getLeafContainers");
        ArrayList<IRecursiveList> containers = new ArrayList<>();

        IRecursiveList.ListItr itr = rootNode.getDepthFirstIterator();
        while (itr.hasNext()) {
            FlagNode next = (FlagNode) itr.next();
            //System.out.println("next: " + next);
            //System.out.println("    clue: " + next.getParentList().size());
            if (!containers.contains(next.getParentList()) && next.getBoolean(targetScope)) {
                //System.out.println("    added parent list");
                containers.add(next.getParentList());
            }
        }
        //System.out.println("containers size = " + containers.size());
        //Commons.disp(containers, "containers");
        FlagNode[][] out = new FlagNode[containers.size()][];
        int i = 0, j;

        for (IRecursiveList container : containers) {
            ArrayList<IRecursiveNode> flatArrayList = container.toFlatArrayList();
            out[i] = new FlagNode[flatArrayList.size()];
            j = 0;
            for (IRecursiveNode node : flatArrayList) {
                out[i][j] = (FlagNode) node;
                j++;
            }
            i++;
        }

        return out;
    }

    public FlagNode[][] getBottomContainers() {
        //System.out.println("getBottomContainers");
        ArrayList<FlagNode[]> containers = new ArrayList<>();
        IRecursiveList.ListItr itr = rootNode.getDepthFirstIterator();
        while (itr.hasNext()) {
            FlagNode next = (FlagNode) itr.next();
            //System.out.println("next: " + next);
            if (next.getBoolean(leafScope)) {
                //System.out.println("added");
                containers.add(new FlagNode[]{next});
            }
        }
        return containers.toArray(new FlagNode[0][]);
    }

    private FlagNode[][] getMiddleContainers1(int targetScope) {
        System.out.println("getMiddleContainers");
        ArrayList<IRecursiveList> containers = new ArrayList<>();

        IRecursiveList.ListItr itr = rootNode.getBreadthFirstIterator();
        while (itr.hasNext()) {
            FlagNode next = (FlagNode) itr.next();
            if (next.getBoolean(targetScope) && !containers.contains(next.getParentList())) {
                containers.add(next.getParentList());
            }
        }
        //Commons.disp(containers, "containers");
        FlagNode[][] out = new FlagNode[containers.size()][];
        int i = 0, j;

        for (IRecursiveList container : containers) {
            ArrayList<IRecursiveNode> flatArrayList = container.toFlatArrayList();
            out[i] = new FlagNode[flatArrayList.size()];
            j = 0;
            for (IRecursiveNode node : flatArrayList) {
                out[i][j] = (FlagNode) node;
                j++;
            }
            i++;
        }

        return out;
    }
}
