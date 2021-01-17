package recursivelist;

public class SharedExternalState {
    private IRecursiveList parentList;
    private int level;

    public SharedExternalState(){
        this.parentList = null;
        this.level = 0;
    }
    public SharedExternalState(IRecursiveList parentList, int level){
        this.parentList = parentList;
        this.level = level;
    }

    public IRecursiveList getParentList() {
        return parentList;
    }

    public void setParentList(IRecursiveList parentList) {
        this.parentList = parentList;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean haveParent(){
        return parentList != null;
    }
}
