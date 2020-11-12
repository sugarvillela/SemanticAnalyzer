// Generated file, do not edit
// Last write: 11/12/2020 10:33:55
package generated.lists;

public class ListNumber {
    public static final int INT = 0x0F;
    
    public static final int NUM_VAL = 0x010;
    public static final int INDEX = 0x011;
    public static final int GROUP = 0x012;
    public static final int EE = 0x013;
    
    public static String categoryByRange (int index) {
        if (
            0x010 <= index && index <= 0x013
        ) {
            return "INT";
        }
        return null;
    }
    public static String categoryByBaseIndex (int index) {
        switch (index) {
            case 0x0F:
                return "INT";
            default:
                return null;
        }
    }
    public static int baseIndexByRange (int index) {
        if (
            0x010 <= index && index <= 0x013
        ) {
            return 0x0F;
        }
        return -1;
    }
    public static int offset () {
        return 0x0F;
    }
}
