package rxword.interfaces;

/** RxFunRun unpacks the pattern and calls set() on first item of left and right function lists
 *
 *  rxFun in list:
 *    set() methods map data to intData or objData
 *    tick() method advances to the next step, passing data to the next RxFun in the linked list.
 *    The last item in list is RxFunRun, so passing data to it signals that the current list is finished
 *
 *  RxFunRun (also implements RxFun):
 *    has more complex behavior, using a state machine.
 *    run(rxFunPattern) unpacks the pattern and runs the test
 *    set() passes data to compare object setLeft or setRight, depending on state
 *    tick() method changes state
 *    when left and right lists have run, rxFunRun requests the result from compare object
 *
 *  RxCompare
 *    setLeft sets data
 *    setRight compares data to set value and sets result
 *    requestResult uses visitor pattern to call set() on rxFunList
 *  */
public interface RxFun {
    void setNext(RxFun next);

    RxFun set(boolean b);
    RxFun set(int n);
    RxFun set(Object o);
    RxFun set(String s);
    void tick();
}
