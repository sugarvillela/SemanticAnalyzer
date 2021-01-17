package toktools;

import java.util.ArrayList;
import java.util.Stack;

/**A more complex string tokenizer.
 * Supports multiple delimiters
 * Ignores adjacent delimiters to prevent empty elements
 * Supports 'skip area' (quoted or bracketed text). Tokenizer leaves these areas joined.
 * Supports multiple, nested skip symbols. Outermost symbol defines skip area.
 * Option to keep or discard delimiters, skip symbols.
 * Use builder to set options.
 *
 * Sample usage:
 *   String text = "Sentence__with_(too_many_'delims')_and_quotes__";
 *   String[] tok = new Tokenizer.Builder().delimiters(" _").skipSymbols("('").build().parse(text).getArray();
 * Output:
 * 	  Sentence
 * 	  with
 * 	  too_many_'delims'
 * 	  and
 * 	  quotes
 */
public class Tokenizer {
    protected  String delimiters;     // input text, list of delimiters text,
    protected char[] oMap, cMap;        // matched open/close skip char arrays
    protected ArrayList<String> tokens; // output
    protected Stack<Character> cSymbols;  // Closing symbol
    protected boolean delimiterToElement;          // keep delims, skips to separate list
    protected boolean keepSkipSymbol;
    protected int index, start;

    public Tokenizer(){
        delimiters = " ";
        delimiterToElement = false;
        keepSkipSymbol = false;
    }

    private void setMap(String skips){
        // map openers to closers, using symbols from arg
        // if you want different symbols, edit this or add a strategy pattern
        oMap =  new char[skips.length()];
        cMap =  new char[skips.length()];
        char[] openers = new char[]{'(','{','[','<','"','\''};
        char[] closers = new char[]{')','}',']','>','"','\''};
        int to = 0;
        for (int i = 0; i < openers.length; i++) {
            if(skips.indexOf(openers[i])!=-1){
                oMap[to]=openers[i];
                cMap[to]=closers[i];
                to++;
            }
        }
        //Commons.disp(oMap, "oMap");
        //Commons.disp(cMap, "cMap");
    }

    private boolean isDelimiter(char symb){
        return delimiters.indexOf(symb) != -1;
    }
    private boolean haveText(){
        return index != start;
    }
    private boolean enterSkipArea(char symbol){
        for(int i=0; i<oMap.length; i++){
            if(symbol == oMap[i]){
                this.cSymbols.push(cMap[i]);// important side effect
                System.out.println("entering skip area: "+symbol + " until " + cMap[i]);
                return true;
            }
        }
        return false;
    }
    private boolean inSkipArea(){
        return !cSymbols.isEmpty();
    }
    private boolean leaveSkipArea(char symbol){
        if(cSymbols.peek().equals(symbol)){
            cSymbols.pop();
            return true;
        }
        return false;
    }
    private boolean noMoreSkips(){
        //System.out.println("\nclearHolding: "+cSymb.peek());

        //System.out.println(cSymb);
        //System.out.println(cSymb.empty());
        return cSymbols.empty();
    }

    public Tokenizer parse(String text){
        if(oMap == null){
            oMap = new char[0];
        }
        cSymbols = new Stack<>();
        this.tokens = new ArrayList<>();

        start = 0;
        //int i;
        for (index = 0; index < text.length(); index++) {
            char curr = text.charAt(index);

            if(inSkipArea()){
                if(leaveSkipArea(curr)){
                    if(noMoreSkips() && haveText()){
                        int offset = (keepSkipSymbol)? 1 : 0;

                        tokens.add(text.substring(start, index + offset));
                        start = index + 1;
                    }

                }
                else if(enterSkipArea(curr)){}
            }
            else if(enterSkipArea(curr)){
                if(haveText()){
                    tokens.add(text.substring(start, index));
                    start = index;
                }
                if(!keepSkipSymbol){
                    start += 1;
                }
            }
            else if(isDelimiter(curr)){
                if(haveText()){
                    tokens.add(text.substring(start, index));
                }
                if(delimiterToElement){
                    tokens.add(text.substring(index, index+1));
                }
                start=index + 1;
            }
        }

        if(haveText()){
            tokens.add(text.substring(start, index));
        }
        return this;
    }

    public ArrayList<String> getArrayList(){
        return this.tokens;
    }

    public String[] getArray(){
        return this.tokens.toArray(new String[0]);
    }

    public static class Builder{
        Tokenizer built;

        public Builder(){
            built = new Tokenizer();
        }

        /**Supports multiple delimiters
         * @param delimiters All delimiters, for example: " _-"
         */
        public Builder delimiters(String delimiters){
            built.delimiters = delimiters;
            return this;
        }

        /**Supports multiple delimiters
         * @param oneDelimiter Can pass single char, for example ' '
         */
        public Builder delimiters(char oneDelimiter){
            built.delimiters = String.valueOf(oneDelimiter);
            return this;
        }

        /**Areas enclosed in symbols are skipped by the tokenizer
         * Supports '(','{','[','<', single- and double-quote
         * Automatically adds the appropriate closing symbols
         * @param openingSymbols All opening symbols, for example "({'"
         */
        public Builder skipSymbols(String openingSymbols){
            built.setMap(openingSymbols);
            return this;
        }

        public Builder skipSymbols(char openingSymbol){
            built.setMap(String.valueOf(openingSymbol));
            return this;
        }

        /**To use symbols not already provided, pass your own
         * @param oMap opening symbols
         * @param cMap closing symbols, must match oMap index and size
         */
        public Builder skipSymbols(char[] oMap, char[]cMap){
            built.oMap = oMap;
            built.cMap = cMap;
            return this;
        }

        /**Tokenizer removes outermost skip symbols by default
         * Setting keepSkipSymbol leaves the symbols in */
        public Builder keepSkipSymbol(){
            built.keepSkipSymbol = true;
            return this;
        }

        /**Tokenizer discards delimiters by default
         * Setting delimiterToElement causes delimiter to be written to
         * its own element (repeated delimiters are not ignored) */
        public Builder delimiterToElement(){
            built.delimiterToElement = true;
            return this;
        }

        public Tokenizer build(){
            if(built.oMap == null){
                built.oMap = new char[0];
            }
            return built;
        }
    }
}
