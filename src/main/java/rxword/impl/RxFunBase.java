package rxword.impl;

import rxword.interfaces.RxFun;

public abstract class RxFunBase implements RxFun {
    protected RxFun next;
    int intData;
    Object objectData;

    /*=====Linked list methods====================================================================================*/

    @Override
    public void setNext(RxFun next) {
        this.next = next;
    }

    /*=====Setters (implemented)==================================================================================*/

    @Override
    public RxFun set(boolean b) {
        intData = (b) ? 1 : 0;
        return this;
    }

    @Override
    public RxFun set(int n) {
        intData = n;
        return this;
    }

    @Override
    public RxFun set(Object o) {
        objectData = o;
        return this;
    }

    @Override
    public RxFun set(String s) {
        objectData = s;
        return this;
    }

    @Override
    public String toString() {
        String nextStr;
        if(next == null){nextStr = "null";}
        else if(next instanceof RxFunRun){nextStr = "RxFunRun";}
        else {nextStr = next.toString();}

        return "RxFunBase{" +
                "intData=" + intData +
                ", objectData=" + objectData +
                ", next=" + nextStr +
                '}';
    }
}
