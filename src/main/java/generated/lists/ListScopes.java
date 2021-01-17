// Generated file, do not edit
// Last write: 01/13/2021 23:55:00
package generated.lists;

public class ListScopes {
    public static final int SCOPES = 0x02000000;
    
    public static final int ALL = 0x02000001;
    public static final int SENTENCE = 0x02000002;
    public static final int PHRASE = 0x02000003;
    public static final int WORD = 0x02000004;
    public static final int CHAR = 0x02000005;
    
    public static String categoryByRange (int index) {
        if (
            (0x02000001 <= index && index <= 0x02000005)
        ) {
            return "SCOPES";
        }
        throw new IllegalStateException("Dev err: unknown datatype");
    }
    public static String categoryByBaseIndex (int index) {
        switch (index) {
            case 0x02000000:
                return "SCOPES";
            default:
                throw new IllegalStateException("Dev err: unknown datatype");
        }
    }
    public static int baseIndexByRange (int index) {
        if (
            (0x02000001 <= index && index <= 0x02000005)
        ) {
            return 0x02000000;
        }
        throw new IllegalStateException("Dev err: unknown datatype");
    }
    // for monotonic values across different arrays
    public static int offset () {
        return 0x02;
    }
    // for debug or user-friendly display
    public static String nameByIndex (int index) {
        switch (index) {
            case ALL:
                return "ALL";
            case SENTENCE:
                return "SENTENCE";
            case PHRASE:
                return "PHRASE";
            case WORD:
                return "WORD";
            case CHAR:
                return "CHAR";
            default:
                return "none";
        }
    }
    public static int topScope () {
        return ALL;
    }
    public static int bottomScope () {
        return CHAR;
    }
    public static int nextScopeUp (int index) {
        switch (index) {
            case ALL:
                return 0;
            case SENTENCE:
                return ALL;
            case PHRASE:
                return SENTENCE;
            case WORD:
                return PHRASE;
            case CHAR:
                return WORD;
            default:
                throw new IllegalStateException("Dev err: unknown datatype");
        }
    }
    public static int nextScopeDown (int index) {
        switch (index) {
            case CHAR:
                return 0;
            case WORD:
                return CHAR;
            case PHRASE:
                return WORD;
            case SENTENCE:
                return PHRASE;
            case ALL:
                return SENTENCE;
            default:
                throw new IllegalStateException("Dev err: unknown datatype");
        }
    }
}
