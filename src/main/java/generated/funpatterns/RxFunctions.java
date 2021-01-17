package generated.funpatterns;

import rxword.impl.RxFunSpecials.*;
import rxword.interfaces.RxFun;

import static generated.lists.ListString.IN;

public class RxFunctions {

    public static final RxFun RX_FUN_000_00_00_00 = new RxFun000_00_00_00();
    public static final RxFun RX_FUN_000_00_00_01 = new RxFun000_00_00_01();
    public static final RxFun RX_FUN_000_00_00_02 = new RxFun000_00_00_02();

    /*=====source language, to be generated===========================================================================*/

    public static class RxFun000_00_00_00 extends StoreGetString {
        protected RxFun000_00_00_00() {
            super(IN);
        }
    }
    public static class RxFun000_00_00_01 extends Len {

    }
    public static class RxFun000_00_00_02 extends Range {
        protected RxFun000_00_00_02() {
            super(1, 6);
        }
    }
}
