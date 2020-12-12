package recursivelist.factories;

import generated.lists.ListScopes;
import recursivelist.FlagNode;
import recursivelist.IRecursiveList;
import recursivelist.IRecursiveNode;
import recursivelist.RecursiveList;
import toktools.Tokenizer;

import static generated.lists.ListScopes.ALL;
import static generated.lists.ListString.IN;

public class NodeFactory {
    private static NodeFactory instance;

    public static NodeFactory getInstance(){
        return (instance == null)? (instance = new NodeFactory()) : instance;
    }

    private NodeFactory(){}

    public IRecursiveNode initRootNode(String text){
        int topScope = ListScopes.topScope();
        int wordScope = ListScopes.nextScopeUp(
            ListScopes.bottomScope()
        );

        IRecursiveList list = stringToFlatList(text, wordScope);

        IRecursiveNode rootNode = new FlagNode();
        rootNode.set(topScope);
        rootNode.setChildList(list);
        return rootNode;
    }

    public IRecursiveList stringToFlatList(String text, int scope){

        Tokenizer tok = new Tokenizer();
        String[] content = tok.toArr(text);

        IRecursiveList list = new RecursiveList();
        for(String s : content){
            IRecursiveNode node = new FlagNode();
            node.set(IN, s);
            node.set(scope);
            list.pushBack(node);
        }
        return list;
    }

}
