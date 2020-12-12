package generated.rxwords;

import recursivelist.IRecursiveNode;

public interface RxWord {// beach clearwater  Dan Lovin Wendy Wise  Lani Craig 2 hrs north
    boolean test(IRecursiveNode flagNode);
    int getLo();
    int getHi();
    Object getPattern();
    Object[] actionsOnMatch();
}
