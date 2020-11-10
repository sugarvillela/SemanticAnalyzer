package rxcore;

import toktools.Tokenizer;

public class TestRunner {
    private final TestNodeMock[] testNodes;
    private final RxNodeMock[] rxNodes;
    private final PositionalTest positionalTest;

    public TestRunner(){
        testNodes = listNodesToArray();
        rxNodes = rxNodesToArray();
        positionalTest = new PositionalTest(testNodes, rxNodes);
        convertHiToRemaining();
    }
    private void convertHiToRemaining(){
        System.out.printf("convertHiToRemaining:  rxNodes.length = %d \n", rxNodes.length);
        for(int i = 0; i < rxNodes.length; i++){
            System.out.printf("Before %d, ", rxNodes[i].hi);
            rxNodes[i].hi = Math.min(rxNodes[i].hi, rxNodes.length - i);
            System.out.printf("After %d \n", rxNodes[i].hi);
        }
    }
    public void test(){
        for(int i = 0; i < testNodes.length; i++){
            positionalTest.reset(i);
            positionalTest.test();
            if(positionalTest.haveWinner()){
                positionalTest.dispWinners();
                break;
            }
        }
    }

    public static class TestNodeMock {
        public Object payload;

        public TestNodeMock(Object payload){
            this.payload = payload;
        }
        public Object get(int integer){
            return payload;
        }
    }

    public static class RxNodeMock {
        public Object payload;
        public int lo, hi;

        public RxNodeMock(int lo, int hi, Object payload){
            this.lo = lo;
            this.hi = hi;
            this.payload = payload;
        }
        public boolean test(TestNodeMock testNode){
            return this.payload.equals(testNode.get(1));
        }
    }

    public TestNodeMock[] listNodesToArray(){
        Tokenizer tok = new Tokenizer();
        String[] testList;
        testList = tok.toArr("I know I have a lovely lovely bunch of coconuts in my shoe");
        TestNodeMock[] out = new TestNodeMock[testList.length];
        for(int i = 0; i < testList.length; i++){
            out[i] = new TestNodeMock(testList[i]);
        }
        return out;
    }
    public RxNodeMock[] rxNodesToArray(){
        Tokenizer tok = new Tokenizer();
        String[] rxList = tok.toArr("I have a lovely bunch of coconuts");
        //
        int[] rangeLo = {1, 1, 1, 1, 1, 1, 1};
        int[] rangeHi = {1, 1, 1, 3, 1, 1, 1};
        RxNodeMock[] out = new RxNodeMock[rxList.length];
        for(int i = 0; i < rxList.length; i++){
            int lo = rangeLo[i];
            int hi = rangeHi[i];
            int remaining = Math.min(hi, rxList.length - i);
            out[i] = new RxNodeMock(lo, hi, rxList[i]);
        }
        return out;
    }
}
