package rxfxcore.interfaces;

import generated.enums.ACTION;
import generated.enums.DATATYPE;


public interface FxPattern {
    FxPattern setResult(RxResult result);
    FxWord[] getFxWords();
    void disp();

}
