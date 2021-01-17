package rxfxcore.interfaces;

import generated.enums.ACTION;
import generated.enums.DATATYPE;

/** Interface for target language and source language implementations
 *  Impl FxWordBase does the algorithmic work for fx actions
 *  The generated subclasses just provide the data in order */
public interface FxWord {
    ACTION nextAction();
    DATATYPE nextDatatype();
    int nextEnu();
    int nextNumber();
    String nextString();

    void rewindIterators();

    StringData stringData();
    TargetData targetData();

    void disp();

    interface StringData{// target language implements
        void setTargets(int[] targets);
        int[] getTargets();

        /** pattern groups $1, $2 etc (see target language implementation) */
        void setStrings(String[] runTimeStrings);
        String nextString();

        void rewindIterator();
    }

    interface TargetData{
        void setTargets(int[] targets);
        int[] getTargets();

        void setRuntimeIndices(int[] runtimeIndices);
        int[] getRuntimeIndices();
    }

}
