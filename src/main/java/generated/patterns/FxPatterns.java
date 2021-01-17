package generated.patterns;

import rxfxcore.fximpl.FxPatternT;
import rxfxcore.interfaces.FxPattern;
import rxfxcore.interfaces.FxWord;

import static generated.patterns.FxWords.*;

public abstract class FxPatterns {
    public static final FxPattern FX_PATTERN_000 = new FxPattern000();

    public static final FxPattern FX_PATTERN_100 = new FxPattern100();
    public static final FxPattern FX_PATTERN_101 = new FxPattern101();
    public static final FxPattern FX_PATTERN_102 = new FxPattern102();
    public static final FxPattern FX_PATTERN_103 = new FxPattern103();

    public static class FxPattern000 extends FxPatternT {
        public FxPattern000(){
            initFxWords(FX_WORD_000A, FX_WORD_000B);
        }
    }

    public static class FxPattern100 extends FxPatternT {
        public FxPattern100(){
            initFxWords(FX_WORD_100A);
        }
    }
    public static class FxPattern101 extends FxPatternT {
        public FxPattern101(){
            initFxWords(FX_WORD_101A);
        }
    }
    public static class FxPattern102 extends FxPatternT {
        public FxPattern102(){
            initFxWords(FX_WORD_102A);
        }
    }
    public static class FxPattern103 extends FxPatternT {
        public FxPattern103(){
            initFxWords(FX_WORD_103A);
        }
    }

}
