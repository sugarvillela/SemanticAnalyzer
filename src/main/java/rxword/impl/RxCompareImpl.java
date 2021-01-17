package rxword.impl;

import rxword.interfaces.RxCompare;

public abstract class RxCompareImpl {
    public static class RxCompareEqual implements RxCompare {
        int intData;
        Object objectData;
        protected boolean result;

        @Override
        public void setLeft(int n) {
            intData = n;
        }

        @Override
        public void setLeft(Object o) {
            System.out.printf("setLeft: o=%s \n", o);
            objectData = o;
        }


        @Override
        public void setRight(int n) {
            result = (intData == n);
        }

        @Override
        public void setRight(Object o) {
            System.out.printf("setRight: o=%s \n", o);
            result = o.equals(objectData);
        }

        @Override
        public void requestResult(RxFunRun rxFunRun) {
            rxFunRun.set(result);
            rxFunRun.tick();
        }
    }

    public static class RxCompareLT extends RxCompareEqual{
        @Override
        public void setRight(int n) {
            result = (intData < n);
        }
    }

    public static class RxCompareGT extends RxCompareEqual{
        @Override
        public void setRight(int n) {
            result = (intData > n);
        }
    }
}
