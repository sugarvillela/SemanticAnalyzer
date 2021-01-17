// Generated file, do not edit
// Last write: 01/13/2021 23:55:00
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
        throw new IllegalStateException("Dev err: unknown datatype");
    }
    public static String categoryByBaseIndex (int index) {
        switch (index) {
            case 0x00:
                return "TEXT";
            default:
                throw new IllegalStateException("Dev err: unknown datatype");
        }
    }
    public static int baseIndexByRange (int index) {
        if (
            (0x01 <= index && index <= 0x05)
        ) {
            return 0x00;
        }
        throw new IllegalStateException("Dev err: unknown datatype");
    }
    // for monotonic values across different arrays
    public static int offset () {
        return 0x00;
    }
    // for debug or user-friendly display
    public static String nameByIndex (int index) {
        switch (index) {
            case ORIG:
                return "ORIG";
            case IN:
                return "IN";
            case ROOT:
                return "ROOT";
            case EXPAND1:
                return "EXPAND1";
            case EXPAND2:
                return "EXPAND2";
            default:
                return "none";
        }
    }
}
