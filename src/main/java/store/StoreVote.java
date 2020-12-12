package store;

public class StoreVote extends StoreDiscrete{
    public StoreVote(Store.CompositeCalculations calc) {
        super(calc);
    }
    @Override
    public void set(int enu){
        seek(enu);
        int posMask = calc.valMask << (seekCol * calc.wval);
        int prev = store[seekRow];
        System.out.printf("mask: 0x%X \n", posMask);
        System.out.printf("enu : 0x%X \n", enu);
        System.out.printf("prev: 0x%X \n", prev);
        System.out.printf("add : 0x%X \n", (enu + prev));
        System.out.printf("|=  : 0x%X \n", (posMask & (enu + prev)));
        System.out.println();
        store[seekRow] &= ~posMask;         // drop old value
        store[seekRow] |= (posMask & (enu + prev));  // add new
    }
}
