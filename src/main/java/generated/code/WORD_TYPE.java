package generated.code;

import generated.bloom.BloomData;
import search.BloomStore;

public enum WORD_TYPE {
    CONJUNCTION     (null),
    CONTRACTION     (BloomData.contraction),
    DETERMINER      (null),
    IRREG           (null),
    LINKING         (null),
    MODAL           (null),
    NAME            (BloomData.name),
    NUM_WORD        (null),
    PREFIX          (BloomData.prefix),
    PREP_ADV        (null),
    PRONOUN         (null),
    SUFFIX          (BloomData.suffix),
    SURNAME         (BloomData.surname),
    WORD            (BloomData.word)
    ;

    private final BloomStore bloom;
    
    private WORD_TYPE(long[] bloomData){
        this.bloom = (bloomData == null)? null : new BloomStore(bloomData);
    }
    public boolean searchBloom(String text){
        return this.bloom != null && this.bloom.get(text);
    }
    public boolean searchBloom(int[] hashes){
        return this.bloom != null && this.bloom.get(hashes);
    }
    public boolean inMainTable(int[] hashes){
        return this.bloom != null;
    }
}
