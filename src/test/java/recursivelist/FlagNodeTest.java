package recursivelist;

import recursivelist.factories.NodeFactory;
import recursivelist.util.SublistUtil;

import java.util.ArrayList;

import static generated.lists.ListScopes.*;
import static generated.lists.ListString.IN;

public class FlagNodeTest {
    public static void testLoad(){
        String text = "This is a sentence   with a space and a period.  ";
        IRecursiveNode flagNode = NodeFactory.getInstance().initRootNode(text);
        flagNode.disp();
    }

    public static void testSublist(){
        String text = "Sentence with (very_special_and_interesting) sub-sentence included";
        String subSentence = "very special and interesting";
        IRecursiveNode flagNode = NodeFactory.getInstance().initRootNode(text);
        IRecursiveList rootList = flagNode.getChildList();

        IRecursiveNode node = rootList.peekIn(2);
        node.drop(IN);
        node.set(PHRASE);
        node.setChildList(NodeFactory.getInstance().stringToFlatList(subSentence, WORD));

        SublistUtil.init(rootList);
        SublistUtil util = SublistUtil.getInstance();

        IRecursiveList[] out = util.seekScope(WORD);
        System.out.println("out length: " + out.length);
        for(IRecursiveList list : out){
            list.disp();
        }
    }
}
