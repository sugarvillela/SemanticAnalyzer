package toktools;

public class Tokenizer {
    private final char delim;
    private final int limit;

    public Tokenizer(){
        this( ' ', 0x7FFFFFFF );
    }
    public Tokenizer( char setDelim ){
        this( setDelim, 0x7FFFFFFF );
    }
    public Tokenizer( char setDelim, int setLimit ){
        delim = setDelim;
        limit = setLimit;
    }

    public String[] toArr(String text){
        // Simple tokenizer with unlimited splits, no empty
        // Rehearse to get size
        int count = 0;
        int start = 0;
        int i, j = 0;
        for( i=0; i<text.length(); i++ ){
            if( text.charAt(i) == delim ){
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
            if( text.charAt(i) == delim ){
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
