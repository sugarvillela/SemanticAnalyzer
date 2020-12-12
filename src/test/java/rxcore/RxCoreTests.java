package rxcore;

import commons.Commons;
import generated.patterns.PatternDefinition;
import generated.patterns.PatternSourceLang;
import generated.rxwords.RxWord;
import generated.rxwords.RxWords;
import recursivelist.FlagNode;
import recursivelist.IRecursiveNode;
import toktools.Tokenizer;

import java.util.ArrayList;

import static generated.code.PATTERN_TYPE.TARG_LANG;
import static generated.lists.ListScopes.WORD;
import static generated.lists.ListString.IN;
import static generated.patterns.Patterns.*;

public class RxCoreTests {

    private static IRecursiveNode[] listNodesToArray(){
        Tokenizer tok = new Tokenizer();
        String[] testList;
        testList = tok.toArr("I know I have a lovely lovely bunch of coconuts in my shoe");
        IRecursiveNode[] out = new FlagNode[testList.length];
        for(int i = 0; i < testList.length; i++){
            out[i] = new FlagNode();
            out[i].set(IN, testList[i]);
            out[i].set(WORD);
        }
        return out;
    }

    private static RxWord[] rxNodesToArray(){
        Tokenizer tok = new Tokenizer();
        String[] rxList = tok.toArr("I have a lovely bunch of coconuts");

        int[] rangeLo = {1, 1, 1, 1, 1, 1, 1};
        int[] rangeHi = {1, 1, 1, 3, 1, 1, 1};
        RxWord[] out = new RxWord[rxList.length];
        for(int i = 0; i < rxList.length; i++){
            int lo = rangeLo[i];
            int hi = rangeHi[i];
            int remaining = Math.min(hi, rxList.length - i);
            out[i] = new RxWords.RxWord010(lo, remaining, rxList[i]);
        }
        return out;
    }

    public static void testSourceLang(){
        RxEngine rxEngine = new RxEngineSourceLang();

        PatternSourceLang pattern = new PatternSourceLang(rxNodesToArray());

        rxEngine.setNodes(listNodesToArray()).setPattern(pattern).test();
        if(rxEngine.haveResult()){
            rxEngine.disp();
            System.out.println(rxEngine.getResult());
        }
        else{
            System.out.println("no match");
        }
    }

    public static void testTargLang(){
        String[] testList = {
            "abc/def", "ghi-klmn", "012/345", "678-910", "Schnitzel", "250,000"
        };
        IRecursiveNode[] nodes = new FlagNode[testList.length];
        for(int i = 0; i < testList.length; i++){
            nodes[i] = new FlagNode();
            nodes[i].set(IN, testList[i]);
            nodes[i].set(WORD);
        }
        //Commons.disp(nodes, "Nodes");

        PatternDefinition[] patterns = {
                PATTERN_000, PATTERN_001, PATTERN_002, PATTERN_003
        };
        //Commons.disp(patterns, "patterns");

        TARG_LANG.engine.setNodes(nodes);

        for(PatternDefinition pattern : patterns){
            if(TARG_LANG.engine.setPattern(pattern).test()){
                //System.out.println("found: "+test + ", pattern: "+ pattern.getPattern());
                if(TARG_LANG.engine.haveResult()){
                    ArrayList<ResultSet> results = TARG_LANG.engine.getResults();
                    Commons.disp(results, "results");
                }
            }
        }

    }
}
