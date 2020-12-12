// Generated file, do not edit
// Last write: 12/03/2020 21:23:09
package generated.lists;

public class ListVote {
    public static final int SEN_TRAIT = 0x03000000;
    
    public static final int NUM_LIST = 0x03000001;
    public static final int SYMBOL_TRANSITION = 0x03100010;
    public static final int PHRASE_SEPARATOR = 0x03200100;
    public static final int END_SEN = 0x03301000;
    public static final int END_CLAUSE = 0x03410000;
    public static final int COLON = 0x04000001;
    public static final int ELLIPSIS = 0x04100010;
    public static final int EXCLAMATION = 0x04200100;
    public static final int QUESTION = 0x04301000;
    public static final int SEMICOLON = 0x04410000;
    
    public static final int QUANTIFIER = 0x05000001;
    
    public static final int PLURAL = 0x05000001;
    public static final int NON_COUNT = 0x05100010;
    public static final int SINGULAR = 0x05200100;
    
    public static final int POS = 0x05301000;
    
    public static final int ADJ = 0x05301000;
    public static final int ADV = 0x05410000;
    public static final int CONJUNCTION = 0x06000001;
    public static final int DETERMINER = 0x06100010;
    public static final int IRREG = 0x06200100;
    public static final int MODAL = 0x06301000;
    public static final int NAME = 0x06410000;
    public static final int NOUN = 0x07000001;
    public static final int PREP = 0x07100010;
    public static final int PREP_ADV = 0x07200100;
    public static final int PRONOUN = 0x07301000;
    public static final int VERB = 0x07410000;
    
    public static final int VERB_TENSE = 0x08000001;
    
    public static final int FUTURE = 0x08000002;
    public static final int GERUND = 0x08100010;
    public static final int INFINITIVE = 0x08200100;
    public static final int PAST = 0x08301000;
    public static final int PAST_PARTICIPLE = 0x08410000;
    public static final int PRESENT = 0x09000001;
    public static final int PRESENT_PERFECT = 0x09100010;
    public static final int PROGRESSIVE = 0x09200100;
    
    public static final int GENDER = 0x09301000;
    
    public static final int M = 0x09302000;
    public static final int F = 0x09410000;
    
    public static final int MATHY = 0x0A000001;
    
    public static final int ASTERISK = 0x0A000002;
    public static final int CARAT = 0x0A100010;
    public static final int COMMA = 0x0A200100;
    public static final int HYPHEN = 0x0A301000;
    public static final int PERCENT = 0x0A410000;
    public static final int PERIOD = 0x0B000001;
    public static final int SLASH = 0x0B100010;
    public static final int DIV_SIGN = 0x0B200100;
    public static final int EQUAL_SIGN = 0x0B301000;
    public static final int MINUS_SIGN = 0x0B410000;
    public static final int MOD_SIGN = 0x0C000001;
    public static final int NUMBER_COMMA = 0x0C100010;
    public static final int PLUS_SIGN = 0x0C200100;
    public static final int POINT = 0x0C301000;
    public static final int POW_SIGN = 0x0C410000;
    public static final int TIMES_SIGN = 0x0D000001;
    
    public static final int SQ_VOTE = 0x0D100010;
    
    public static final int VOTE_QUOTE = 0x0D100020;
    public static final int VOTE_POSSESSIVE = 0x0D200100;
    
    public static final int SUBJECT_OBJECT = 0x0D301000;
    
    public static final int OBJECT = 0x0D302000;
    public static final int SUBJECT = 0x0D410000;
    
    public static String categoryByRange (int index) {
        if (
            (0x03000001 <= index && index <= 0x04410000)
        ) {
            return "SEN_TRAIT";
        }
        if (
            (0x05000002 <= index && index <= 0x05200100)
        ) {
            return "QUANTIFIER";
        }
        if (
            (0x05302000 <= index && index <= 0x07410000)
        ) {
            return "POS";
        }
        if (
            (0x08000002 <= index && index <= 0x09200100)
        ) {
            return "VERB_TENSE";
        }
        if (
            (0x09302000 <= index && index <= 0x09410000)
        ) {
            return "GENDER";
        }
        if (
            (0x0A000002 <= index && index <= 0x0D000001)
        ) {
            return "MATHY";
        }
        if (
            (0x0D100020 <= index && index <= 0x0D200100)
        ) {
            return "SQ_VOTE";
        }
        if (
            (0x0D302000 <= index && index <= 0x0D410000)
        ) {
            return "SUBJECT_OBJECT";
        }
        throw new IllegalStateException(String.format("Dev err: unknown datatype: 0x%X", index));
    }
    public static String categoryByBaseIndex (int index) {
        switch (index) {
            case 0x03000000:
                return "SEN_TRAIT";
            case 0x05000001:
                return "QUANTIFIER";
            case 0x05301000:
                return "POS";
            case 0x08000001:
                return "VERB_TENSE";
            case 0x09301000:
                return "GENDER";
            case 0x0A000001:
                return "MATHY";
            case 0x0D100010:
                return "SQ_VOTE";
            case 0x0D301000:
                return "SUBJECT_OBJECT";
            default:
                throw new IllegalStateException(String.format("Dev err: unknown datatype: 0x%X", index));
        }
    }
    public static int baseIndexByRange (int index) {
        if (
            (0x03000001 <= index && index <= 0x04410000)
        ) {
            return 0x03000000;
        }
        if (
            (0x05000002 <= index && index <= 0x05200100)
        ) {
            return 0x05000001;
        }
        if (
            (0x05302000 <= index && index <= 0x07410000)
        ) {
            return 0x05301000;
        }
        if (
            (0x08000002 <= index && index <= 0x09200100)
        ) {
            return 0x08000001;
        }
        if (
            (0x09302000 <= index && index <= 0x09410000)
        ) {
            return 0x09301000;
        }
        if (
            (0x0A000002 <= index && index <= 0x0D000001)
        ) {
            return 0x0A000001;
        }
        if (
            (0x0D100020 <= index && index <= 0x0D200100)
        ) {
            return 0x0D100010;
        }
        if (
            (0x0D302000 <= index && index <= 0x0D410000)
        ) {
            return 0x0D301000;
        }
        throw new IllegalStateException(String.format("Dev err: unknown datatype: 0x%X", index));
    }
    public static int offset () {
        return 0x03;
    }
}
