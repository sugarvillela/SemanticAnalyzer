package rxword.interfaces;

import recursivelist.FlagNode;

public interface RxWord {
    int getLo();
    int getHi();

    RxWord setNode(FlagNode flagNode);
    boolean go();
}
