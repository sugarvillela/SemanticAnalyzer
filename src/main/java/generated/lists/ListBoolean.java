// Generated file, do not edit
// Last write: 12/03/2020 21:23:09
package generated.lists;

public class ListBoolean {
    public static final int SYMBOL_OTHER = 0x0F000000;
    
    public static final int AMPERSAND = 0x0F000001;
    public static final int AT = 0x0F000002;
    public static final int BACK_SLASH = 0x0F000004;
    public static final int DOLLAR = 0x0F000008;
    public static final int POUND = 0x0F000010;
    public static final int QUESTION_MARK = 0x0F000020;
    public static final int VERT_BAR = 0x0F000040;
    
    public static final int WORD_CONTAINS = 0x010000000;
    
    public static final int ALGEBRA = 0x010000001;
    public static final int ALPHA = 0x010000002;
    public static final int ALPHA_ALL = 0x010000004;
    public static final int CAP = 0x010000008;
    public static final int CAP_ALL = 0x010000010;
    public static final int CAP_FIRST = 0x010000020;
    public static final int CAP_INNER = 0x010000040;
    public static final int FIRST_IN_SEN = 0x010000080;
    public static final int MATH = 0x010000100;
    public static final int NON_ASCII = 0x010000200;
    public static final int NUMERIC = 0x010000400;
    public static final int NUMERIC_ALL = 0x010000800;
    public static final int SYMBOL = 0x010001000;
    
    public static final int HINT1 = 0x011000000;
    
    public static final int ABILITY = 0x011000001;
    public static final int BECOME = 0x011000002;
    public static final int CAUSE = 0x011000004;
    public static final int COMPARISON = 0x011000008;
    public static final int CONCESSION = 0x011000010;
    public static final int CONDITION = 0x011000020;
    public static final int DEGREE = 0x011000040;
    public static final int DOER = 0x011000080;
    public static final int FOLLOWER = 0x011000100;
    public static final int FREQUENCY = 0x011000200;
    public static final int HEAT = 0x011000400;
    public static final int HYPOTHETICAL = 0x011000800;
    public static final int LIKELIHOOD = 0x011001000;
    public static final int MANNER = 0x011002000;
    public static final int MULT = 0x011004000;
    public static final int NEGATE = 0x011008000;
    
    public static final int HINT2 = 0x012000000;
    
    public static final int OBLIGATION = 0x012000001;
    public static final int P_POS = 0x012000002;
    public static final int PERMISSION = 0x012000004;
    public static final int PLACE = 0x012000008;
    public static final int RELATION = 0x012000010;
    public static final int SIZE = 0x012000020;
    public static final int STATE = 0x012000040;
    public static final int TIME = 0x012000080;
    public static final int TRAIT = 0x012000100;
    public static final int TRANSITION = 0x012000200;
    public static final int U_NEG = 0x012000400;
    public static final int U_POS = 0x012000800;
    
    public static final int SEN_DIVIDER = 0x013000000;
    
    public static final int SQ = 0x013000001;
    public static final int DQ = 0x013000002;
    public static final int OSQ = 0x013000004;
    public static final int ODQ = 0x013000008;
    public static final int CSQ = 0x013000010;
    public static final int CDQ = 0x013000020;
    public static final int OPAREN = 0x013000040;
    public static final int OCURLY = 0x013000080;
    public static final int OBRACK = 0x013000100;
    public static final int CPAREN = 0x013000200;
    public static final int CCURLY = 0x013000400;
    public static final int CBRACK = 0x013000800;
    public static final int OCHEVRON = 0x013001000;
    public static final int CCHEVRON = 0x013002000;
    
    public static final int WORD_TRAIT = 0x014000000;
    
    public static final int ABBR = 0x014000001;
    public static final int DATE = 0x014000002;
    public static final int EMAIL = 0x014000004;
    public static final int FILE_PATH = 0x014000008;
    public static final int NUM_WORD = 0x014000010;
    public static final int PASSWORD = 0x014000020;
    
    public static final int WORD_SUB_TYPE = 0x015000000;
    
    public static final int ARTICLE = 0x015000001;
    public static final int CONJ_ADV = 0x015000002;
    public static final int COORDINATING = 0x015000004;
    public static final int CORRELATING = 0x015000008;
    public static final int DEFINITE = 0x015000010;
    public static final int DEMONSTRATIVE = 0x015000020;
    public static final int DISTRIBUTIVE = 0x015000040;
    public static final int INDEFINITE = 0x015000080;
    public static final int LINKING = 0x015000100;
    public static final int PERSONAL = 0x015000200;
    public static final int POSSESSIVE = 0x015000400;
    public static final int QUANTITIVE = 0x015000800;
    public static final int RECIPROCAL = 0x015001000;
    public static final int REFLEXIVE = 0x015002000;
    public static final int SUBORDINATING = 0x015004000;
    
    public static String categoryByRange (int index) {
        if (
            (0x0F000001 <= index && index <= 0x0F000040)
        ) {
            return "SYMBOL_OTHER";
        }
        if (
            (0x010000001 <= index && index <= 0x010001000)
        ) {
            return "WORD_CONTAINS";
        }
        if (
            (0x011000001 <= index && index <= 0x011008000)
        ) {
            return "HINT1";
        }
        if (
            (0x012000001 <= index && index <= 0x012000800)
        ) {
            return "HINT2";
        }
        if (
            (0x013000001 <= index && index <= 0x013002000)
        ) {
            return "SEN_DIVIDER";
        }
        if (
            (0x014000001 <= index && index <= 0x014000020)
        ) {
            return "WORD_TRAIT";
        }
        if (
            (0x015000001 <= index && index <= 0x015004000)
        ) {
            return "WORD_SUB_TYPE";
        }
        throw new IllegalStateException(String.format("Dev err: unknown datatype: 0x%X", index));
    }
    public static String categoryByBaseIndex (int index) {
        switch (index) {
            case 0x0F000000:
                return "SYMBOL_OTHER";
            case 0x010000000:
                return "WORD_CONTAINS";
            case 0x011000000:
                return "HINT1";
            case 0x012000000:
                return "HINT2";
            case 0x013000000:
                return "SEN_DIVIDER";
            case 0x014000000:
                return "WORD_TRAIT";
            case 0x015000000:
                return "WORD_SUB_TYPE";
            default:
                throw new IllegalStateException(String.format("Dev err: unknown datatype: 0x%X", index));
        }
    }
    public static int baseIndexByRange (int index) {
        if (
            (0x0F000001 <= index && index <= 0x0F000040)
        ) {
            return 0x0F000000;
        }
        if (
            (0x010000001 <= index && index <= 0x010001000)
        ) {
            return 0x010000000;
        }
        if (
            (0x011000001 <= index && index <= 0x011008000)
        ) {
            return 0x011000000;
        }
        if (
            (0x012000001 <= index && index <= 0x012000800)
        ) {
            return 0x012000000;
        }
        if (
            (0x013000001 <= index && index <= 0x013002000)
        ) {
            return 0x013000000;
        }
        if (
            (0x014000001 <= index && index <= 0x014000020)
        ) {
            return 0x014000000;
        }
        if (
            (0x015000001 <= index && index <= 0x015004000)
        ) {
            return 0x015000000;
        }
        throw new IllegalStateException(String.format("Dev err: unknown datatype: 0x%X", index));
    }
    public static int offset () {
        return 0x0F;
    }
}
