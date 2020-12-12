// Generated file, do not edit
// Last write: 12/03/2020 21:23:09
package generated.lists;

public class ListDiscrete {
    public static final int STATUS = 0x01000000;
    
    public static final int GO = 0x01000001;
    public static final int SET = 0x01000002;
    public static final int DONE = 0x01000003;
    public static final int FAIL = 0x01000004;
    
    public static final int SURNAME = 0x01100000;
    
    public static final int CHINESE = 0x01100010;
    public static final int GERMAN = 0x01100020;
    public static final int INDIAN = 0x01100030;
    public static final int JAPANESE = 0x01100040;
    public static final int OTHER = 0x01100050;
    public static final int SPANISH = 0x01100060;
    public static final int VIET = 0x01100070;
    
    public static final int PERSON = 0x01200000;
    
    public static final int PERSON1 = 0x01200100;
    public static final int PERSON2 = 0x01200200;
    public static final int PERSON3 = 0x01200300;
    
    public static final int ADJ_TYPE = 0x01300000;
    
    public static final int COMPARATIVE = 0x01301000;
    public static final int POSITIVE = 0x01302000;
    public static final int SUPERLATIVE = 0x01303000;
    
    public static String categoryByRange (int index) {
        if (
            (0x01000001 <= index && index <= 0x01000004)
        ) {
            return "STATUS";
        }
        if (
            (0x01100010 <= index && index <= 0x01100070)
        ) {
            return "SURNAME";
        }
        if (
            (0x01200100 <= index && index <= 0x01200300)
        ) {
            return "PERSON";
        }
        if (
            (0x01301000 <= index && index <= 0x01303000)
        ) {
            return "ADJ_TYPE";
        }
        throw new IllegalStateException(String.format("Dev err: unknown datatype: 0x%X", index));
    }
    public static String categoryByBaseIndex (int index) {
        switch (index) {
            case 0x01000000:
                return "STATUS";
            case 0x01100000:
                return "SURNAME";
            case 0x01200000:
                return "PERSON";
            case 0x01300000:
                return "ADJ_TYPE";
            default:
                throw new IllegalStateException(String.format("Dev err: unknown datatype: 0x%X", index));
        }
    }
    public static int baseIndexByRange (int index) {
        if (
            (0x01000001 <= index && index <= 0x01000004)
        ) {
            return 0x01000000;
        }
        if (
            (0x01100010 <= index && index <= 0x01100070)
        ) {
            return 0x01100000;
        }
        if (
            (0x01200100 <= index && index <= 0x01200300)
        ) {
            return 0x01200000;
        }
        if (
            (0x01301000 <= index && index <= 0x01303000)
        ) {
            return 0x01300000;
        }
        throw new IllegalStateException(String.format("Dev err: unknown datatype: 0x%X", index));
    }
    public static int offset () {
        return 0x01;
    }
}
