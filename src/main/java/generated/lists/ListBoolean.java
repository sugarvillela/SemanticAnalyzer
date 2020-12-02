// Generated file, do not edit
// Last write: 11/18/2020 16:36:34
package generated.lists;

public class ListBoolean {
    public static final int STATE = 0x020000000;
    
    public static final int MORE = 0x020000001;
    public static final int DONE = 0x020000002;
    
    public static final int COLORS = 0x030000000;
    
    public static final int RED = 0x030000001;
    public static final int BLUE = 0x030000002;
    public static final int GREEN = 0x030000004;
    public static final int ORANGE = 0x030000008;
    public static final int FUSCIA = 0x030000010;
    public static final int PURPLE = 0x030000020;
    public static final int VIOLET = 0x030000040;
    
    public static final int POS = 0x040000000;
    
    public static final int VERB = 0x040000001;
    public static final int NOUN = 0x040000002;
    public static final int ADJECTIVE = 0x040000004;
    public static final int ADVERB = 0x040000008;
    public static final int LINKING = 0x040000010;
    public static final int ARTICLE = 0x040000020;
    public static final int DETERMINER = 0x040000040;
    public static final int MODAL = 0x040000080;
    
    public static String categoryByRange (int index) {
        if (
            0x020000001 <= index && index <= 0x020000002
        ) {
            return "STATE";
        }
        if (
            0x030000001 <= index && index <= 0x030000040
        ) {
            return "COLORS";
        }
        if (
            0x040000001 <= index && index <= 0x040000080
        ) {
            return "POS";
        }
        throw new IllegalStateException("Dev err: unknown datatype");
    }
    public static String categoryByBaseIndex (int index) {
        switch (index) {
            case 0x020000000:
                return "STATE";
            case 0x030000000:
                return "COLORS";
            case 0x040000000:
                return "POS";
            default:
                throw new IllegalStateException("Dev err: unknown datatype");
        }
    }
    public static int baseIndexByRange (int index) {
        if (
            0x020000001 <= index && index <= 0x020000002
        ) {
            return 0x020000000;
        }
        if (
            0x030000001 <= index && index <= 0x030000040
        ) {
            return 0x030000000;
        }
        if (
            0x040000001 <= index && index <= 0x040000080
        ) {
            return 0x040000000;
        }
        throw new IllegalStateException("Dev err: unknown datatype");
    }
    public static int offset () {
        return 0x02;
    }
}
