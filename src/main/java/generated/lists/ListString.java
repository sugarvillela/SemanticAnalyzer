// Generated file, do not edit
// Last write: 11/18/2020 16:36:34
package generated.lists;

public class ListString {
    public static final int NAMES = 0x00;
    
    public static final int DAVE = 0x01;
    public static final int IRVING = 0x02;
    public static final int LUPE = 0x03;
    public static final int MORK = 0x04;
    public static final int DONOVAN = 0x05;
    public static final int LARS = 0x06;
    
    public static final int TEXT = 0x07;
    
    public static final int IN = 0x08;
    public static final int ORIG = 0x09;
    public static final int ALT = 0x0A;
    public static final int EXPAND1 = 0x0B;
    public static final int EXPAND2 = 0x0C;
    public static final int NAME = 0x0D;
    public static final int SURNAME = 0x0E;
    
    public static String categoryByRange (int index) {
        if (
            0x01 <= index && index <= 0x06
        ) {
            return "NAMES";
        }
        if (
            0x08 <= index && index <= 0x0E
        ) {
            return "TEXT";
        }
        throw new IllegalStateException("Dev err: unknown datatype");
    }
    public static String categoryByBaseIndex (int index) {
        switch (index) {
            case 0x00:
                return "NAMES";
            case 0x07:
                return "TEXT";
            default:
                throw new IllegalStateException("Dev err: unknown datatype");
        }
    }
    public static int baseIndexByRange (int index) {
        if (
            0x01 <= index && index <= 0x06
        ) {
            return 0x00;
        }
        if (
            0x08 <= index && index <= 0x0E
        ) {
            return 0x07;
        }
        throw new IllegalStateException("Dev err: unknown datatype");
    }
    public static int offset () {
        return 0x00;
    }
}
