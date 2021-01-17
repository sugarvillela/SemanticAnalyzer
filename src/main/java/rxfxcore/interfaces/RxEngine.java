package rxfxcore.interfaces;

import recursivelist.FlagNode;

/** Two implementations: target language and source language
 *  Target language impl uses Java regex pattern for char traits in one flagNode
 *    Runs with RxEngineT
 *    RxPatternT contains the compiled Java pattern
 *    RxResultT passes compile- and run-time values to FxEngine
 *
 *  Source language impl uses custom regex pattern for word traits across multiple flagNodes
 *    Runs with RxEngineS; runs a pattern at each position of flagNode array to locate best match
 *    RxPatternS tests for match at a given position, optimizes results for longest match
 *    RxWord runs RxFunPattern (a list of test functions) on a given flagNode: funPatternA && funPatternB etc.
 *    RxFunPattern is a comparison engine: funA ?= funB
 *    RxFun is a test function: retrieve value or trait from flagNode; supply a constant value to compare to
 *    RxResultS passes compile- and run-time values to FxEngine
 *    */
public interface RxEngine {
    RxEngine setNodes(FlagNode[] flagNodes);
    RxEngine setRxPattern(RxPattern rxPattern);
    RxEngine setIndex(int index);
    boolean go();
    RxResult getResult();
    void disp();
}
