package rxfxcore.fximpl;

import datasource.IntSource;

import java.util.Arrays;

public class FxWordS extends FxWordBase{
    private final TargetData targetDataImpl;

    public FxWordS() {
        targetDataImpl = new TargetDataImpl();
    }

    @Override
    public StringData stringData() {
        return null;
    }
    @Override
    public TargetData targetData() {
        return targetDataImpl;
    }

    @Override
    protected final void initRuntimeTargets(int... targets){
        this.targetDataImpl.setTargets(targets);
    }

    public static class TargetDataImpl implements TargetData{
        private int[] targets, runtimeIndices;

        @Override
        public void setTargets(int[] targets) {
            this.targets = targets;
        }

        @Override
        public int[] getTargets() {
            return this.targets;
        }

        @Override
        public void setRuntimeIndices(int[] runtimeIndices) {
            this.runtimeIndices = runtimeIndices;
        }

        @Override
        public int[] getRuntimeIndices() {
            return runtimeIndices;
        }

        @Override
        public String toString() {
            return "  TargetDataImpl{" +
                    "\n    targets=" + Arrays.toString(targets) +
                    "\n    runtimeIndices=" + Arrays.toString(runtimeIndices) +
                    "\n  }";
        }
    }

    @Override
    public String toString() {
        return "FxWordS{" +
                "\n" + targetDataImpl +
                '}';
    }
}
