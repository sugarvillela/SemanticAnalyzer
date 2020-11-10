// Generated file, do not edit
// Last write: 11/05/2020 19:50:29
package generated.lists;

public class ListDiscrete {
    public static final int INFINITIVE = 0x00;
    public static final int PRESENT = 0x01;
    public static final int PAST = 0x02;
    public static final int PAST_PARTICIPLE = 0x03;
    public static final int PROGRESSIVE = 0x04;
    public static final int GERUND = 0x05;
    public static final int SINGULAR = 0x06;
    public static final int PLURAL = 0x07;
    public static final int FUTURE = 0x08;
    public static final int COMPARATIVE = 0x09;
    public static final int SUPURLATIVE = 0x0A;
    
    public static final int ONE = 0x01000000;
    public static final int TWO = 0x01000010;
    public static final int THREE = 0x01000020;
    public static final int FOUR = 0x01000030;
    public static final int FIVE = 0x01000040;
    public static final int SIX = 0x01000050;
    public static final int SEVEN = 0x01000060;
    public static final int EIGHT = 0x01000070;
    public static final int NINE = 0x01000080;
    public static final int TEN = 0x01000090;
    
    public static String rangeToString (int index) {
        if (
            0x00 <= index && index <= 0x0A
        ) {
            return "VERB_FORMS";
        }
        if (
            0x01000000 <= index && index <= 0x01000090
        ) {
            return "NUMBERS";
        }
        return null;
    }
}
