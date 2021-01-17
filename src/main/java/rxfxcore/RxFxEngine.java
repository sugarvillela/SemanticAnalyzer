package rxfxcore;

import commons.Commons;
import rxfxcore.interfaces.*;
import recursivelist.FlagNode;
import recursivelist.util.ScopeUtil;
import runstate.RunState;
import rxfxcore.util.ResultScope;
import rxfxcore.util.ResultScopeS;

import java.util.ArrayList;

import static generated.lists.ListScopes.ALL;
/*
    engine both     go scope rxPattern
    engine 2 impl   go nodes rxPattern index
                    get result (type)
    rxPattern       go nodes (index)
                    get result type fxPattern

    fxEngine        go fxPattern nodes result
    fxPattern       go nodes result
                    get patternType dataType enu number string group
    fxSingle        go fxPattern index nodes

*
*
* */

public class RxFxEngine {
    private static RxFxEngine instance;

    public static RxFxEngine init(ScopeUtil scopeUtil){
        return (instance == null)? (instance = new RxFxEngine(scopeUtil)) : instance;
    }
    public static RxFxEngine getInstance(){
        return instance;
    }

    private final ScopeUtil scopeUtil;


    private RxFxEngine(ScopeUtil scopeUtil) {
        this.scopeUtil = scopeUtil;

    }

    public void causeEffect(int scope, RxPattern rxPattern){
        FlagNode[][] cases = scopeUtil.getContainers(scope);

        //RunState.getFlagNodeUtil().dispFlagNodes(cases);
        rxPattern.disp();

        RxEngine rxEngine = rxPattern.getPatternType().rxEngine;
        FxEngine fxEngine = rxPattern.getPatternType().fxEngine;

        rxEngine.setRxPattern(rxPattern);
        fxEngine.setFxPattern(rxPattern.getFxPattern());

        for(int i = cases.length - 1; i >= 0; i--){
//            for(int j = 0; j < cases[i].length; j++){
//                System.out.printf("%d:%d: %s \n",  i, j, cases[i][j].toString());
//            }

            if(rxEngine.setIndex(i).setNodes(cases[i]).go()){
                System.out.println("rxfxEngine found");
                RxResult result = rxEngine.getResult();
                result.disp();
                fxEngine.setNodes(cases[i]).setResult(rxEngine.getResult()).go();
            }
        }
    }

    public FlagNode[][] rxAsScope(RxPattern rxPattern){
        RxEngine rxEngine = rxPattern.getPatternType().rxEngine;
        ResultScope resultScope = rxPattern.getPatternType().resultScope;

        FlagNode[][] cases = scopeUtil.getContainers(ALL);
        ArrayList<FlagNode[]> scopes = new ArrayList<>();
        for(int i = cases.length - 1; i >= 0; i--){
            if(rxEngine.setIndex(i).setRxPattern(rxPattern).setNodes(cases[i]).go()){
                System.out.println("rxfxEngine found");
                RxResult result = rxEngine.getResult();
                result.disp();
                resultScope.addAll(scopes, result, cases[i]);
            }
        }
        int i = 0;
        for(FlagNode[] curr: scopes){
            Commons.disp(curr, "scopes "+i);
            i++;
        }
        return scopes.toArray(new FlagNode[scopes.size()][]);
    }
}
