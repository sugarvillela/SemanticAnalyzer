package flagobj;

public interface IFlags {
//    boolean getBool(int enu);
//    int getDiscrete(int enu);
//    int getNumber(int enu);
//    String getString(int enu);
    Object getObject(int enu);
    boolean test(IFlags testObj);
}
