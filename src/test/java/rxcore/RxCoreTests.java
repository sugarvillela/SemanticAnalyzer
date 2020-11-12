package rxcore;

import flagobj.IFlags;
import flagobj.IRx;
import toktools.Tokenizer;

public class RxCoreTests {
    public static class FlagMock implements IFlags {
        public Object payload;

        public FlagMock(Object payload){
            this.payload = payload;
        }

        @Override
        public Object getObject(int enu) {
            return payload;
        }

        @Override
        public boolean test(IFlags testObj) {
            return false;
        }
    }

    public static class RxMock implements IRx {
        private Object payload;
        private int lo, hi;

        public RxMock(int lo, int hi, Object payload){
            this.lo = lo;
            this.hi = hi;
            this.payload = payload;
        }
        @Override
        public boolean test(IFlags testObj) {
            return this.payload.equals(testObj.getObject(1));
        }

        @Override
        public void setLo(int lo) {
            this.lo = lo;
        }

        @Override
        public void setHi(int hi) {
            this.hi = hi;
        }

        @Override
        public int getLo() {
            return lo;
        }

        @Override
        public int getHi() {
            return hi;
        }

        @Override
        public Object getObj() {
            return payload;
        }
    }

    public static IFlags[] listNodesToArray(){
        Tokenizer tok = new Tokenizer();
        String[] testList;
        testList = tok.toArr("I know I have a lovely lovely bunch of coconuts in my shoe");
        FlagMock[] out = new FlagMock[testList.length];
        for(int i = 0; i < testList.length; i++){
            out[i] = new FlagMock(testList[i]);
        }
        return out;
    }

    public static IRx[] rxNodesToArray(){
        Tokenizer tok = new Tokenizer();
        String[] rxList = tok.toArr("I have a lovely bunch of coconuts");
        //
        int[] rangeLo = {1, 1, 1, 1, 1, 1, 1};
        int[] rangeHi = {1, 1, 1, 3, 1, 1, 1};
        RxMock[] out = new RxMock[rxList.length];
        for(int i = 0; i < rxList.length; i++){
            int lo = rangeLo[i];
            int hi = rangeHi[i];
            int remaining = Math.min(hi, rxList.length - i);
            out[i] = new RxMock(lo, hi, rxList[i]);
        }
        return out;
    }
    public static void testPositional(){
        TestRunner testRunner = new TestRunner(listNodesToArray(), rxNodesToArray());
        testRunner.test();
        testRunner.dispTestResults();
        System.out.println(testRunner.getBestResult());
    }
}
