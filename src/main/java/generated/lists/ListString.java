// Generated file, do not edit
// Last write: 11/05/2020 19:50:29
package generated.lists;

public class ListString {
    public static final int IN = 0x04;
    public static final int ORIG = 0x05;
    public static final int ALT = 0x06;
    public static final int EXPAND1 = 0x07;
    public static final int EXPAND2 = 0x08;
    public static final int NAME = 0x09;
    public static final int SURNAME = 0x0A;
    
    public static String rangeToString (int index) {
        if (
            0x04 <= index && index <= 0x0A
        ) {
            return "TEXT";
        }
        return null;
    }
}
