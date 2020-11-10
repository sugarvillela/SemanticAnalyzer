// Generated file, do not edit
// Last write: 11/05/2020 19:50:29
package generated.lists;

public class ListBoolean {
    public static final int STATE = 0x00;
    public static final int MORE = 0x01;
    public static final int DONE = 0x02;
    
    public static final int COLORS = 0x010000000;
    public static final int RED = 0x010000001;
    public static final int BLUE = 0x010000002;
    public static final int GREEN = 0x010000004;
    public static final int ORANGE = 0x010000008;
    public static final int FUSCIA = 0x010000010;
    public static final int PURPLE = 0x010000020;
    public static final int VIOLET = 0x010000040;
    
    public static final int POS = 0x020000000;
    public static final int VERB = 0x020000001;
    public static final int NOUN = 0x020000002;
    public static final int ADJECTIVE = 0x020000004;
    public static final int ADVERB = 0x020000008;
    public static final int LINKING = 0x020000010;
    public static final int ARTICLE = 0x020000020;
    public static final int DETERMINER = 0x020000040;
    public static final int MODAL = 0x020000080;
    
    public static String rangeToString (int index) {
        if (
            0x01 <= index && index <= 0x02
        ) {
            return "STATE";
        }
        if (
            0x010000001 <= index && index <= 0x010000040
        ) {
            return "COLORS";
        }
        if (
            0x020000001 <= index && index <= 0x020000080
        ) {
            return "POS";
        }
        return null;
    }
    public static int rangeToBaseIndex (int index) {
        if (
            0x01 <= index && index <= 0x02
        ) {
            return 0x00;
        }
        if (
            0x010000001 <= index && index <= 0x010000040
        ) {
            return 0x010000000;
        }
        if (
            0x020000001 <= index && index <= 0x020000080
        ) {
            return 0x020000000;
        }
        return -1;
    }
    public static String baseIndexToString (int index) {
        switch (index) {
            case 0x00:
                return "STATE";
            case 0x010000000:
                return "COLORS";
            case 0x020000000:
                return "POS";
            default:
                return null;
        }
    }
}
