package generated.code;

import rxcore.RxEngine;
import rxcore.RxEngineTargLang;

public enum PATTERN_TYPE {
    TARG_LANG   (new RxEngineTargLang()),
    SOURCE_LANG (null);

    public final RxEngine engine;

    PATTERN_TYPE(RxEngine engine) {
        this.engine = engine;
    }
}
