package store;

import commons.BIT;

import static generated.lists.ListString.*;
import static generated.lists.ListNumber.*;
import static generated.lists.ListDiscrete.*;
import static generated.lists.ListBoolean.*;

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
        store.set(DAVE, "01");
        store.set(IRVING, "02");
        store.set(IN, "08");
        store.set(SURNAME, "15");
        store.set(INDEX, 2);

        store.set(INFINITIVE);//discrete
        store.set(TEN);//discrete
        store.set(MORE);//boolean state
        store.set(MODAL);//boolean pos
        store.disp();

        System.out.println("getBoolean(DAVE): " + store.getBoolean(DAVE));
        System.out.println("getBoolean(IRVING): " + store.getBoolean(IRVING));
        System.out.println("getBoolean(LUPE): " + store.getBoolean(LUPE));
        System.out.println("drop: IRVING");
        store.drop(IRVING);
        System.out.println("getBoolean(IRVING): " + store.getBoolean(IRVING));
        System.out.println();

        System.out.println("getBoolean(INDEX): " + store.getBoolean(INDEX));
        System.out.println("getBoolean(GROUP): " + store.getBoolean(GROUP));
        System.out.println("drop: INDEX");
        store.drop(INDEX);
        System.out.println("getBoolean(INDEX): " + store.getBoolean(INDEX));
        System.out.println();

        System.out.println("getBoolean(INFINITIVE): " + store.getBoolean(INFINITIVE));
        System.out.println("getBoolean(TEN): " + store.getBoolean(TEN));
        System.out.println("getBoolean(NINE): " + store.getBoolean(NINE));
        System.out.println("drop: INFINITIVE");
        store.drop(INFINITIVE);
        System.out.println("getBoolean(INFINITIVE): " + store.getBoolean(INFINITIVE));
        System.out.println();

        System.out.println("getBoolean(MORE): " + store.getBoolean(MORE));
        System.out.println("getBoolean(DONE): " + store.getBoolean(DONE));
        System.out.println("drop: MORE");
        store.drop(MORE);
        System.out.println("getBoolean(MORE): " + store.getBoolean(MORE));
        System.out.println();

    }
    public static void anyNonZeroDiscrete(){
        Store store = new Store();
        System.out.println("byBaseIndex: anyNonZero(VERB_FORMS): " + store.anyNonZero(VERB_FORMS));
        System.out.println("byBaseIndex: anyNonZero(NUMBERS): " + store.anyNonZero(NUMBERS));
        System.out.println("set: INFINITIVE");
        store.set(INFINITIVE);//discrete
        System.out.println("byBaseIndex:  anyNonZero(VERB_FORMS): " + store.anyNonZero(VERB_FORMS));
        System.out.println("byBaseIndex:  anyNonZero(NUMBERS): " + store.anyNonZero(NUMBERS));

        System.out.println("byRange: anyNonZero(PRESENT): " + store.anyNonZero(PRESENT));
        System.out.println("byRange: anyNonZero(ONE): " + store.anyNonZero(ONE));
        System.out.println("set: TEN");
        store.set(TEN);//discrete
        System.out.println("byRange:  anyNonZero(VERB_FORMS): " + store.anyNonZero(PRESENT));
        System.out.println("byRange:  anyNonZero(NUMBERS): " + store.anyNonZero(ONE));
    }
    public static void anyNonZeroBoolean(){
        Store store = new Store();
        System.out.println("byBaseIndex: anyNonZero(COLORS): " + store.anyNonZero(COLORS));
        System.out.println("byBaseIndex: anyNonZero(POS): " + store.anyNonZero(POS));
        System.out.println("set: GREEN");
        store.set(GREEN);
        System.out.println("byBaseIndex:  anyNonZero(COLORS): " + store.anyNonZero(COLORS));
        System.out.println("byBaseIndex:  anyNonZero(POS): " + store.anyNonZero(POS));

        System.out.println("byRange: anyNonZero(FUSCIA): " + store.anyNonZero(FUSCIA));
        System.out.println("byRange: anyNonZero(VERB): " + store.anyNonZero(VERB));
        System.out.println("set: NOUN");
        store.set(NOUN);
        System.out.println("byRange:  anyNonZero(FUSCIA): " + store.anyNonZero(FUSCIA));
        System.out.println("byRange:  anyNonZero(VERB): " + store.anyNonZero(VERB));

        System.out.println("drop: NOUN");
        store.drop(NOUN);
        System.out.println("byRange:  anyNonZero(FUSCIA): " + store.anyNonZero(FUSCIA));
        System.out.println("byRange:  anyNonZero(VERB): " + store.anyNonZero(VERB));
    }
    public static void numNonZeroDiscrete(){
        Store store = new Store();
        System.out.println("byBaseIndex: numNonZero(VERB_FORMS): " + store.numNonZero(VERB_FORMS));
        System.out.println("byBaseIndex: numNonZero(NUMBERS): " + store.numNonZero(NUMBERS));
        System.out.println("set: INFINITIVE");
        store.set(INFINITIVE);
        System.out.println("byBaseIndex: numNonZero(VERB_FORMS): " + store.numNonZero(VERB_FORMS));
        System.out.println("byBaseIndex: numNonZero(NUMBERS): " + store.numNonZero(NUMBERS));

        System.out.println("set: PAST");
        store.set(PAST);
        System.out.println("byBaseIndex: numNonZero(VERB_FORMS): " + store.numNonZero(VERB_FORMS));
        System.out.println("byBaseIndex: numNonZero(NUMBERS): " + store.numNonZero(NUMBERS));

        System.out.println("drop: PAST");
        store.drop(PAST);
        System.out.println("byBaseIndex: numNonZero(VERB_FORMS): " + store.numNonZero(VERB_FORMS));
        System.out.println("byBaseIndex: numNonZero(NUMBERS): " + store.numNonZero(NUMBERS));
    }
    public static void numNonZeroBoolean(){
        Store store = new Store();
        System.out.println("byBaseIndex: numNonZero(COLORS): " + store.numNonZero(COLORS));
        System.out.println("byBaseIndex: numNonZero(POS): " + store.numNonZero(POS));
        System.out.println("set: GREEN");
        store.set(GREEN);
        System.out.println("byBaseIndex: numNonZero(COLORS): " + store.numNonZero(COLORS));
        System.out.println("byBaseIndex: numNonZero(POS): " + store.numNonZero(POS));

        System.out.println("set: FUSCIA");
        store.set(FUSCIA);
        System.out.println("set: RED");
        store.set(RED);
        System.out.println("byBaseIndex: numNonZero(COLORS): " + store.numNonZero(COLORS));
        System.out.println("byBaseIndex: numNonZero(POS): " + store.numNonZero(POS));

        System.out.println("drop: RED");
        store.drop(RED);
        System.out.println("byBaseIndex: numNonZero(COLORS): " + store.numNonZero(COLORS));
        System.out.println("byBaseIndex: numNonZero(POS): " + store.numNonZero(POS));
    }
}
