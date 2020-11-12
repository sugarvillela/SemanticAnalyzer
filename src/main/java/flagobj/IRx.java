package flagobj;

public interface IRx {
    boolean test(IFlags testNode);
    void setLo(int lo);
    void setHi(int hi);
    int getLo();
    int getHi();
    Object getObj();

}
