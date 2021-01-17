package rxword.interfaces;

import rxword.impl.RxFunRun;

public interface RxCompare {
    void setLeft(int n);
    void setLeft(Object o);

    void setRight(int n);
    void setRight(Object o);

    void requestResult(RxFunRun rxFunRun);
}
