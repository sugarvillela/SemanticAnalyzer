package rxword.impl;

import generated.enums.RX_COMPARE;
import runstate.RunState;
import rxword.interfaces.RxFun;
import rxword.interfaces.RxFunPattern;

import java.util.Arrays;

public class RxFunPatternBase implements RxFunPattern {
    private RxFun[] left, right;
    private RX_COMPARE rxCompare;

    private void initList(RxFun[] list){
        int i;
        for(i = 0; i < list.length - 1; i++){
            list[i].setNext(list[i + 1]);
        }
        list[i].setNext(RunState.getRxFunRun());
    }

    protected final void initLeft(RxFun... left) {
        this.initList(left);
        this.left = left;
    }

    protected final void initRight(RxFun... right) {
        this.initList(right);
        this.right = right;
    }

    protected final void initCompare(RX_COMPARE rxCompare) {
        this.rxCompare = rxCompare;
    }

    @Override
    public RxFun[] getLeft() {
        return left;
    }

    @Override
    public RxFun[] getRight() {
        return right;
    }

    @Override
    public RX_COMPARE getCompare() {
        return rxCompare;
    }

    @Override
    public String toString() {
        return "RxFunPatternBase{" +
                "\n    left=" + left[0] +
                "\n    right=" + right[0] +
                "\n    rxCompare=" + rxCompare +
                "\n}";
    }
}
