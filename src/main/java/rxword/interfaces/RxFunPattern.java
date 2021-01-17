package rxword.interfaces;

import generated.enums.RX_COMPARE;

public interface RxFunPattern {
    RxFun[] getLeft();
    RxFun[] getRight();
    RX_COMPARE getCompare();
}
