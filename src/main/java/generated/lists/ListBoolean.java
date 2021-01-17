// Generated file, do not edit
// Last write: 01/13/2021 23:55:00
package generated.lists;

public class ListBoolean {
    public static final int SYMBOL_OTHER = 0x0E000000;
    
    public static final int AMPERSAND = 0x0E000001;
    public static final int AT = 0x0E000002;
    public static final int BACK_SLASH = 0x0E000004;
    public static final int DOLLAR = 0x0E000008;
    public static final int POUND = 0x0E000010;
    public static final int QUESTION_MARK = 0x0E000020;
    public static final int VERT_BAR = 0x0E000040;
    
    public static final int WORD_CONTAINS = 0x0F000000;
    
    public static final int ALGEBRA = 0x0F000001;
    public static final int ALPHA = 0x0F000002;
    public static final int ALPHA_ALL = 0x0F000004;
    public static final int CAP = 0x0F000008;
    public static final int CAP_ALL = 0x0F000010;
    public static final int CAP_FIRST = 0x0F000020;
    public static final int CAP_INNER = 0x0F000040;
    public static final int FIRST_IN_SEN = 0x0F000080;
    public static final int MATH = 0x0F000100;
    public static final int NON_ASCII = 0x0F000200;
    public static final int NUMERIC = 0x0F000400;
    public static final int NUMERIC_ALL = 0x0F000800;
    public static final int SYMBOL = 0x0F001000;
    
    public static final int HINT1 = 0x010000000;
    
    public static final int ABILITY = 0x010000001;
    public static final int BECOME = 0x010000002;
    public static final int CAUSE = 0x010000004;
    public static final int COMPARISON = 0x010000008;
    public static final int CONCESSION = 0x010000010;
    public static final int CONDITION = 0x010000020;
    public static final int DEGREE = 0x010000040;
    public static final int DOER = 0x010000080;
    public static final int FOLLOWER = 0x010000100;
    public static final int FREQUENCY = 0x010000200;
    public static final int HEAT = 0x010000400;
    public static final int HYPOTHETICAL = 0x010000800;
    public static final int LIKELIHOOD = 0x010001000;
    public static final int MANNER = 0x010002000;
    public static final int MULT = 0x010004000;
    public static final int NEGATE = 0x010008000;
    
    public static final int HINT2 = 0x011000000;
    
    public static final int OBLIGATION = 0x011000001;
    public static final int P_POS = 0x011000002;
    public static final int PERMISSION = 0x011000004;
    public static final int PLACE = 0x011000008;
    public static final int RELATION = 0x011000010;
    public static final int SIZE = 0x011000020;
    public static final int STATE = 0x011000040;
    public static final int TIME = 0x011000080;
    public static final int TRAIT = 0x011000100;
    public static final int TRANSITION = 0x011000200;
    public static final int U_NEG = 0x011000400;
    public static final int U_POS = 0x011000800;
    
    public static final int SEN_DIVIDER = 0x012000000;
    
    public static final int SQ = 0x012000001;
    public static final int DQ = 0x012000002;
    public static final int OSQ = 0x012000004;
    public static final int ODQ = 0x012000008;
    public static final int CSQ = 0x012000010;
    public static final int CDQ = 0x012000020;
    public static final int OPAREN = 0x012000040;
    public static final int OCURLY = 0x012000080;
    public static final int OBRACK = 0x012000100;
    public static final int CPAREN = 0x012000200;
    public static final int CCURLY = 0x012000400;
    public static final int CBRACK = 0x012000800;
    public static final int OCHEVRON = 0x012001000;
    public static final int CCHEVRON = 0x012002000;
    
    public static final int WORD_TRAIT = 0x013000000;
    
    public static final int ABBR = 0x013000001;
    public static final int DATE = 0x013000002;
    public static final int EMAIL = 0x013000004;
    public static final int FILE_PATH = 0x013000008;
    public static final int NUM_WORD = 0x013000010;
    public static final int PASSWORD = 0x013000020;
    
    public static final int WORD_SUB_TYPE = 0x014000000;
    
    public static final int ARTICLE = 0x014000001;
    public static final int CONJ_ADV = 0x014000002;
    public static final int COORDINATING = 0x014000004;
    public static final int CORRELATING = 0x014000008;
    public static final int DEFINITE = 0x014000010;
    public static final int DEMONSTRATIVE = 0x014000020;
    public static final int DISTRIBUTIVE = 0x014000040;
    public static final int INDEFINITE = 0x014000080;
    public static final int LINKING = 0x014000100;
    public static final int PERSONAL = 0x014000200;
    public static final int POSSESSIVE = 0x014000400;
    public static final int QUANTITIVE = 0x014000800;
    public static final int RECIPROCAL = 0x014001000;
    public static final int REFLEXIVE = 0x014002000;
    public static final int SUBORDINATING = 0x014004000;
    
    public static String categoryByRange (int index) {
        if (
            (0x0E000001 <= index && index <= 0x0E000040)
        ) {
            return "SYMBOL_OTHER";
        }
        if (
            (0x0F000001 <= index && index <= 0x0F001000)
        ) {
            return "WORD_CONTAINS";
        }
        if (
            (0x010000001 <= index && index <= 0x010008000)
        ) {
            return "HINT1";
        }
        if (
            (0x011000001 <= index && index <= 0x011000800)
        ) {
            return "HINT2";
        }
        if (
            (0x012000001 <= index && index <= 0x012002000)
        ) {
            return "SEN_DIVIDER";
        }
        if (
            (0x013000001 <= index && index <= 0x013000020)
        ) {
            return "WORD_TRAIT";
        }
        if (
            (0x014000001 <= index && index <= 0x014004000)
        ) {
            return "WORD_SUB_TYPE";
        }
        throw new IllegalStateException("Dev err: unknown datatype");
    }
    public static String categoryByBaseIndex (int index) {
        switch (index) {
            case 0x0E000000:
                return "SYMBOL_OTHER";
            case 0x0F000000:
                return "WORD_CONTAINS";
            case 0x010000000:
                return "HINT1";
            case 0x011000000:
                return "HINT2";
            case 0x012000000:
                return "SEN_DIVIDER";
            case 0x013000000:
                return "WORD_TRAIT";
            case 0x014000000:
                return "WORD_SUB_TYPE";
            default:
                throw new IllegalStateException("Dev err: unknown datatype");
        }
    }
    public static int baseIndexByRange (int index) {
        if (
            (0x0E000001 <= index && index <= 0x0E000040)
        ) {
            return 0x0E000000;
        }
        if (
            (0x0F000001 <= index && index <= 0x0F001000)
        ) {
            return 0x0F000000;
        }
        if (
            (0x010000001 <= index && index <= 0x010008000)
        ) {
            return 0x010000000;
        }
        if (
            (0x011000001 <= index && index <= 0x011000800)
        ) {
            return 0x011000000;
        }
        if (
            (0x012000001 <= index && index <= 0x012002000)
        ) {
            return 0x012000000;
        }
        if (
            (0x013000001 <= index && index <= 0x013000020)
        ) {
            return 0x013000000;
        }
        if (
            (0x014000001 <= index && index <= 0x014004000)
        ) {
            return 0x014000000;
        }
        throw new IllegalStateException("Dev err: unknown datatype");
    }
    // for monotonic values across different arrays
    public static int offset () {
        return 0x0E;
    }
    // for debug or user-friendly display
    public static String nameByIndex (int index) {
        switch (index) {
            case AMPERSAND:
                return "AMPERSAND";
            case AT:
                return "AT";
            case BACK_SLASH:
                return "BACK_SLASH";
            case DOLLAR:
                return "DOLLAR";
            case POUND:
                return "POUND";
            case QUESTION_MARK:
                return "QUESTION_MARK";
            case VERT_BAR:
                return "VERT_BAR";
            case ALGEBRA:
                return "ALGEBRA";
            case ALPHA:
                return "ALPHA";
            case ALPHA_ALL:
                return "ALPHA_ALL";
            case CAP:
                return "CAP";
            case CAP_ALL:
                return "CAP_ALL";
            case CAP_FIRST:
                return "CAP_FIRST";
            case CAP_INNER:
                return "CAP_INNER";
            case FIRST_IN_SEN:
                return "FIRST_IN_SEN";
            case MATH:
                return "MATH";
            case NON_ASCII:
                return "NON_ASCII";
            case NUMERIC:
                return "NUMERIC";
            case NUMERIC_ALL:
                return "NUMERIC_ALL";
            case SYMBOL:
                return "SYMBOL";
            case ABILITY:
                return "ABILITY";
            case BECOME:
                return "BECOME";
            case CAUSE:
                return "CAUSE";
            case COMPARISON:
                return "COMPARISON";
            case CONCESSION:
                return "CONCESSION";
            case CONDITION:
                return "CONDITION";
            case DEGREE:
                return "DEGREE";
            case DOER:
                return "DOER";
            case FOLLOWER:
                return "FOLLOWER";
            case FREQUENCY:
                return "FREQUENCY";
            case HEAT:
                return "HEAT";
            case HYPOTHETICAL:
                return "HYPOTHETICAL";
            case LIKELIHOOD:
                return "LIKELIHOOD";
            case MANNER:
                return "MANNER";
            case MULT:
                return "MULT";
            case NEGATE:
                return "NEGATE";
            case OBLIGATION:
                return "OBLIGATION";
            case P_POS:
                return "P_POS";
            case PERMISSION:
                return "PERMISSION";
            case PLACE:
                return "PLACE";
            case RELATION:
                return "RELATION";
            case SIZE:
                return "SIZE";
            case STATE:
                return "STATE";
            case TIME:
                return "TIME";
            case TRAIT:
                return "TRAIT";
            case TRANSITION:
                return "TRANSITION";
            case U_NEG:
                return "U_NEG";
            case U_POS:
                return "U_POS";
            case SQ:
                return "SQ";
            case DQ:
                return "DQ";
            case OSQ:
                return "OSQ";
            case ODQ:
                return "ODQ";
            case CSQ:
                return "CSQ";
            case CDQ:
                return "CDQ";
            case OPAREN:
                return "OPAREN";
            case OCURLY:
                return "OCURLY";
            case OBRACK:
                return "OBRACK";
            case CPAREN:
                return "CPAREN";
            case CCURLY:
                return "CCURLY";
            case CBRACK:
                return "CBRACK";
            case OCHEVRON:
                return "OCHEVRON";
            case CCHEVRON:
                return "CCHEVRON";
            case ABBR:
                return "ABBR";
            case DATE:
                return "DATE";
            case EMAIL:
                return "EMAIL";
            case FILE_PATH:
                return "FILE_PATH";
            case NUM_WORD:
                return "NUM_WORD";
            case PASSWORD:
                return "PASSWORD";
            case ARTICLE:
                return "ARTICLE";
            case CONJ_ADV:
                return "CONJ_ADV";
            case COORDINATING:
                return "COORDINATING";
            case CORRELATING:
                return "CORRELATING";
            case DEFINITE:
                return "DEFINITE";
            case DEMONSTRATIVE:
                return "DEMONSTRATIVE";
            case DISTRIBUTIVE:
                return "DISTRIBUTIVE";
            case INDEFINITE:
                return "INDEFINITE";
            case LINKING:
                return "LINKING";
            case PERSONAL:
                return "PERSONAL";
            case POSSESSIVE:
                return "POSSESSIVE";
            case QUANTITIVE:
                return "QUANTITIVE";
            case RECIPROCAL:
                return "RECIPROCAL";
            case REFLEXIVE:
                return "REFLEXIVE";
            case SUBORDINATING:
                return "SUBORDINATING";
            default:
                return "none";
        }
    }
}
