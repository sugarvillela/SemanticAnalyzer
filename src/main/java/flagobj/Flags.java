package flagobj;

import listnode.IRecursible;

public class Flags implements IRecursible, IFlags {
    @Override
    public String toString(){
        return "Wheee!";
    }

    @Override
    public boolean recursible() {
        return false;
    }

    @Override
    public void disp(){
        System.out.println(this.toString());
    }
}
