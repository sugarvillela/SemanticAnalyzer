package runstate;

import recursivelist.FlagNode;
import recursivelist.util.ScopeUtil;
import recursivelist.util.FlagNodeUtil;
import recursivelist.util.GroupDownUtil;
import rxfxcore.RxFxEngine;
import rxword.impl.RxFunRun;

public class RunState {
    private static final GroupDownUtil groupDownUtil;
    private static final FlagNodeUtil flagNodeUtil;
    private static final RxFunRun rxFunRun;
    
    static{
        groupDownUtil = GroupDownUtil.init();
        flagNodeUtil = FlagNodeUtil.init();
        rxFunRun = RxFunRun.init();
    }
    
    private static FlagNode rootNode;
    private static ScopeUtil scopeUtil;
    private static RxFxEngine rxFxEngine;

    public static void initText(String text){
        rootNode = flagNodeUtil.initRootNode(text);
        scopeUtil = ScopeUtil.init(rootNode);
        rxFxEngine = RxFxEngine.init(scopeUtil);
    }

    public static GroupDownUtil getGroupDownUtil(){
        return groupDownUtil;
    }

    public static FlagNodeUtil getFlagNodeUtil(){
        return flagNodeUtil;
    }

    public static FlagNode getRootNode(){
        return rootNode;
    }

    public static ScopeUtil getScopeUtil(){
        return scopeUtil;
    }

    public static RxFxEngine getRxFxEngine() {
        return rxFxEngine;
    }

    public static RxFunRun getRxFunRun() {
        return rxFunRun;
    }
}
