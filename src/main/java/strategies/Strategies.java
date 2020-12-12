package strategies;

import store.IStore;

public class Strategies {
    public static class WordTrait{
        boolean alpha, allAlpha;
        boolean numeric, allNumeric;
        boolean symbol, allSymbol;

        public void go(String word, IStore store){
            int len = word.length();
            for(int i = 0; i < len; i++){
                char curr = word.charAt(i);


            }
        }
        private void alpha(char curr){

        }
    }
}
