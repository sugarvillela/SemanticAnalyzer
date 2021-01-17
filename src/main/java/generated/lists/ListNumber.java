// Generated file, do not edit
// Last write: 01/13/2021 23:55:00
package generated.lists;

public class ListNumber {
    public static final int NUM_WORD_HINT = 0x06;
    
    public static final int VALUE = 0x07;
    public static final int EE = 0x08;
    
    public static String categoryByRange (int index) {
        if (
            (0x07 <= index && index <= 0x08)
        ) {
            return "NUM_WORD_HINT";
        }
        throw new IllegalStateException("Dev err: unknown datatype");
    }
    public static String categoryByBaseIndex (int index) {
        switch (index) {
            case 0x06:
                return "NUM_WORD_HINT";
            default:
                throw new IllegalStateException("Dev err: unknown datatype");
        }
    }
    public static int baseIndexByRange (int index) {
        if (
            (0x07 <= index && index <= 0x08)
        ) {
            return 0x06;
        }
        throw new IllegalStateException("Dev err: unknown datatype");
    }
    // for monotonic values across different arrays
    public static int offset () {
        return 0x06;
    }
    // for debug or user-friendly display
    public static String nameByIndex (int index) {
        switch (index) {
            case VALUE:
                return "VALUE";
            case EE:
                return "EE";
            default:
                return "none";
        }
    }
}
