package toktools;

import commons.Commons;

public class TestTokenizer {
    public static void testSimple(){
        String text = "This here is   a   sentence  (and another phrase) with  too many   spaces";
        String[] tok = new TokenizerSimple().getArray(text);
        Commons.disp(tok, "result");
    }
    public static void testTokenizer(){
        //String text = "This here is   a   sentence  (and another phrase) with  too many   spaces";
        String text = "sentence(in here)word ";
        //String[] tok = new Tokenizer.Builder().build().parse(text).getArray();
        String[] tok = new Tokenizer.Builder().skipSymbols('(').build().parse(text).getArray();
        Commons.disp(tok, "result");
    }
    public static void testMultiSkip(){
        //String text = "sentence (word 'in quotes') word";
        String text = "Sentence__with_(too_many_'delims')__and_quotes__";
        String[] tok = new Tokenizer.Builder().delimiters(" _").skipSymbols("('").build().parse(text).getArray();
        Commons.disp(tok, "result");
    }
}
