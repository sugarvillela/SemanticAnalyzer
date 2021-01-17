package recursivelist;

import commons.Commons;
import toktools.TokenizerSimple;

import static generated.lists.ListString.IN;

public class FlagNodeTest {
    public static IRecursiveList makeListForTest(String text){
        TokenizerSimple tok = new TokenizerSimple();
        String[] content = tok.getArray(text);

        IRecursiveList list = new RecursiveList();
        for(String s : content){
            FlagNode node = new FlagNode();
            node.set(IN, s);
            list.pushBack(node);
        }
        return list;
    }
    public static void testLoad(){
        String text = "This is a sentence   with a space and a period.  ";
        FlagNode flagNode = new FlagNode();
        flagNode.setChildList(makeListForTest(text));
        flagNode.dispStore();
    }

    public static IRecursiveList getList0(){
        String text = "A0 B0 C0 D0 E0";//"zero one two three four five six seven eight nine";
        IRecursiveList list = makeListForTest(text);
        //list.disp();
        return list;
    }
    public static IRecursiveList getList1(){
        String text = "A1 B1 C1 D1 E1";
        IRecursiveList list = makeListForTest(text);
        //list.disp();
        return list;
    }
    public static IRecursiveList getList2(){
        String text = "A2 B2 C2 D2 E2";
        IRecursiveList list = makeListForTest(text);
        //list.disp();
        return list;
    }
    public static IRecursiveList getList3(){
        String text = "A3 B3 C3 D3 E3";
        IRecursiveList list = makeListForTest(text);
        //list.disp();
        return list;
    }
    public static IRecursiveList getList4(){
        String text = "A4 B4 C4 D4 E4";
        IRecursiveList list = makeListForTest(text);
        //list.disp();
        return list;
    }
    public static void testListAdvanced(){
        IRecursiveList list = getList0();

        IRecursiveList sublist;
        sublist = list.peekIn(-7, -3);
        //sublist = list.reverse();

        sublist.dispList();
    }
    public static void testToArray(){
        IRecursiveList list = getList0();
        //ArrayList array = list.toArrayList();
        IRecursiveNode[] array = list.toFlatArray();
        Commons.disp(array);
    }
    public static void nodeEquality(){
        IRecursiveList list = getList0();
        IRecursiveNode s = list.peekIn(2);
        IRecursiveNode e = s.copyNode();
        System.out.println("copy nodes");
        System.out.println(s);
        System.out.println(e);
        System.out.println(s.equals(e));

        s = new FlagNode();
        System.out.println(s.equals(e));
        e = s.copyNode();
        System.out.println(s.equals(e));

        //IRecursiveList.FlatItr flatItr = list.getIterator();
    }
    public static void testListAdvanced1(){
        IRecursiveList list = getList0();
//        IRecursiveList list2 = getList2();
//        list2.disp();
//        //list.pushFront(list2);
//        list.pushIn(3, list2);
//        //list.pushBack(list2);
//        list.disp();
        //System.out.println(list);

        IRecursiveNode noExists = new FlagNode();
        IRecursiveNode exists = list.peekIn(3);
        IRecursiveNode exists2 = list.peekIn(8);
        System.out.println(list.peekIn(exists, exists2));

//        exists.set(IN, "Scrappy");
//        list.disp();
//        noExists.set(IN, "fluffy");


//        noExists.set(IN, "fluffy");
//        System.out.println(list.peekBack(noExists));

//        System.out.println(list.indexOf(exists));
//        System.out.println(list.seek(exists));
//        System.out.println(list.seek(5));
//
//        System.out.println(list.indexOf(noExists));
//        System.out.println(list.seek(noExists));
//        //System.out.println(list.seek(22));    error, if enabled
//
//        System.out.println(list.peekFront());
//        System.out.println(list.peekIn(6));
//        System.out.println(list.peekBack());
//
//        System.out.println(list.peekFront(3));
//        System.out.println(list.peekIn(3,15));
//        System.out.println(list.peekBack(-3));

//        System.out.println(list.popFront(3));
//        System.out.println(list.popIn(3,7));
//        System.out.println(list.popBack(-3));
//        System.out.println(list);

//        IRecursiveList copy = list.copy();
//        copy.pushIn(3, exists);
//        copy.pushIn(5, noExists);
//        System.out.println(copy);




//        list.pushBack(copy);
//        System.out.println(list);
//

//        System.out.println();
//
//        IRecursiveList sublist;
//        IRecursiveNode s = list.peekIn(3);
//        IRecursiveNode e = list.peekIn(-2);
//        System.out.println("start, end nodes:");
//        System.out.println(s);
//        System.out.println(e);
//        System.out.println(s.equals(e));
//
//        sublist = list.peekIn(s, e);
//
//        sublist.disp();
    }
    public static void testPopMulti(){
        IRecursiveList list = getList0();

        IRecursiveList sublist;

        //System.out.println("start, end nodes:");

        System.out.println("BEFORE, size = " + list.size());
        sublist = list.popIn(4, 9);
        System.out.println("AFTER, size = " + list.size());
        list.dispList();
        sublist.dispList();
    }
    public static void testPushPop(){
        IRecursiveList list = getList0();
        FlagNode node = new FlagNode();
        node.set(IN, "Wowee!!!");
        list.pushIn(5, node);

        list.dispList();
    }
    public static void testItr(){
        IRecursiveList list = getList0();
        IRecursiveList.ListItr itr = list.getFlatIterator();
        itr.setRange(2, -2, -1);
        itr.rewind();
        while(itr.hasNext()){
            System.out.println(itr.key() + ": " + itr.next());
        }

        //System.out.println("changing size before");
        //list.popFront();

        System.out.println("\nIterate again");
        //itr.iterateBack();
        itr.clearRange();
        itr.rewind();
        while(itr.hasNext()){
            System.out.println(itr.key() + ":" + itr.next() + ":      " + itr.peekNext());
//            if(itr.key() == 5){
//                System.out.println("changing size");
//                list.popFront();
//            }
        }
        System.out.println("changing size after");
        list.popFront();

    }
    public static void testDepthFirstItr(){
        IRecursiveList list = getList0();
        IRecursiveList list2 = getList2();
        IRecursiveList list3 = getList3();
        //IRecursiveNode node = list.peekIn(1);
        list.pushBelow(list2, 1);
        list.pushBelow(list3, 2);

        list.dispList();

        //System.out.println("retrieve: " + list.popBelow(1, 3));

        IRecursiveList.ListItr itr = list.getDepthFirstIterator();
        while(itr.hasNext()){
            System.out.println(itr.level() + ": " + itr.key() + ": " + itr.next());
        }
    }
    public static void dispBreadthFirstArray(IRecursiveNode[][][] arr){
        int i = 0, j, k;
        for(IRecursiveNode[][] outerArray : arr){       //level
            System.out.println();
            j = 0;
            for(IRecursiveNode[] innerArray : outerArray){  //num recursive nodes
                System.out.printf("\t%d:%d: ", i, j);
                k = 0;
                for(IRecursiveNode item : innerArray){      //size of childList
                    System.out.printf("%d%s, ", k, item);
                    k++;
                }
                j++;
            }
            i++;
        }
    }
    public static void testBreadthFirstArray(){
        IRecursiveList list = getList0();
        IRecursiveList list1 = getList1();
        IRecursiveList list2 = getList2();
        IRecursiveList list3 = getList3();
        IRecursiveList list4 = getList4();

        list.pushBelow(list1, 1);
        list.pushBelow(list2, 3);
        list.pushBelow(list3, 1, 2 );
        list.pushBelow(list4, 1, 2, 3);

        list.dispList();
        dispBreadthFirstArray(list.toBreadthFirstArray());
//        IRecursiveList.ListItr itr = list.getDepthFirstIterator();
//
//        int i = -1;
//        while(itr.hasNext()){
//            System.out.println(itr.level() + ": " + itr.key() + ": " + itr.next());
//            if(++i >=50){
//                break;
//            }
//        }
    }
}
