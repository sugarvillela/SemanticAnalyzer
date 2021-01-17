package generated.funpatterns;

import rxword.interfaces.RxFunPattern;
import rxword.impl.RxFunPatternBase;

import static generated.enums.RX_COMPARE.*;
import static generated.lists.ListString.IN;
import static rxword.impl.RxFunSpecials.*;

public class RxFunPatterns {

    public static final RxFunPattern RX_FUN_PATTERN_000_00_00 = new RxFunPattern000_00_00();
    public static final RxFunPattern RX_FUN_PATTERN_000_01_00 = new RxFunPattern000_01_00();
    public static final RxFunPattern RX_FUN_PATTERN_000_02_00 = new RxFunPattern000_02_00();

    public static final RxFunPattern RX_FUN_PATTERN_001_00_00 = new RxFunPattern001_00_00();
    public static final RxFunPattern RX_FUN_PATTERN_001_01_00 = new RxFunPattern001_01_00();

    /*=====source language, to be generated======================================================================*/

    public static class RxFunPattern000_00_00 extends RxFunPatternBase {
        public RxFunPattern000_00_00() {
            initLeft(new StoreGetString(IN));
            initCompare(COMPARE_EQUAL);
            initRight(new valContainerObject("have"));
        }
    }
    public static class RxFunPattern000_01_00 extends RxFunPatternBase {
        public RxFunPattern000_01_00() {
            initLeft(new StoreGetString(IN));
            initCompare(COMPARE_EQUAL);
            initRight(new valContainerObject("a"));
        }
    }
    public static class RxFunPattern000_02_00 extends RxFunPatternBase {
        public RxFunPattern000_02_00() {
            initLeft(new StoreGetString(IN));
            initCompare(COMPARE_EQUAL);
            initRight(new valContainerObject("dream"));
        }
    }

    public static class RxFunPattern001_00_00 extends RxFunPatternBase {
        public RxFunPattern001_00_00() {
            initLeft(new StoreGetString(IN));
            initCompare(COMPARE_EQUAL);
            initRight(new valContainerObject("three"));
        }
    }
    public static class RxFunPattern001_01_00 extends RxFunPatternBase {
        public RxFunPattern001_01_00() {
            initLeft(new StoreGetString(IN));
            initCompare(COMPARE_EQUAL);
            initRight(new valContainerObject("four"));
        }
    }

    public static class Example extends RxFunPatternBase {
        public Example() {
            // getString(IN) -> len() -> range(1-6) = true
            initLeft(new StoreGetString(IN), new Len(), new Range(1,6));
            initCompare(COMPARE_EQUAL);
            initRight(new ValContainerInt(1));
        }
    }
}
