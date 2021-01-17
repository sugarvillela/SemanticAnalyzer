package search;

import commons.Commons;
import datasource.TextSource;
import datasource.TextSourceFile;
import erlog.Erlog;
import generated.enums.WORD_TYPE;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DbSearchUtil {
    private final DbSearch dbSearch;
    private String[] currCol, currVal;
    private String currTable, currText;


    public DbSearchUtil() {
        dbSearch = DbSearch.getInstance();
    }

    public boolean insertUnique(String table, String text, String... flagNames){
        currTable = table;
        currText = text;
        if(dbSearch.exists(table, text)){
            return false;
        }
        else{
            //System.out.println("insert '" + text + "' returned " + result);  UPDATE `wordlists`.`word` SET `irreg` = '1' WHERE `text` = 'write';
            setInsertValues(flagNames);
            String q = insertSetFlag();
            System.out.println(q);
            return dbSearch.query(q);
        }
    }

    public boolean insertOrUpdate(String table, String text, String... flagNames){
        currTable = table;
        currText = text;

        String q;// = String.format("SELECT `text` FROM `wordlists`.`%s` WHERE `text` = '%s' ;", currTable, currText);
        //System.out.println(q);

        if(dbSearch.exists(table, text)){
            //System.out.println("update '" + text + "' returned " + result);
            setUpdateValues(flagNames);
            q = updateSetFlag();
        }
        else{
            //System.out.println("insert '" + text + "' returned " + result);  UPDATE `wordlists`.`word` SET `irreg` = '1' WHERE `text` = 'write';
            setInsertValues(flagNames);
            q = insertSetFlag();
        }
        System.out.println(q);
        return dbSearch.query(q);
    }

    public boolean insertUniqueAll(String table, String text, String... flagNames){
        for(WORD_TYPE wordType : WORD_TYPE.values()){
            String wordTypeTable = wordType.toString().toLowerCase();
            if(!wordTypeTable.equals(table) && dbSearch.exists(wordTypeTable, text)){//
                System.out.println(text + " exists in " + wordTypeTable);
                return false;
            }
        }
        if(!insertUnique(table, text, flagNames)){
            System.out.println(text + " exists in " + table);
            return false;
        }
        return true;
    }

    private void setInsertValues(String[] updateCol){//combine text and flags
        int len = updateCol.length + 1;
        currCol = new String[len];
        currVal = new String[len];
        currCol[0] = "text";
        currVal[0] = currText;
        for(int i = 1; i < len; i++){
            currCol[i] = updateCol[i - 1];
            currVal[i] = "1";
        }
    }

    private void setUpdateValues(String[] updateCol){
        int len = updateCol.length;
        currCol = updateCol;
        currVal = new String[len];
        for(int i = 0; i < len; i++){
            currVal[i] = "1";
        }
    }

    private String insertSetFlag(){
        return new QueryGen.QueryGenBuilder().setOp(QueryGen.OP.INSERT).setTable(currTable).
                setCols(currCol).setValues(currVal).
                build().finish();
    }

    private String updateSetFlag(){
        return new QueryGen.QueryGenBuilder().setOp(QueryGen.OP.UPDATE).setTable(currTable).
                setCols(currCol).setValues(currVal).
                setWhereScopes("text").setWhereStates(currText).
                build().finish();
    }

    public void toTextFile(String table){// useful: for getting text-only list from db
        String q = String.format("SELECT `text` FROM `wordlists`.`%s` ORDER BY `text` ASC;", table);
        if(dbSearch.query(q)){
            String[] list = dbSearch.getCol();
            int count = 0;
            try(
                    BufferedWriter file = new BufferedWriter(new FileWriter(table + "_list.txt"))
            ){

                for (String line: list) {
                    file.write(line);
                    file.newLine();
                    count++;
                }
            }
            catch(IOException e){
                System.out.println(table + ": exception = " + e);
                System.exit(-1);
            }
            System.out.printf("%s: Wrote %d of %d \n", table, count, list.length);
        }
    }

    public boolean insertUnique(String data){
        readTokenString(data);
        return insertUnique(currTable, currText, currCol);
    }

    public boolean insertOrUpdate(String data){
        readTokenString(data);
        return insertOrUpdate(currTable, currText, currCol);
    }

    public boolean insertUniqueAll(String data){
        if(data.contains(".")){
            System.out.println(data + " is a file name");
            return insertFlagFile(data);
        }
        readTokenString(data);
        return insertUniqueAll(currTable, currText, currCol);
    }

    public boolean insertFlagFile(String fileName){
        int total = 0, written = 0;
        String table = null;

        TextSource textSource = new TextSourceFile(fileName);
        textSource.rewind();
        if(textSource.hasNext()){
            table = textSource.next() + " ";
        }
        while(textSource.hasNext()){
            String next = textSource.next();
            if(next.contains(".")){//prevent recursive call from bad file
                Erlog.kill("Dot in input string", fileName, next);
            }
            else if(insertUniqueAll(table + next)){
                written++;
            }
            total++;
        }
        System.out.printf("%s: Wrote %d of %d \n", fileName, written, total);
        return written > 0;
    }

    private void readTokenString(String data){
        try{
            String[] tok = Commons.deleteEmpties(data.split(" "));
            currTable = tok[0];
            currText = tok[1];

            int len = tok.length - 2;
            currCol = new String[len];
            for(int i = 0; i < len; i++){
                currCol[i] = tok[i + 2];
            }
        }catch(ArrayIndexOutOfBoundsException ex){
            Erlog.kill("Out of bounds here: \n" + ex, data);
        }
    }
}
