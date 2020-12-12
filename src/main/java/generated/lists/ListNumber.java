// Generated file, do not edit
// Last write: 12/03/2020 21:23:09
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
        throw new IllegalStateException(String.format("Dev err: unknown datatype: 0x%X", index));
    }
    public static String categoryByBaseIndex (int index) {
        switch (index) {
            case 0x06:
                return "NUM_WORD_HINT";
            default:
                throw new IllegalStateException(String.format("Dev err: unknown datatype: 0x%X", index));
        }
    }
    public static int baseIndexByRange (int index) {
        if (
            (0x07 <= index && index <= 0x08)
        ) {
            return 0x06;
        }
        throw new IllegalStateException(String.format("Dev err: unknown datatype: 0x%X", index));
    }
    public static int offset () {
        return 0x06;
    }
}
