package rxfxcore.fximpl;

import datasource.StringSource;

public class FxWordT extends FxWordBase{
    protected final StringData stringDataImpl;


    protected FxWordT() {
        stringDataImpl = new StringDataT();
    }

    @Override
    public void rewindIterators() {
        super.rewindIterators();
        stringDataImpl.rewindIterator();
    }

    @Override
    public StringData stringData() {
        return stringDataImpl;
    }
    @Override
    public TargetData targetData() {
        return null;
    }

    @Override
    protected final void initRuntimeTargets(int... targets){
        this.stringDataImpl.setTargets(targets);
    }

    public static class StringDataT implements StringData{
        private StringSource runtimeStringSource;
        private int[] targets;

        @Override
        public void setTargets(int[] targets) {
            this.targets = targets;
        }

        @Override
        public final int[] getTargets(){
            return targets;
        }

        @Override
        public void setStrings(String[] runTimeStrings) {
            this.runtimeStringSource = new StringSource(runTimeStrings);
        }

        @Override
        public String nextString() {
            return runtimeStringSource.next();
        }

        @Override
        public void rewindIterator() {
            runtimeStringSource.rewind();
        }
    }
}
