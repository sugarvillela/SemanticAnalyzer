package toktools;

/**A simple string tokenizer.
 * Supports single delimiter
 * Ignores adjacent delimiters to prevent empty elements
 * Option to limit number of splits
 */
public class TokenizerSimple {
    private final char delimiter;
    private final int limit;

    public TokenizerSimple(){
        this( ' ', 0x7FFFFFFF );
    }
    public TokenizerSimple(char delimiter ){
        this( delimiter, 0x7FFFFFFF );
    }
    public TokenizerSimple(char delimiter, int limit ){
        this.delimiter = delimiter;
        this.limit = limit;
    }

    public String[] getArray(String text){
        // Rehearse to get size
        int count = 0;
        int start = 0;
        int i, j = 0;
        for( i=0; i<text.length(); i++ ){
            if( text.charAt(i) == delimiter){
                if( i != start ){
                    count++;
                    // Limit size, if limit passed
                    if( count == limit ){
                        i = start;
                        break;
                    }
                }
                start=i+1;
            }
        }
        if( i != start ){
            count++;
        }
        // Set array and run again to populate
        String[] out = new String[count];
        start = 0;
        for( i=0; i<text.length(); i++ ){
            if( text.charAt(i) == delimiter){
                if( i != start ){
                    if( j >= limit-1){
                        break;
                    }
                    out[j] = text.substring(start, i);
                    j++;

                }
                start=i+1;
            }
        }
        if( i != start ){
            out[j] = text.substring(start);
        }
        return out;
    }
}
