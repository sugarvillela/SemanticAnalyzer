package generated.patterns;

import java.util.regex.Pattern;

import static generated.lists.ListVote.*;

public abstract class Patterns {
    public static final PatternDefinition PATTERN_000 = new Pattern000();
    public static final PatternDefinition PATTERN_001 = new Pattern001();
    public static final PatternDefinition PATTERN_002 = new Pattern002();
    public static final PatternDefinition PATTERN_003 = new Pattern003();

    //hyphen word
    public static class Pattern000 extends PatternTargLang {
        protected Pattern000() {
            super(
                Pattern.compile("^([A-Za-z]+)[-]([A-Za-z]+)$"),
                new int[]{1, 2},
                new Integer[]{HYPHEN}
            );
        }

    }
    //num subtract
    public static class Pattern001 extends PatternTargLang {
        protected Pattern001() {
            super(
                    Pattern.compile("^(([0-9]*[.])?[0-9]+)[-](([0-9]*[.])?[0-9]+)$"),
                    new int[]{1, 3},
                    new Integer[]{MINUS_SIGN}
            );
        }
    }
    //dash word
    public static class Pattern002 extends PatternTargLang {
        protected Pattern002() {
            super(
                    Pattern.compile("^([A-Za-z]+)[/]([A-Za-z]+)$"),
                    new int[]{1, 2},
                    new Integer[]{SLASH}
            );
        }
    }
    //num divide
    public static class Pattern003 extends PatternTargLang {
        protected Pattern003() {
            super(
                    Pattern.compile("^(([0-9]*[.])?[0-9]+)[/](([0-9]*[.])?[0-9]+)$"),
                    new int[]{1, 3},
                    new Integer[]{DIV_SIGN}
            );
        }
    }
//    public static class Pattern004 extends PatternSourceLang {
//        protected Pattern004() {
//            super(0,0,null
//
//            );
//        }
//    }
}
