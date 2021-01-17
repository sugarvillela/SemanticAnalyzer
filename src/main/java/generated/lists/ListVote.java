// Generated file, do not edit
// Last write: 01/13/2021 23:55:00
package generated.lists;

public class ListVote {
    public static final int SEN_TRAIT = 0x03000000;
    
    public static final int NUM_LIST = 0x03000001;
    public static final int SYMBOL_TRANSITION = 0x03100010;
    
    public static final int QUANTIFIER = 0x03200000;
    
    public static final int PLURAL = 0x03200100;
    public static final int NON_COUNT = 0x03301000;
    public static final int SINGULAR = 0x03410000;
    
    public static final int POS = 0x04000000;
    
    public static final int ADJ = 0x04000001;
    public static final int ADV = 0x04100010;
    public static final int CONJUNCTION = 0x04200100;
    public static final int DETERMINER = 0x04301000;
    public static final int IRREG = 0x04410000;
    public static final int MODAL = 0x05000001;
    public static final int NAME = 0x05100010;
    public static final int NOUN = 0x05200100;
    public static final int PREP = 0x05301000;
    public static final int PREP_ADV = 0x05410000;
    public static final int PRONOUN = 0x06000001;
    public static final int VERB = 0x06100010;
    
    public static final int PHRASE_SEPARATOR = 0x06200000;
    
    public static final int END_SEN = 0x06200100;
    public static final int END_CLAUSE = 0x06301000;
    public static final int COLON = 0x06410000;
    public static final int ELLIPSIS = 0x07000001;
    public static final int EXCLAMATION = 0x07100010;
    public static final int QUESTION = 0x07200100;
    public static final int SEMICOLON = 0x07301000;
    
    public static final int VERB_TENSE = 0x07400000;
    
    public static final int FUTURE = 0x07410000;
    public static final int GERUND = 0x08000001;
    public static final int INFINITIVE = 0x08100010;
    public static final int PAST = 0x08200100;
    public static final int PAST_PARTICIPLE = 0x08301000;
    public static final int PRESENT = 0x08410000;
    public static final int PRESENT_PERFECT = 0x09000001;
    public static final int PROGRESSIVE = 0x09100010;
    
    public static final int GENDER = 0x09200000;
    
    public static final int M = 0x09200100;
    public static final int F = 0x09301000;
    
    public static final int MATHY = 0x09400000;
    
    public static final int ASTERISK = 0x09410000;
    public static final int CARAT = 0x0A000001;
    public static final int COMMA = 0x0A100010;
    public static final int HYPHEN = 0x0A200100;
    public static final int PERCENT = 0x0A301000;
    public static final int PERIOD = 0x0A410000;
    public static final int SLASH = 0x0B000001;
    public static final int DIV_SIGN = 0x0B100010;
    public static final int EQUAL_SIGN = 0x0B200100;
    public static final int MINUS_SIGN = 0x0B301000;
    public static final int MOD_SIGN = 0x0B410000;
    public static final int NUMBER_COMMA = 0x0C000001;
    public static final int PLUS_SIGN = 0x0C100010;
    public static final int POINT = 0x0C200100;
    public static final int POW_SIGN = 0x0C301000;
    public static final int TIMES_SIGN = 0x0C410000;
    
    public static final int SQ_VOTE = 0x0D000000;
    
    public static final int VOTE_QUOTE = 0x0D000001;
    public static final int VOTE_POSSESSIVE = 0x0D100010;
    
    public static final int SUBJECT_OBJECT = 0x0D200000;
    
    public static final int OBJECT = 0x0D200100;
    public static final int SUBJECT = 0x0D301000;
    
    public static String categoryByRange (int index) {
        if (
            (0x03000001 <= index && index <= 0x03100010)
        ) {
            return "SEN_TRAIT";
        }
        if (
            (0x03200100 <= index && index <= 0x03410000)
        ) {
            return "QUANTIFIER";
        }
        if (
            (0x04000001 <= index && index <= 0x06100010)
        ) {
            return "POS";
        }
        if (
            (0x06200100 <= index && index <= 0x07301000)
        ) {
            return "PHRASE_SEPARATOR";
        }
        if (
            (0x07410000 <= index && index <= 0x09100010)
        ) {
            return "VERB_TENSE";
        }
        if (
            (0x09200100 <= index && index <= 0x09301000)
        ) {
            return "GENDER";
        }
        if (
            (0x09410000 <= index && index <= 0x0C410000)
        ) {
            return "MATHY";
        }
        if (
            (0x0D000001 <= index && index <= 0x0D100010)
        ) {
            return "SQ_VOTE";
        }
        if (
            (0x0D200100 <= index && index <= 0x0D301000)
        ) {
            return "SUBJECT_OBJECT";
        }
        throw new IllegalStateException("Dev err: unknown datatype");
    }
    public static String categoryByBaseIndex (int index) {
        switch (index) {
            case 0x03000000:
                return "SEN_TRAIT";
            case 0x03200000:
                return "QUANTIFIER";
            case 0x04000000:
                return "POS";
            case 0x06200000:
                return "PHRASE_SEPARATOR";
            case 0x07400000:
                return "VERB_TENSE";
            case 0x09200000:
                return "GENDER";
            case 0x09400000:
                return "MATHY";
            case 0x0D000000:
                return "SQ_VOTE";
            case 0x0D200000:
                return "SUBJECT_OBJECT";
            default:
                throw new IllegalStateException("Dev err: unknown datatype");
        }
    }
    public static int baseIndexByRange (int index) {
        if (
            (0x03000001 <= index && index <= 0x03100010)
        ) {
            return 0x03000000;
        }
        if (
            (0x03200100 <= index && index <= 0x03410000)
        ) {
            return 0x03200000;
        }
        if (
            (0x04000001 <= index && index <= 0x06100010)
        ) {
            return 0x04000000;
        }
        if (
            (0x06200100 <= index && index <= 0x07301000)
        ) {
            return 0x06200000;
        }
        if (
            (0x07410000 <= index && index <= 0x09100010)
        ) {
            return 0x07400000;
        }
        if (
            (0x09200100 <= index && index <= 0x09301000)
        ) {
            return 0x09200000;
        }
        if (
            (0x09410000 <= index && index <= 0x0C410000)
        ) {
            return 0x09400000;
        }
        if (
            (0x0D000001 <= index && index <= 0x0D100010)
        ) {
            return 0x0D000000;
        }
        if (
            (0x0D200100 <= index && index <= 0x0D301000)
        ) {
            return 0x0D200000;
        }
        throw new IllegalStateException("Dev err: unknown datatype");
    }
    // for monotonic values across different arrays
    public static int offset () {
        return 0x03;
    }
    // for debug or user-friendly display
    public static String nameByIndex (int index) {
        switch (index) {
            case NUM_LIST:
                return "NUM_LIST";
            case SYMBOL_TRANSITION:
                return "SYMBOL_TRANSITION";
            case PLURAL:
                return "PLURAL";
            case NON_COUNT:
                return "NON_COUNT";
            case SINGULAR:
                return "SINGULAR";
            case ADJ:
                return "ADJ";
            case ADV:
                return "ADV";
            case CONJUNCTION:
                return "CONJUNCTION";
            case DETERMINER:
                return "DETERMINER";
            case IRREG:
                return "IRREG";
            case MODAL:
                return "MODAL";
            case NAME:
                return "NAME";
            case NOUN:
                return "NOUN";
            case PREP:
                return "PREP";
            case PREP_ADV:
                return "PREP_ADV";
            case PRONOUN:
                return "PRONOUN";
            case VERB:
                return "VERB";
            case END_SEN:
                return "END_SEN";
            case END_CLAUSE:
                return "END_CLAUSE";
            case COLON:
                return "COLON";
            case ELLIPSIS:
                return "ELLIPSIS";
            case EXCLAMATION:
                return "EXCLAMATION";
            case QUESTION:
                return "QUESTION";
            case SEMICOLON:
                return "SEMICOLON";
            case FUTURE:
                return "FUTURE";
            case GERUND:
                return "GERUND";
            case INFINITIVE:
                return "INFINITIVE";
            case PAST:
                return "PAST";
            case PAST_PARTICIPLE:
                return "PAST_PARTICIPLE";
            case PRESENT:
                return "PRESENT";
            case PRESENT_PERFECT:
                return "PRESENT_PERFECT";
            case PROGRESSIVE:
                return "PROGRESSIVE";
            case M:
                return "M";
            case F:
                return "F";
            case ASTERISK:
                return "ASTERISK";
            case CARAT:
                return "CARAT";
            case COMMA:
                return "COMMA";
            case HYPHEN:
                return "HYPHEN";
            case PERCENT:
                return "PERCENT";
            case PERIOD:
                return "PERIOD";
            case SLASH:
                return "SLASH";
            case DIV_SIGN:
                return "DIV_SIGN";
            case EQUAL_SIGN:
                return "EQUAL_SIGN";
            case MINUS_SIGN:
                return "MINUS_SIGN";
            case MOD_SIGN:
                return "MOD_SIGN";
            case NUMBER_COMMA:
                return "NUMBER_COMMA";
            case PLUS_SIGN:
                return "PLUS_SIGN";
            case POINT:
                return "POINT";
            case POW_SIGN:
                return "POW_SIGN";
            case TIMES_SIGN:
                return "TIMES_SIGN";
            case VOTE_QUOTE:
                return "VOTE_QUOTE";
            case VOTE_POSSESSIVE:
                return "VOTE_POSSESSIVE";
            case OBJECT:
                return "OBJECT";
            case SUBJECT:
                return "SUBJECT";
            default:
                return "none";
        }
    }
}
