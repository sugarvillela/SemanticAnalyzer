package recursivelist.util;

import recursivelist.IRecursiveList;
import recursivelist.IRecursiveNode;

import java.util.ArrayList;

public class SublistUtil {
    private static SublistUtil instance;

    public static void init(IRecursiveList rootList){
        instance = new SublistUtil(rootList);
    }
    public static SublistUtil getInstance(){
        return instance;
    }

    private final IRecursiveList rootList;

    private SublistUtil(IRecursiveList rootList){
        this.rootList = rootList;
    }



    public void parseAll(IRecursiveList list){
        list.clearRange();
        list.rewind();
        int count = 0;
        while(list.hasNext()){
            if(list.next().isRecursive()){
                count++;
            }
        }
        IRecursiveList[] out = new IRecursiveList[count];
        list.rewind();
        int i = 0;
        while(list.hasNext()){
            IRecursiveNode next = list.next();
            if(next.isRecursive()){
                out[i++] = next.getChildList();
            }

        }
        //return out;
    }

    public IRecursiveList[] seekScope(int targetScope){
        ArrayList<IRecursiveList> out = new ArrayList<>();
        this.seekScope(targetScope, out, rootList);
        return out.toArray(new IRecursiveList[0]);
    }
    private void seekScope(int targetScope, ArrayList<IRecursiveList> out, IRecursiveList list){
        list.clearRange();
        list.rewind();
        boolean found = false;
        while(list.hasNext()){
            IRecursiveNode node = list.next();
            if(node.getBoolean(targetScope)){

                System.out.println("found: " + node.toString());
                found = true;
                break;
            }
            else{
                System.out.println("nope : " + node.toString());
            }
        }
        if(found){
            out.add(list);
        }
        list.rewind();
        while(list.hasNext()){
            IRecursiveNode node = list.next();
            if(node.isRecursive()){
                seekScope(targetScope, out, node.getChildList());
            }
        }
    }
}
