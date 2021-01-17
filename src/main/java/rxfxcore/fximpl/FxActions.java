package rxfxcore.fximpl;

import generated.enums.ACTION;
import rxfxcore.interfaces.FxAction;
import recursivelist.FlagNode;
import recursivelist.IRecursiveList;
import rxfxcore.interfaces.FxWord;

import static generated.lists.ListString.*;

public abstract class FxActions {
    public static class FxSet implements FxAction {
        @Override
        public void go(FxWord fxWord, FlagNode[] flagNodes, int index) {
            System.out.println("FxSet: ");
            switch(fxWord.nextDatatype()){
                case LIST_VOTE:
                case LIST_BOOLEAN:
                case LIST_DISCRETE:
                    flagNodes[index].set(fxWord.nextEnu());
                    break;
                case LIST_NUMBER:
                    flagNodes[index].set(fxWord.nextEnu(), fxWord.nextNumber());
                    break;
                case LIST_STRING:
                    flagNodes[index].set(fxWord.nextEnu(), fxWord.nextString());
                    break;
                default:
                    throw new IllegalStateException("");
            }
        }
    }
    public static class FxSetRuntimeData implements FxAction {
        @Override
        public void go(FxWord fxWord, FlagNode[] flagNodes, int index) {
            System.out.println("FxSetRuntimeData: ");
            switch(fxWord.nextDatatype()){
                case LIST_STRING:
                    flagNodes[index].set(fxWord.nextEnu(), fxWord.stringData().nextString());
                    break;
                default:
                    throw new IllegalStateException("");
            }
        }
    }

    public static class FxDrop implements FxAction {
        @Override
        public void go(FxWord fxWord, FlagNode[] flagNodes, int index) {
            flagNodes[index].drop(fxWord.nextEnu());
        }
    }

    public static class FxRem implements FxAction {
        @Override
        public void go(FxWord fxWord, FlagNode[] flagNodes, int index) {
            IRecursiveList parentList = flagNodes[index].getParentList();
            parentList.popIn(index);
        }
    }

    public static class FxSplit implements FxAction {
        @Override
        public void go(FxWord fxWord, FlagNode[] flagNodes, int index) {
            flagNodes[index].set(IN, fxWord.stringData().nextString());
            flagNodes[index].set(EXPAND1, fxWord.stringData().nextString());
            flagNodes[index].mark(ACTION.SPLIT);
        }

        public boolean destructiveEdit(FlagNode[] flagNodes, int index) {
            IRecursiveList parentList = flagNodes[index].getParentList();
            FlagNode newNode = new FlagNode();
            newNode.set(IN, flagNodes[index].getString(EXPAND1));
            parentList.pushIn(index + 1, newNode);
            flagNodes[index].drop(EXPAND1);
            return true;
        }
    }
}
