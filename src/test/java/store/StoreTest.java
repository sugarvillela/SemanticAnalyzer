package store;

import commons.Commons;
import generated.enums.DATATYPE;
import store.util.VoteUtil;

import static generated.lists.ListString.*;
import static generated.lists.ListNumber.*;
import static generated.lists.ListDiscrete.*;
import static generated.lists.ListBoolean.*;
import static generated.lists.ListVote.*;

public class StoreTest {
    public static void discrete(){
//        StoreDiscrete store = new StoreDiscrete(new int[6]);
//        int heading1 =  commons.BIT.binStrToInt("0000_0001_0000_0000_0000_0000_0000_0000");
//        int heading2 =  commons.BIT.binStrToInt("0000_0010_0000_0000_0000_0000_0000_0000");
//        int heading3 =  commons.BIT.binStrToInt("0000_0011_0000_0000_0000_0000_0000_0000");
//
//        int fred =      commons.BIT.binStrToInt("0000_0011_0000_0000_0000_0000_0000_0011");
//        int barney =    commons.BIT.binStrToInt("0000_0011_0100_1110_0000_0000_0000_0000");
//        int wilma =     commons.BIT.binStrToInt("0000_0000_0000_0000_0000_0000_0000_1000");
//        int pebbles =   commons.BIT.binStrToInt("0000_0010_0010_0000_0000_1001_0000_0000");
//        int bamBam =    commons.BIT.binStrToInt("0000_0001_0100_0111_0000_0000_0000_0000");
//        //System.out.println(n);
//        //BIT.disp(n);
//        store.set(fred);
//        store.set(barney);
//        store.set(wilma);
//        store.set(pebbles);
//        store.set(bamBam);
//
//        store.disp();
//
//        System.out.print("fred state:    "); BIT.disp(store.getState(fred));
//        System.out.print("fred getNumber: "); BIT.disp(store.getNumber(fred));
//        System.out.println("isSet bamBam: " + store.getBoolean(bamBam));
//        System.out.println("isSet almost: " + store.getBoolean(commons.BIT.binStrToInt("0000_0100_0100_0110_0000_0000_0000_0000")));
//        System.out.println("isSet random: " + store.getBoolean(commons.BIT.binStrToInt("0000_0001_0010_0000_0000_0100_0000_0000")));
//        System.out.println("num set 1:     " +  store.numNonZero(heading1));
//        System.out.println("num set 2:     " +  store.numNonZero(heading2));
//        System.out.println("num set 3:     " +  store.numNonZero(heading3));
//        //store.seek(pebbles);
//        //System.out.printf("pebbles at Row=%x Col=%x \n", store.getSeekRow(), store.getSeekCol());
//        System.out.println("\nAfter drop wilma and barney");
//        store.drop(wilma);
//        store.drop(barney);
//        store.disp();
    }
    public static void flags(){
//        StoreBoolean store = new StoreBoolean(new int[6]);
//        int heading1 =  commons.BIT.binStrToInt("0000_0001_0000_0000_0000_0000_0000_0000");
//        int heading2 =  commons.BIT.binStrToInt("0000_0010_0000_0000_0000_0000_0000_0000");
//        int heading3 =  commons.BIT.binStrToInt("0000_0011_0000_0000_0000_0000_0000_0000");
//
//        int fred =      commons.BIT.binStrToInt("0000_0001_0000_0000_0000_0000_0000_1000");
//        int barney =    commons.BIT.binStrToInt("0000_0010_0000_0000_0001_0000_0000_0000");
//        int wilma =     commons.BIT.binStrToInt("0000_0010_0000_0000_0010_0000_0000_0000");
//        int pebbles =   commons.BIT.binStrToInt("0000_0010_0000_0000_0100_0000_0000_0000");
//        int bamBam =    commons.BIT.binStrToInt("0000_0010_0000_0000_1000_0000_0000_0000");
//        //System.out.println(n);
//        //BIT.disp(n);
//        store.set(fred);
//        store.set(barney);
//        store.set(wilma);
//        store.set(pebbles);
//        store.set(bamBam);
//
//        store.disp();
//
//        System.out.print  ("fred state:     "); BIT.disp(store.getState(fred));
//        System.out.print  ("fred getNumber: "); BIT.disp(store.getNumber(fred));
//        System.out.println("fred isSet:     " + store.getBoolean(fred));
//        System.out.println("random isSet:   " + store.getBoolean(commons.BIT.binStrToInt("0000_0010_0000_0000_0000_1000_0000_0000")));
//        System.out.println("num set 1:     " +  store.numNonZero(heading1));
//        System.out.println("num set 2:     " +  store.numNonZero(heading2));
//        System.out.println("num set 3:     " +  store.numNonZero(heading3));
//        //store.seek(barney);
//        //System.out.printf("barney at Row=%x \n", store.getSeekRow());
//        System.out.println("\nAfter drop wilma");
//        store.drop(wilma);
//        store.disp();
    }
    public static void store(){
        Store store = new Store();
        store.set(ORIG, "01");
        store.set(IN, "02");
        store.set(ROOT, "08");
        store.set(EXPAND1, "15");
        store.set(VALUE, 2);

        store.set(CHINESE);//discrete
        store.set(JAPANESE);//discrete
        store.set(CAP_FIRST);//boolean WORD_CONTAINS
        store.set(CAP_ALL);//boolean WORD_CONTAINS
        store.dispStore();

        System.out.println("getBoolean(ORIG): " + store.getBoolean(ORIG));
        System.out.println("getString(ORIG): " + store.getString(ORIG));
        System.out.println("getString(IN): " + store.getString(IN));
        System.out.println("getString(EXPAND1): " + store.getString(EXPAND1));
        System.out.println("getNumber(VALUE): " + store.getNumber(VALUE));
        System.out.println("getString(VALUE): " + store.getString(VALUE));
        System.out.println("getBoolean(CHIbNESE): " + store.getBoolean(CHINESE));
        System.out.println("getBoolean(JAPANESE): " + store.getBoolean(JAPANESE));
        System.out.println("getBoolean(GERMAN): " + store.getBoolean(GERMAN));
        System.out.println("drop: IRVING");
        store.drop(JAPANESE);
        System.out.println("getBoolean(JAPANESE): " + store.getBoolean(JAPANESE));
        System.out.println();

    }
    public static void testVote(){
        Store store = new Store();
        store.set(ADJ);
        store.set(ADJ);
        store.set(ADJ);
        store.set(ADJ);
        store.set(DETERMINER);
        store.set(DETERMINER);
        store.set(PREP);
        store.set(PREP);
        store.set(PREP);
        store.set(PREP);
        System.out.println(store.getNumber(ADJ));
        System.out.println(store.getNumber(DETERMINER));
        System.out.println(store.getNumber(PREP));

        VoteUtil voteUtil = store.getVoteUtil();
        voteUtil.tallyVotes(ADJ, VERB);
        if(voteUtil.isTie()){
            Commons.disp(voteUtil.getTies(), "ties");
        }
        else{
            System.out.printf("bestEnu = 0x%X, bestScore = %d", voteUtil.getBestEnu(), voteUtil.getBestScore());
        }
    }
    public static void testItrDiscrete(){
        Store store = new Store();
        store.set(ADJ);
        //store.set(ADV);
        store.set(CONJUNCTION);
        //store.set(DETERMINER);
        store.set(IRREG);
        //store.set(MODAL);
        store.set(NAME);
        //store.set(NOUN);
        store.set(PREP);
        store.set(PREP);
        store.set(PREP);
        //store.set(PREP_ADV);
        store.set(PRONOUN);
        //store.set(VERB);
        int startEnu = 0x05301000;  // ADJ
        int stopEnu = 0x07410000;   // VERB
        IStore.ItrStore itr = store.getStore(DATATYPE.LIST_VOTE).getStoreItr();//(startEnu, stopEnu);
        int i = 0;
        while(itr.hasNext() && 40 > i++){
            //System.out.printf("%02d: %s \n", i, itr.nextNumber());
            System.out.printf("%02d: %s \n", i, itr.nextString());
        }
        //voteUtil.tallyVotes(start, stop);
    }
    public static void testItrBoolean(){
        Store store = new Store();
//        store.set(ARTICLE);
//        store.set(CONJ_ADV);
        store.set(COORDINATING);
        //store.set(CORRELATING);
        store.set(DEFINITE);
        //store.set(DEMONSTRATIVE);
        store.set(DISTRIBUTIVE);
        //store.set(INDEFINITE);
        store.set(LINKING);
        //store.set(PERSONAL);
        store.set(POSSESSIVE);
        //store.set(QUANTITIVE);
        store.set(RECIPROCAL);
//        store.set(REFLEXIVE);
//        store.set(SUBORDINATING);

        int startEnu = 0x015000004; //COORDINATING
        int stopEnu = 0x015001000; //RECIPROCAL
        IStore.ItrStore itr = store.getStore(DATATYPE.LIST_BOOLEAN).getStoreItr(startEnu, stopEnu);
        int i = 20;
        while(itr.hasNext() && 0 < i--){
            int valCurr = itr.nextNumber();
            System.out.printf("output: valCurr=%d \n", valCurr);
        }
    }
    public static void testItrStrInt(){
        Store store = new Store();
        store.set(IN, "karaoke hero");
        store.set(ORIG, "original text");
        store.set(EXPAND1, "expando uno");

        int startEnu = 0x01; //
        int stopEnu = 0x03; //
        IStore.ItrStore itrString = store.getStore(DATATYPE.LIST_STRING).getStoreItr(startEnu, stopEnu);
        int i = 20;
        while(itrString.hasNext() && 0 < i--){
            int key = itrString.nextKey();
            String valCurr = itrString.nextString();
            System.out.printf("%d: valCurr=%s \n", key, valCurr);
        }

        store.set(VALUE, 69);
        store.set(EE, 6);

        startEnu = VALUE; //
        stopEnu = EE; //
        IStore.ItrStore itrNumber = store.getStore(VALUE).getStoreItr(startEnu, stopEnu);
        i = 20;
        while(itrNumber.hasNext() && 0 < i--){
            int key = itrNumber.nextKey();
            int valCurr = itrNumber.nextNumber();
            System.out.printf("%d: valCurr=%d \n", key, valCurr);
        }
    }
    private static void loadStore(Store store){
        store.set(NUM_LIST);//00 = 4 vote
        store.set(NUM_LIST);
        store.set(NUM_LIST);
        store.set(NUM_LIST);

        store.set(ADJ);//13 = 5
        store.set(ADJ);
        store.set(ADJ);
        store.set(ADJ);
        store.set(ADJ);
        store.set(CONJUNCTION);//15 = 1
        store.set(DETERMINER);//16 = 2
        store.set(DETERMINER);
        store.set(IRREG);//17 = 1
        store.set(NAME);//19 = 1

        store.set(PREP);//21 = 7
        store.set(PREP);
        store.set(PREP);
        store.set(PREP);
        store.set(PREP);
        store.set(PREP);
        store.set(PREP);

        store.set(PRONOUN);//23 = 1
        store.set(VERB);//24 = 1

        store.set(DONE);//2 discrete
        store.set(CHINESE);//4
        store.set(PERSON2);//12

        store.set(AMPERSAND);//0  boolean
        store.set(BACK_SLASH);//2
        store.set(POUND);//4
        store.set(COORDINATING);
        store.set(DEFINITE);
        store.set(DISTRIBUTIVE);
        store.set(LINKING);
        store.set(POSSESSIVE);
        store.set(RECIPROCAL);

        store.set(IN, "love bug");
        store.set(ORIG, "the new original");
        store.set(EXPAND1, "fluffy tiger");
        store.set(VALUE, 69);
        store.set(EE, 6);
    }
    public static void testItrAll(){
        Store store = new Store();
        loadStore(store);
        store.dispStore();
    }
    public static void anyNonZeroDiscrete(){
//        Store store = new Store();
//        System.out.println("byBaseIndex: anyNonZero(VERB_FORMS): " + store.anyNonZero(VERB_FORMS));
//        System.out.println("byBaseIndex: anyNonZero(NUMBERS): " + store.anyNonZero(NUMBERS));
//        System.out.println("set: INFINITIVE");
//        store.set(INFINITIVE);//discrete
//        System.out.println("byBaseIndex:  anyNonZero(VERB_FORMS): " + store.anyNonZero(VERB_FORMS));
//        System.out.println("byBaseIndex:  anyNonZero(NUMBERS): " + store.anyNonZero(NUMBERS));
//
//        System.out.println("byRange: anyNonZero(PRESENT): " + store.anyNonZero(PRESENT));
//        System.out.println("byRange: anyNonZero(ONE): " + store.anyNonZero(ONE));
//        System.out.println("set: TEN");
//        store.set(TEN);//discrete
//        System.out.println("byRange:  anyNonZero(VERB_FORMS): " + store.anyNonZero(PRESENT));
//        System.out.println("byRange:  anyNonZero(NUMBERS): " + store.anyNonZero(ONE));
    }
    public static void anyNonZeroBoolean(){
//        Store store = new Store();
//        System.out.println("byBaseIndex: anyNonZero(COLORS): " + store.anyNonZero(COLORS));
//        System.out.println("byBaseIndex: anyNonZero(POS): " + store.anyNonZero(POS));
//        System.out.println("set: GREEN");
//        store.set(GREEN);
//        System.out.println("byBaseIndex:  anyNonZero(COLORS): " + store.anyNonZero(COLORS));
//        System.out.println("byBaseIndex:  anyNonZero(POS): " + store.anyNonZero(POS));
//
//        System.out.println("byRange: anyNonZero(FUSCIA): " + store.anyNonZero(FUSCIA));
//        System.out.println("byRange: anyNonZero(VERB): " + store.anyNonZero(VERB));
//        System.out.println("set: NOUN");
//        store.set(NOUN);
//        System.out.println("byRange:  anyNonZero(FUSCIA): " + store.anyNonZero(FUSCIA));
//        System.out.println("byRange:  anyNonZero(VERB): " + store.anyNonZero(VERB));
//
//        System.out.println("drop: NOUN");
//        store.drop(NOUN);
//        System.out.println("byRange:  anyNonZero(FUSCIA): " + store.anyNonZero(FUSCIA));
//        System.out.println("byRange:  anyNonZero(VERB): " + store.anyNonZero(VERB));
    }
    public static void numNonZeroDiscrete(){
//        Store store = new Store();
//        System.out.println("byBaseIndex: numNonZero(VERB_FORMS): " + store.numNonZero(VERB_FORMS));
//        System.out.println("byBaseIndex: numNonZero(NUMBERS): " + store.numNonZero(NUMBERS));
//        System.out.println("set: INFINITIVE");
//        store.set(INFINITIVE);
//        System.out.println("byBaseIndex: numNonZero(VERB_FORMS): " + store.numNonZero(VERB_FORMS));
//        System.out.println("byBaseIndex: numNonZero(NUMBERS): " + store.numNonZero(NUMBERS));
//
//        System.out.println("set: PAST");
//        store.set(PAST);
//        System.out.println("byBaseIndex: numNonZero(VERB_FORMS): " + store.numNonZero(VERB_FORMS));
//        System.out.println("byBaseIndex: numNonZero(NUMBERS): " + store.numNonZero(NUMBERS));
//
//        System.out.println("drop: PAST");
//        store.drop(PAST);
//        System.out.println("byBaseIndex: numNonZero(VERB_FORMS): " + store.numNonZero(VERB_FORMS));
//        System.out.println("byBaseIndex: numNonZero(NUMBERS): " + store.numNonZero(NUMBERS));
    }
    public static void numNonZeroBoolean(){
//        Store store = new Store();
//        System.out.println("byBaseIndex: numNonZero(COLORS): " + store.numNonZero(COLORS));
//        System.out.println("byBaseIndex: numNonZero(POS): " + store.numNonZero(POS));
//        System.out.println("set: GREEN");
//        store.set(GREEN);
//        System.out.println("byBaseIndex: numNonZero(COLORS): " + store.numNonZero(COLORS));
//        System.out.println("byBaseIndex: numNonZero(POS): " + store.numNonZero(POS));
//
//        System.out.println("set: FUSCIA");
//        store.set(FUSCIA);
//        System.out.println("set: RED");
//        store.set(RED);
//        System.out.println("byBaseIndex: numNonZero(COLORS): " + store.numNonZero(COLORS));
//        System.out.println("byBaseIndex: numNonZero(POS): " + store.numNonZero(POS));
//
//        System.out.println("drop: RED");
//        store.drop(RED);
//        System.out.println("byBaseIndex: numNonZero(COLORS): " + store.numNonZero(COLORS));
//        System.out.println("byBaseIndex: numNonZero(POS): " + store.numNonZero(POS));
    }
}
