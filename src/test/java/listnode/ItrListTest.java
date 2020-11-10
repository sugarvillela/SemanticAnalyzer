package listnode;

import flagobj.Flags;
import flagobj.IFlags;
import toktools.Tokenizer;

public class ItrListTest {
    private static class Itr <T> extends ItrList{
        public Itr(){
            super();
        }
        public Itr(T[] array){
            this();
            for(T item : array){
                this.pushBack(new ListNode<>(item));
            }
        }

        @Override
        public boolean meetsCriteria(ListNode item, Object criteria) {
            return item.payload.equals(criteria);
        }

        @Override
        public ItrList newList() {
            return new Itr();
        }
    }

    private static FlatList<String> getStringList(String text){
        Tokenizer tok = new Tokenizer();
        String[] toks = tok.toArr(text);
        commons.Commons.disp(toks, "Tokenized");
        return new FlatList<>(toks);
    }
    private static FlatList<Integer> getIntegerList(){
        Integer[] array = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        commons.Commons.disp(array, "int array");
        return new FlatList<>(array);
    }

    public static void testItr(){
        ItrList<String> list = getStringList("This is a sentence   with a space and a period.  ");
        list.disp();
    }
    public static void testItrManip(){
        ItrList<String> list = getStringList("This is a sentence   with a space and a period.  ");
        list.pushIn(6, new ListNode<>("redundant"));
        list.pushIn(3, new ListNode<>("long"));
        list.disp();

        System.out.println("pop: " + list.popBack());
        list.pushBack(new ListNode<>("comma,"));
        System.out.println("peekFront: " + list.peekFront());
        System.out.println("peekBack: " + list.peekBack());
        System.out.println("pop 3: " + list.popIn(3));
        list.disp();
    }
    public static void testSublist(){
        ItrList<String> list = getStringList("This is a sentence   with a space and a period.  ");
        ItrList<String> sublist = list.sublist(2, 5);
        sublist.disp();
    }
    public static void testSublistCriteria(){
        ItrList<String> list = getStringList("This is a sentence   with a space and a period.  ");
        ItrList<String> sublist = list.sublist("a");
        sublist.disp();
    }
    public static void testItrInteger(){
        ItrList<Integer> list = getIntegerList();
        list.disp();

        System.out.println("pop: " + list.popBack());
        list.pushBack(new ListNode<>(222));
        System.out.println("peekFront: " + list.peekFront());
        System.out.println("peekBack: " + list.peekBack());
        System.out.println("pop 3: " + list.popIn(3));
        list.disp();
    }

    public static void testRecursiveList(){
        FlatList<String> put0 = getStringList("This is a sentence   with a space and a period.  ");
        FlatList<String> put1 = getStringList("Howdy there pardner");
        FlatList<String> put2 = getStringList("Munchkins are taking over");
        FlatList<String> put3 = new FlatList<>();

        RecursiveList<ItrList> list = new RecursiveList<>();
        list.pushBack(new ListNode<>(put0));
        list.pushBack(new ListNode<>(put1));
        list.pushBack(new ListNode<>(put2));
        list.disp();
    }
    public static void testFlagObj(){
        FlatList<String> put0 = getStringList("This is a sentence   with a space and a period.  ");
        IFlags obj1 = new Flags();
        RecursiveList<ItrList> list = new RecursiveList<>();
        list.pushBack(new ListNode<>(put0));
        list.pushBack(new ListNode<>(obj1));
        list.disp();
    }
}
