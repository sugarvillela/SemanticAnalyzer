package generated.patterns;

import rxword.impl.RxWordImpl;

import static generated.funpatterns.RxFunPatterns.*;

/* Constructors only, presents rxTree*/
public abstract class RxWords {
    public static final RxWordImpl RX_WORD_000_00 = new RxWord000_00();
    public static final RxWordImpl RX_WORD_000_01 = new RxWord000_01();
    public static final RxWordImpl RX_WORD_000_02 = new RxWord000_02();

    public static final RxWordImpl RX_WORD_001_00 = new RxWord001_00();
    public static final RxWordImpl RX_WORD_001_01 = new RxWord001_01();

    /*=====test source language, to be generated======================================================================*/
    public static class RxWord000_00 extends RxWordImpl {
        public RxWord000_00() {
            super(1, 3);
        }

        @Override
        public boolean go() {
            return f.run(RX_FUN_PATTERN_000_00_00);
        }
    }
    public static class RxWord000_01 extends RxWordImpl {
        public RxWord000_01() {
            super(1, 1);
        }
        @Override
        public boolean go() {
            return f.run(RX_FUN_PATTERN_000_01_00);
        }
    }
    public static class RxWord000_02 extends RxWordImpl {
        public RxWord000_02() {
            super(1, 1);
        }
        @Override
        public boolean go() {
            return f.run(RX_FUN_PATTERN_000_02_00);
        }
    }

    public static class RxWord001_00 extends RxWordImpl {
        public RxWord001_00() {
            super(1, 1);
        }
        @Override
        public boolean go() {
            return f.run(RX_FUN_PATTERN_001_00_00);
        }
    }
    public static class RxWord001_01 extends RxWordImpl {
        public RxWord001_01() {
            super(1, 1);
        }
        @Override
        public boolean go() {
            return f.run(RX_FUN_PATTERN_001_01_00);
        }
    }
}
