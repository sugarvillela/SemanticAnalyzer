package recursivelist.util;

import recursivelist.FlagNode;
import recursivelist.IRecursiveList;
import recursivelist.IRecursiveNode;
import runstate.RunState;

import java.util.ArrayList;

import static generated.lists.ListScopes.*;
import static generated.lists.ListString.IN;

public class UtilTest {
    public static void testScopeUtil(){
        ScopeUtil scopeUtil = RunState.getScopeUtil();
        FlagNodeUtil flagNodeUtil = RunState.getFlagNodeUtil();

        FlagNode rootNode = RunState.getRootNode();
        rootNode.dispList();

        FlagNode[][] all = scopeUtil.getContainers(ALL);
        flagNodeUtil.dispFlagNodes(all);

        FlagNode[][] some = scopeUtil.getContainers(CHAR);
        flagNodeUtil.dispFlagNodes(some);
    }
    public static void testGroupDownUtil(){
        FlagNodeUtil flagNodeUtil = RunState.getFlagNodeUtil();
        GroupDownUtil groupDownUtil = RunState.getGroupDownUtil();

        FlagNode rootNode = RunState.getRootNode();
        rootNode.dispList();

        FlagNode[] flagArray;

        flagArray = rootNode.toFlatFlagArray();
        flagNodeUtil.dispFlagNodes(flagArray);
        groupDownUtil.groupDown(flagArray, 4, 7);
        rootNode.dispList();

        flagArray = rootNode.toFlatFlagArray();
        flagNodeUtil.dispFlagNodes(flagArray);
    }
    public static void testGroupDownUtil1(){
        ScopeUtil scopeUtil = RunState.getScopeUtil();
        FlagNodeUtil flagNodeUtil = RunState.getFlagNodeUtil();
        GroupDownUtil groupDownUtil = RunState.getGroupDownUtil();

        FlagNode rootNode = RunState.getRootNode();
        rootNode.dispList();
        //System.out.println("rootNode level = " + rootNode.getLevel());
        //System.out.println("childList level = " + rootNode.getChildList().getLevel());
        groupDownUtil.groupDown(rootNode.getChildList(), 6, 9);
//        rootNode.refreshTree();
//        rootNode.dispList();

        groupDownUtil.groupDown(rootNode.getChildList(), 1, 2);
//        rootNode.refreshTree();
//        rootNode.dispList();

        groupDownUtil.groupDown(rootNode.peekIn(5).getChildList(), 1, 2);
        rootNode.refreshTree();
        rootNode.dispList();

        System.out.println("iterator");
        IRecursiveList.ListItr itr = rootNode.getBreadthFirstIterator();
        int i = 0;
        while(itr.hasNext()){
            IRecursiveNode next = itr.next();
            System.out.printf("%d:%d: %s \n", itr.level(), itr.key(), next);
            if(22 < i++){
                break;
            }
        }

        System.out.println("PHRASE");
        FlagNode[][] some = scopeUtil.getContainers(PHRASE);
        flagNodeUtil.dispFlagNodes(some);

        System.out.println("WORD");
        FlagNode[][] words = scopeUtil.getContainers(WORD);
        flagNodeUtil.dispFlagNodes(words);
    }
    public static void makeListForTest(){
        GroupDownUtil groupDownUtil = RunState.getGroupDownUtil();

        FlagNode rootNode = RunState.getRootNode();
//        rootNode.popIn(9);
//        FlagNode newNode = new FlagNode();
//        newNode.set(WORD);
//        newNode.set(IN, "newNode");
//        rootNode.pushIn(9, newNode);
        //rootNode.dispList();
        groupDownUtil.groupDown(rootNode.getChildList(), 6, 9);
        groupDownUtil.groupDown(rootNode.getChildList(), 1, 2);
        //rootNode.dispList();
        groupDownUtil.groupDown(rootNode.peekIn(5).getChildList(), 1, 2);
        rootNode.refreshTree();
        rootNode.dispList();
    }
    public static void test3dAlgos(){
        makeListForTest();
        FlagNode rootNode = RunState.getRootNode();
        rootNode.dispList();
        ScopeUtil scopeUtil = RunState.getScopeUtil();

        FlagNode[][] all = scopeUtil.getContainers(ALL);
        RunState.getFlagNodeUtil().dispFlagNodes(all);
    }
}
