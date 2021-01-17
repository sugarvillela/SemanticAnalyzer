package rxfxcore.rximpl_t;

import generated.enums.PATTERN_TYPE;
import rxfxcore.interfaces.FxPattern;
import rxfxcore.interfaces.FxWord;
import rxfxcore.interfaces.RxResult;
import rxfxcore.interfaces.RxPattern;
import recursivelist.FlagNode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static generated.lists.ListString.IN;

public abstract class RxPatternT implements RxPattern {
    protected final Pattern pattern;
    protected final FxPattern fxPattern; // action on match
    protected FlagNode flagNode;
    protected RxResultT result;
    protected int seqIndex;

    protected RxPatternT(Pattern pattern, FxPattern fxPattern) {
        this.pattern = pattern;
        this.fxPattern = fxPattern;
    }

    @Override
    public PATTERN_TYPE getPatternType() {
        return PATTERN_TYPE.TARG_LANG;
    }

    @Override
    public FxPattern getFxPattern() {
        return this.fxPattern;
    }

    @Override
    public RxPattern setIndex(int index) {
        seqIndex = index;
        return this;
    }

    @Override
    public RxPattern setNodes(FlagNode[] flagNodes) {
        flagNode = flagNodes[0];
        return this;
    }

    @Override
    public boolean go() {
        String text = flagNode.getString(IN);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            //System.out.println("found: "+text + ", pattern: "+pattern.pattern());
            FxWord[] fxWords = fxPattern.getFxWords();
            for(FxWord fxWord : fxWords){
                int[] runtimeTargets = fxWord.stringData().getTargets();

                if (runtimeTargets != null) {
                    String[] stringResults = new String[runtimeTargets.length];

                    for (int i = 0; i < runtimeTargets.length; i++) {
                        if (runtimeTargets[i] > matcher.groupCount()) {
                            throw new IllegalStateException(autoDebugTest(matcher));
                        }
                        stringResults[i] = matcher.group(runtimeTargets[i]);
                    }

                    fxWord.stringData().setStrings(stringResults);
                }
                else{
                    fxWord.stringData().setStrings(null);
                }

            }

            result = new RxResultT();
            result.setSeqIndex(seqIndex);
            return true;
        }
        return false;
    }

    @Override
    public RxResult getResult() {
        return result;
    }

    @Override
    public void disp() {
        System.out.println(this.toString());
    }

    protected String autoDebugTest(Matcher matcher) {
        int count = matcher.groupCount();
        String[] groups = new String[count];
        for (int i = 0; i <= count; i++) {
            groups[i] = String.format("Group %d: %s", i, matcher.group(i));
        }
        return "Available groups:\n" + String.join("\n    ", groups);
    }

    @Override
    public String toString() {
        return "RxPatternT{" +
                "\n    pattern=" + pattern +
                "\n    fxPattern=" + fxPattern +
                "}";
    }
}
