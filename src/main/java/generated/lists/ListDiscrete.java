// Generated file, do not edit
// Last write: 11/18/2020 16:36:34
package generated.lists;

public class ListDiscrete {
    public static final int VERB_FORMS = 0x010000000;
    
    public static final int INFINITIVE = 0x010000001;
    public static final int PRESENT = 0x010000002;
    public static final int PAST = 0x010000003;
    public static final int PAST_PARTICIPLE = 0x010000004;
    public static final int PROGRESSIVE = 0x010000005;
    public static final int GERUND = 0x010000006;
    public static final int SINGULAR = 0x010000007;
    public static final int PLURAL = 0x010000008;
    public static final int FUTURE = 0x010000009;
    public static final int COMPARATIVE = 0x01000000A;
    public static final int SUPURLATIVE = 0x01000000B;
    
    public static final int NUMBERS = 0x011000000;
    
    public static final int ONE = 0x011000010;
    public static final int TWO = 0x011000020;
    public static final int THREE = 0x011000030;
    public static final int FOUR = 0x011000040;
    public static final int FIVE = 0x011000050;
    public static final int SIX = 0x011000060;
    public static final int SEVEN = 0x011000070;
    public static final int EIGHT = 0x011000080;
    public static final int NINE = 0x011000090;
    public static final int TEN = 0x0110000A0;
    
    public static String categoryByRange (int index) {
        if (
            0x010000001 <= index && index <= 0x01000000B
        ) {
            return "VERB_FORMS";
        }
        if (
            0x011000010 <= index && index <= 0x0110000A0
        ) {
            return "NUMBERS";
        }
        throw new IllegalStateException("Dev err: unknown datatype");
    }
    public static String categoryByBaseIndex (int index) {
        switch (index) {
            case 0x010000000:
                return "VERB_FORMS";
            case 0x011000000:
                return "NUMBERS";
            default:
                throw new IllegalStateException("Dev err: unknown datatype");
        }
    }
    public static int baseIndexByRange (int index) {
        if (
            0x010000001 <= index && index <= 0x01000000B
        ) {
            return 0x010000000;
        }
        if (
            0x011000010 <= index && index <= 0x0110000A0
        ) {
            return 0x011000000;
        }
        throw new IllegalStateException("Dev err: unknown datatype");
    }
    public static int offset () {
        return 0x01;
    }
}
