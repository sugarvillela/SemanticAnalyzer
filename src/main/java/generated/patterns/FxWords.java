package generated.patterns;

import rxfxcore.fximpl.FxWordS;
import rxfxcore.fximpl.FxWordT;
import rxfxcore.interfaces.FxWord;

import static generated.enums.ACTION.*;
import static generated.enums.DATATYPE.*;
import static generated.lists.ListString.*;
import static generated.lists.ListVote.*;

public abstract class FxWords {
    public static final FxWord FX_WORD_000A = new FxWord000A();
    public static final FxWord FX_WORD_000B = new FxWord000B();

    public static final FxWord FX_WORD_100A = new FxWord100A();
    public static final FxWord FX_WORD_101A = new FxWord101A();
    public static final FxWord FX_WORD_102A = new FxWord102A();
    public static final FxWord FX_WORD_103A = new FxWord103A();

    public static class FxWord000A extends FxWordS {
        public FxWord000A(){
            initActions(SET);
            initDatatypes(LIST_STRING);
            initEnus(IN);
            initStrings("the");
            initRuntimeTargets(1);
        }
    }
    public static class FxWord000B extends FxWordS {
        public FxWord000B(){
            initActions(SET, SET);
            initDatatypes(LIST_VOTE, LIST_STRING);
            initEnus(VERB, IN);
            initStrings("possess");


            initRuntimeTargets(0);
        }
    }

    public static class FxWord100A extends FxWordT {
        public FxWord100A(){
            initActions(SET);
            initDatatypes(LIST_VOTE);
            initEnus(HYPHEN);

            initRuntimeTargets(1,2);
        }
    }
    public static class FxWord101A extends FxWordT {
        public FxWord101A(){
            initActions(SET, SET_RUNTIME, SET_RUNTIME);
            initDatatypes(LIST_VOTE, LIST_STRING, LIST_STRING);
            initEnus(MINUS_SIGN, IN, EXPAND1);

            initRuntimeTargets(1,3);
        }
    }
    public static class FxWord102A extends FxWordT {
        public FxWord102A(){
            initActions(SET, SET_RUNTIME, SET_RUNTIME);
            initDatatypes(LIST_VOTE, LIST_STRING, LIST_STRING);
            initEnus(SLASH, IN, EXPAND1);

            initRuntimeTargets(1,2);
        }
    }
    public static class FxWord103A extends FxWordT {
        public FxWord103A(){
            initActions(SET);
            initDatatypes(LIST_VOTE);
            initEnus(DIV_SIGN);

            initRuntimeTargets(1,3);
        }
    }
}
