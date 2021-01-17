package generated.enums;

import rxfxcore.fximpl.FxEngineS;
import rxfxcore.interfaces.FxEngine;
import rxfxcore.interfaces.RxEngine;
import rxfxcore.fximpl.FxEngineT;
import rxfxcore.rximpl_s.RxEngineS;
import rxfxcore.rximpl_t.RxEngineT;
import rxfxcore.util.ResultScope;
import rxfxcore.util.ResultScopeS;
import rxfxcore.util.ResultScopeT;

public enum PATTERN_TYPE {
    TARG_LANG   (new RxEngineT(), new FxEngineT(), new ResultScopeT()),
    SOURCE_LANG (new RxEngineS(), new FxEngineS(), new ResultScopeS())
    ;

    public final RxEngine rxEngine;
    public final FxEngine fxEngine;
    public final ResultScope resultScope;

    PATTERN_TYPE(RxEngine rxEngine, FxEngine fxEngine, ResultScope resultScope) {
        this.rxEngine = rxEngine;
        this.fxEngine = fxEngine;
        this.resultScope = resultScope;
    }
}
