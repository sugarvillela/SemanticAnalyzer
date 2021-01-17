package generated.patterns;

import rxfxcore.interfaces.RxPattern;
import rxfxcore.rximpl_s.RxPatternS;
import rxfxcore.rximpl_t.RxPatternT;

import java.util.regex.Pattern;

import static generated.patterns.FxPatterns.*;
import static generated.patterns.RxWords.*;

public abstract class RxPatterns {
    public static final RxPattern RX_PATTERN_000 = new RxPattern000();
    public static final RxPattern RX_PATTERN_001 = new RxPattern001();

    public static final RxPattern RX_PATTERN_100 = new RxPattern100();
    public static final RxPattern RX_PATTERN_101 = new RxPattern101();
    public static final RxPattern RX_PATTERN_102 = new RxPattern102();
    public static final RxPattern RX_PATTERN_103 = new RxPattern103();

    /*=====test source language, to be generated======================================================================*/
    public static class RxPattern000 extends RxPatternS {
        protected RxPattern000() {
            super(FX_PATTERN_000);
            this.initRxWords(RX_WORD_000_00, RX_WORD_000_01, RX_WORD_000_02);
        }
    }
    public static class RxPattern001 extends RxPatternS {
        protected RxPattern001() {
            super(FX_PATTERN_000);
            this.initRxWords(RX_WORD_001_00, RX_WORD_001_01);
        }
    }

    /*=====test target language, to be generated======================================================================*/
    //hyphen word
    public static class RxPattern100 extends RxPatternT {
        protected RxPattern100() {
            super(
                    Pattern.compile("^([A-Za-z]+)[-]([A-Za-z]+)$"),
                    FX_PATTERN_100
            );
        }

    }
    //num subtract
    public static class RxPattern101 extends RxPatternT {
        protected RxPattern101() {
            super(
                    Pattern.compile("^(([0-9]*[.])?[0-9]+)[-](([0-9]*[.])?[0-9]+)$"),
                    FX_PATTERN_101
            );
        }
    }
    //slash word
    public static class RxPattern102 extends RxPatternT {
        protected RxPattern102() {
            super(
                    Pattern.compile("^([A-Za-z]+)[/]([A-Za-z]+)$"),
                    FX_PATTERN_102
            );
        }
    }
    //num divide
    public static class RxPattern103 extends RxPatternT {
        protected RxPattern103() {
            super(
                    Pattern.compile("^(([0-9]*[.])?[0-9]+)[/](([0-9]*[.])?[0-9]+)$"),
                    FX_PATTERN_103
            );
        }
    }
}
