package rxword.impl;

import recursivelist.FlagNode;
import rxword.interfaces.RxCompare;
import rxword.interfaces.RxFun;
import rxword.interfaces.RxFunPattern;

public class RxFunRun implements RxFun {
    private static RxFunRun instance;

    public static RxFunRun init(){
        return (instance == null)? (instance = new RxFunRun()) : instance;
    }
    public static RxFunRun getInstance(){
        return instance;
    }

    private RxFunRun(){}

    private final int RUN_LEFT = 0, RUN_RIGHT = 1, GET_RESULT = 2;
    private int state;
    private FlagNode flagNode;
    RxCompare rxCompare;
    private boolean result;

    public void setNode(FlagNode flagNode){
        this.flagNode = flagNode;
    }
    public boolean run(RxFunPattern funPattern){
        System.out.println("funPattern");
        System.out.println(funPattern);
        state = RUN_LEFT;

        RxFun[] left = funPattern.getLeft();
        RxFun[] right = funPattern.getRight();
        rxCompare = funPattern.getCompare().op;

        // state = RUN_LEFT;
        // run left list, sets rxCompare left, calls this.tick() to change state
        left[0].set(flagNode).tick();

        // state = RUN_RIGHT;
        // run right list, sets rxCompare right, calls this.tick() to change state
        right[0].set(flagNode).tick();

        // state = GET_RESULT;
        // rxCompare.tick() sets this.result via this.set(boolean), calls this.tick() to change state
        rxCompare.requestResult(this);

        return result;
    }

    @Override
    public RxFun set(boolean b) {
        switch (state){
            case RUN_LEFT:
                rxCompare.setLeft(b? 1 : 0);
                break;
            case RUN_RIGHT:
                rxCompare.setRight(b? 1 : 0);
                break;
            default:
                result = b;
        }
        return this;
    }

    @Override
    public RxFun set(int n) {
        System.out.printf("set: state=%d, n=%d \n", state, n);
        switch (state){
            case RUN_LEFT:
                rxCompare.setLeft(n);
                break;
            case RUN_RIGHT:
                rxCompare.setRight(n);
                break;
            default:
                throw new IllegalStateException("Dev error");
        }
        return this;
    }

    @Override
    public RxFun set(Object o) {
        System.out.printf("RxFunRun set: state=%d, o=%s \n", state, o);
        switch (state){
            case RUN_LEFT:
                rxCompare.setLeft(o);
                break;
            case RUN_RIGHT:
                rxCompare.setRight(o);
                break;
            default:
                throw new IllegalStateException("Dev error");
        }
        return this;
    }

    @Override
    public RxFun set(String s) {
        switch (state){
            case RUN_LEFT:
                rxCompare.setLeft(s);
                break;
            case RUN_RIGHT:
                rxCompare.setRight(s);
                break;
            default:
                throw new IllegalStateException("Dev error");
        }
        return this;
    }

    @Override
    public void tick() {
        switch (state){
            case RUN_LEFT:
                state = RUN_RIGHT;
                break;
            case RUN_RIGHT:
                state = GET_RESULT;
                break;
            default:
                state = RUN_LEFT;
        }
    }

    @Override
    public void setNext(RxFun next) {}
}
