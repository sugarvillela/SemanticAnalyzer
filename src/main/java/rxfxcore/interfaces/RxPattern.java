package rxfxcore.interfaces;

import generated.enums.PATTERN_TYPE;
import recursivelist.FlagNode;

public interface RxPattern {
    PATTERN_TYPE getPatternType();

    FxPattern getFxPattern();

    RxPattern setIndex(int index);

    RxPattern setNodes(FlagNode[] flagNodes);

    boolean go();

    RxResult getResult();

    void disp();
}
