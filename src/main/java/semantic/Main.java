package semantic;

import toktools.TokenizerSimple;

public class Main {

    public static void main(String[] args) {
	    initAnalyzer(new String[]{"This is a sentence   with extra spaces.  "});
    }
    public static void initAnalyzer(String[] args) {
        String text = args[0];
        TokenizerSimple tok = new TokenizerSimple();
        String[] toks = tok.getArray(text);
        commons.Commons.disp(toks, "Tokenized");
//        ItrList<String> list = new ItrList<>(toks);
//        list.rewind();
//        int i = -1;
//
//        System.out.println("\nList:" );
//        while(list.hasNext() && 8 > i++){
//            System.out.println(i + ": " + list.next());
//        }
    }
}
