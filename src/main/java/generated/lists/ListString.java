// Generated file, do not edit
// Last write: 12/03/2020 21:23:09
package generated.lists;

public class ListString {
    public static final int TEXT = 0x00;
    
    public static final int ORIG = 0x01;
    public static final int IN = 0x02;
    public static final int ROOT = 0x03;
    public static final int EXPAND1 = 0x04;
    public static final int EXPAND2 = 0x05;
    
    public static String categoryByRange (int index) {
        if (
            (0x01 <= index && index <= 0x05)
        ) {
            return "TEXT";
        }
        throw new IllegalStateException(String.format("Dev err: unknown datatype: 0x%X", index));
    }
    public static String categoryByBaseIndex (int index) {
        switch (index) {
            case 0x00:
                return "TEXT";
            default:
                throw new IllegalStateException(String.format("Dev err: unknown datatype: 0x%X", index));
        }
    }
    public static int baseIndexByRange (int index) {
        if (
            (0x01 <= index && index <= 0x05)
        ) {
            return 0x00;
        }
        throw new IllegalStateException(String.format("Dev err: unknown datatype: 0x%X", index));
    }
    public static int offset () {
        return 0x00;
    }
}
