package generated.rxwords;

import recursivelist.IRecursiveNode;

import static generated.lists.ListString.IN;

public class RxWords {
    public static abstract class RxWordBase implements RxWord {
        private final Object payload;
        private final int lo, hi;

        public RxWordBase(int lo, int hi, Object payload) {
            this.lo = lo;
            this.hi = hi;
            this.payload = payload;

            System.out.println("RxWordBase payload: " + payload);
        }

        @Override
        public boolean test(IRecursiveNode flagNode) {
            return this.payload.equals(flagNode.getString(IN));
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
        public Object getPattern() {
            return payload;
        }

        @Override
        public Object[] actionsOnMatch() {
            return new Object[0];
        }
    }
    public static class RxWord010 extends RxWordBase {
        public RxWord010(int lo, int hi, Object payload) {
            super(lo, hi, payload);
        }
    }
}
