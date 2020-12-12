package generated.patterns;

import recursivelist.IRecursiveNode;
import rxcore.RxEngineSourceLang;
import rxcore.RxEngineTargLang;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static generated.lists.ListString.IN;

public abstract class PatternTargLang implements PatternDefinition {
    protected final Pattern pattern;
    protected final int[] groupsOfInterest;
    protected final Object[] actionsOnMatch;
    protected String[] stringResults;
    protected int matchedIndex;

    protected PatternTargLang(Pattern pattern, int[] groupsOfInterest, Object[] actionsOnMatch) {
        this.pattern = pattern;
        this.groupsOfInterest = groupsOfInterest;
        this.actionsOnMatch = actionsOnMatch;
    }

    @Override
    public boolean test(IRecursiveNode... flagNode) {
        return this.test(0, flagNode);
    }

    @Override
    public boolean test(int index, IRecursiveNode... flagNode) {
        String text = flagNode[0].getString(IN);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            //System.out.println("found: "+text + ", pattern: "+pattern.pattern());
            if (groupsOfInterest != null) {
                stringResults = new String[groupsOfInterest.length];
                for (int i = 0; i < groupsOfInterest.length; i++) {
                    if (groupsOfInterest[i] > matcher.groupCount()) {
                        throw new IllegalStateException(autoDebugTest(matcher));
                    }
                    stringResults[i] = matcher.group(groupsOfInterest[i]);
                }
                matchedIndex = index;
            }
            return true;
        }
        return false;
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
    public void getResult(RxEngineTargLang requester) {
        requester.receive(this, matchedIndex, stringResults);
    }

    @Override
    public void getResult(RxEngineSourceLang requester) {}

    @Override
    public Object[] actionsOnMatch() {
        return actionsOnMatch;
    }

    @Override
    public Object getPattern() {
        return null;
    }

    @Override
    public String toString() {
        return "PatternTargLang{" +
                "\n    pattern=" + pattern +
                "\n    groupsOfInterest=" + Arrays.toString(groupsOfInterest) +
                "\n    actionsOnMatch=" + Arrays.toString(actionsOnMatch) +
                "\n}";
    }
}
