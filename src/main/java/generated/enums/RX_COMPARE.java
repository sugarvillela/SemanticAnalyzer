package generated.enums;

import rxword.interfaces.RxCompare;
import rxword.impl.RxCompareImpl.*;

public enum RX_COMPARE {
    COMPARE_EQUAL   (new RxCompareEqual()),
    COMPARE_GT      (new RxCompareGT()),
    COMPARE_LT      (new RxCompareLT());

    public final RxCompare op;

    RX_COMPARE(RxCompare op) {
        this.op = op;
    }
}
