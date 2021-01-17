package recursivelist.util;

import generated.lists.ListScopes;
import recursivelist.*;
import toktools.TokenizerSimple;

import static generated.lists.ListString.IN;

/** Singleton: access instance from here or runState.getFlagNodeUtil() */
public class FlagNodeUtil {
    private static FlagNodeUtil instance;

    public static FlagNodeUtil init(){
        return (instance == null)? (instance = new FlagNodeUtil()) : instance;
    }
    public static FlagNodeUtil getInstance(){
        return instance;
    }
    private FlagNodeUtil() {}

    /** Primary way to initialize root node from text input.
     *  Tokenize on space.
     *  Root node given scope ALL (or root); list elements given scope WORD (or leaf).
     * @param text user input
     * @return FlagNode--RecursiveList with one recursion. */
    public FlagNode initRootNode(String text){
        int topScope = ListScopes.topScope();
        int leafScope = ListScopes.nextScopeUp(ListScopes.bottomScope());

        TokenizerSimple tok = new TokenizerSimple();
        String[] content = tok.getArray(text);

        FlagNode rootNode = new FlagNode();// top level constructor
        rootNode.set(topScope);
        rootNode.makeRecursive();

        for(String s : content){
            FlagNode node = new FlagNode(rootNode, 1);// subsequent level constructor
            node.set(IN, s);
            node.set(leafScope);
            rootNode.pushBack(node);
        }
        return rootNode;
    }

    /** Simple 1-d display
     * @param flagArray 1-d array */
    public void dispFlagNodes(FlagNode[] flagArray){
        System.out.println("dispFlagNodes 1-d: size " + flagArray.length);
        int inner = 0;
        for(FlagNode node : flagArray){
            System.out.printf("%d: %s \n", inner, node.toString());
            inner++;
        }
        System.out.println("End dispFlagNodes 1-d \n");
    }

    /** To be called by 2-d display for 1-d sub-display
     * @param flagArray 1-d array
     * @param outer index from which the current array came */
    private void dispFlagNodes(FlagNode[] flagArray, int outer){
        int inner = 0;
        for(FlagNode node : flagArray){
            System.out.printf("    %d: %d: %s \n", outer, inner, node.toString());
            inner++;
        }
    }

    /** 2-d display, where outer is cases and inner is sequence
     * @param containers 2-d array */
    public void dispFlagNodes(FlagNode[][] containers){
        System.out.println("dispFlagNodes 2-d: size " + containers.length);
        int outer = 0;
        for(FlagNode[] container : containers){
            dispFlagNodes(container, outer);
            outer++;
            System.out.println();
        }
        System.out.println("End dispFlagNodes 2-d \n");
    }

    /** 2-d display, where outer is a 3-d container and inner is cases
     * @param containers 2-d array */
    public void dispFlagNodes(FlagNode[][] containers, int outer){
        System.out.println("  " + outer);
        int inner = 0;
        for(FlagNode[] container : containers){
            dispFlagNodes(container, inner);
            inner++;
        }
    }

    /** 3-d display, where outer is cases and inner is sequence
     * @param containers 3-d array */
    public void dispFlagNodes(FlagNode[][][] containers){
        System.out.println("dispFlagNodes 3-d: size " + containers.length);
        int outer = 0;
        for(FlagNode[][] container : containers){
            dispFlagNodes(container, outer);
            outer++;
            System.out.println();
        }
        System.out.println("End dispFlagNodes 3-d \n");
    }

}
