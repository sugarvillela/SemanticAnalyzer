package generated.enums;

import rxfxcore.interfaces.FxAction;
import rxfxcore.fximpl.FxActions;

/*
    // Flags
    VOTE    (0, new FX_DATATYPE[]{FX_L}),
    SET     (0, new FX_DATATYPE[]{FX_L}),
    DROP    (0, new FX_DATATYPE[]{FX_L}),
    // Structure
    CON     (0, new FX_DATATYPE[]{FX_E, FX_N, FX_R, FX_C}),
    COPY    (0, new FX_DATATYPE[]{FX_N, FX_R, FX_C}),
    SWAP    (0, new FX_DATATYPE[]{FX_N, FX_R, FX_C}),
    REM     (0, new FX_DATATYPE[]{FX_E}),
    MARK    (0, new FX_DATATYPE[]{FX_E}),
    MREM    (1, new FX_DATATYPE[]{FX_E}),                   // Remove if marked
    RUN     (1, new FX_DATATYPE[]{FX_A})
* */
public enum ACTION {
    // Flags
    SET         (new FxActions.FxSet()),
    SET_RUNTIME (new FxActions.FxSetRuntimeData()),
    DROP        (new FxActions.FxDrop()),

    // Structure
    SPLIT       (new FxActions.FxSplit()),
    CON         (null),
    COPY        (null),
    SWAP        (null),
    REM         (new FxActions.FxRem()),
    MARK        (null),
    MREM        (null),
    RUN         (null);

    public final FxAction component;

    ACTION(FxAction component) {
        this.component = component;
    }
}
