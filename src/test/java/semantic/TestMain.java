package semantic;

import runstate.RunState;
import rxfxcore.CoreTests;

public class TestMain {
    public static void main(String[] args) {
        System.out.println("Semantic Analyzer...");

        String text = "i have have a dream said he have a dream";//"zero one two three four five six three four nine ten";//"abc/def ghi-klmn 012/345 678-910 Schnitzel 250,000";//
        RunState.initText(text);

        //UtilTest.makeListForTest();
        CoreTests.testSourceLang();
        //StoreTest.testItrAll();

    }
}
