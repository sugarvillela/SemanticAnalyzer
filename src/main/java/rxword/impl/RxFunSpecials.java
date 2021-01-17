package rxword.impl;

import recursivelist.FlagNode;
import rxword.interfaces.RxFun;

/** Linked list nodes of strategy objects: consider head on left and tail on right
 *  Head is first node in list; tail is the list itself: last node in list calls go() on list to complete
 *  Runtime parameters set from left with 'set' methods
 *  Source language parameters are known at compile time; pass them through constructor if impl requires */
public abstract class RxFunSpecials {
    public static class StoreGetString extends RxFunBase{
        private final int enu;

        public StoreGetString(int enu) {
            this.enu = enu;
        }

        /** Require objectData = FlagNode */
        @Override
        public void tick() {
            next.set(((FlagNode)objectData).getString(enu)).tick();
        }
    }
    public static class StoreGetNumber extends RxFunBase{
        private final int enu;

        public StoreGetNumber(int enu) {
            this.enu = enu;
        }

        /** Require objectData = FlagNode */
        @Override
        public void tick() {
            next.set(((FlagNode)objectData).getNumber(enu)).tick();
        }
    }
    public static class StoreGetBoolean extends RxFunBase{
        private final int enu;

        public StoreGetBoolean(int enu) {
            this.enu = enu;
        }

        /** Require objectData = FlagNode */
        @Override
        public void tick() {
            next.set(((FlagNode)objectData).getBoolean(enu)).tick();
        }
    }
    public static class StoreGetState extends RxFunBase{
        private final int enu;

        public StoreGetState(int enu) {
            this.enu = enu;
        }

        /** Require objectData = FlagNode */
        @Override
        public void tick() {
            next.set(((FlagNode)objectData).getState(enu)).tick();
        }
    }

    /** Immutable class to represent a constant test value (int, char, boolean) */
    public static class ValContainerInt extends RxFunBase{
        public ValContainerInt(int state) {
            this.intData = state;
        }

        @Override
        public RxFun set(boolean b) {
            return this;
        }

        @Override
        public RxFun set(int n) {
            return this;
        }

        @Override
        public void tick() {
            next.set(intData).tick();
        }
    }

    /** Immutable class to represent a constant test value (String) */
    public static class valContainerObject extends RxFunBase{

        public valContainerObject(Object objectData) {
            this.objectData = objectData;
        }

        @Override
        public RxFun set(Object o) {
            return this;
        }

        @Override
        public RxFun set(String s) {
            return this;
        }

        @Override
        public void tick() {
            next.set(objectData).tick();
        }
    }

    public static class Len extends RxFunBase {
        /** Require objectData = String */
        @Override
        public void tick() {
            next.set(((String)objectData).length()).tick();
        }
    }

    public static class StartsWith extends RxFunBase {
        private final String param;

        public StartsWith(String param) {
            this.param = param;
        }

        /** Require objectData = String */
        @Override
        public void tick() {
            String data = (String)objectData;
            next.set(data.startsWith(param)).tick();
        }
    }

    public static class EndsWith extends RxFunBase {
        private final String param;

        public EndsWith(String param) {
            this.param = param;
        }

        /** Require objectData = String */
        @Override
        public void tick() {
            String data = (String)objectData;
            next.set(data.endsWith(param)).tick();
        }
    }

    public static class Substring extends RxFunBase {
        private final int[] param;

        public Substring(int... param) {
            this.param = param;
        }

        /** Index-safe impl
         * Require objectData = String */
        @Override
        public void tick() {
            String data = (String)objectData;

            if(param.length == 1){
                int start = Math.min(param[0], data.length());
                next.set(data.substring(start));
            }
            else{
                int end = Math.min(param[1], data.length());
                if(param[0] >= end){
                    next.set("");
                }
                else{
                    next.set(data.substring(param[0], end));
                }
            }
            next.tick();
        }
    }

    public static class Range extends RxFunBase {
        private final int[] param;

        public Range(int... param) {
            this.param = param;
        }

        /** Require intData is set, non-empty param; param.length = 1 okay*/
        @Override
        public void tick() {
            next.set(param[0] <= intData && intData <= param[param.length - 1]).tick();
        }
    }


    // TODO add search and stem here or in a similar impl
}
