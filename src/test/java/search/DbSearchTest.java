package search;

import commons.Commons;
import datasource.TextSource;
import datasource.TextSourceFile;
import generated.enums.WORD_TYPE;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static generated.enums.WORD_TYPE.*;

public class DbSearchTest {
    public static void disp2d(String[][] arr){// util for debug
        for(String[] row : arr){
            for(String item : row){
                System.out.printf("(%s), ", item);
            }
            System.out.println();
        }
    }


    public static void getColNames(){
        DbSearch dbSearch = DbSearch.getInstance();
        for(WORD_TYPE wordType : WORD_TYPE.values()){

            Commons.disp(dbSearch.getColNames(wordType.toString().toLowerCase()));
        }
    }

    public static void testColFetch(){
        DbSearch dbSearch = DbSearch.getInstance();
        String q = String.format("SELECT text FROM %s", "conjunction");
        dbSearch.query(q);
        System.out.println("hasResults = " + dbSearch.hasResult());

        Commons.disp(dbSearch.getCol(), "getCol");
    }

    public static void testRowFetch(){
        DbSearch dbSearch = DbSearch.getInstance();
        String q = String.format("SELECT * FROM %s LIMIT 1", "conjunction");
        dbSearch.query(q);
        System.out.println("hasResults = " + dbSearch.hasResult());

        //Commons.disp(dbSearch.getRow(), "getRow");

        for(boolean bool : dbSearch.getRowBoolean(3)){
            System.out.println("---" + bool);
        }
    }



    public static void testFetchAll(){
        DbSearch dbSearch = DbSearch.getInstance();
        String q = String.format("SELECT * FROM %s WHERE `text` = 'bade' ", "irreg");
        dbSearch.query(q);
        System.out.println("hasResults = " + dbSearch.hasResult());

        disp2d(dbSearch.getAll());
    }

    public static void testQueryGen(){
        String q = new QueryGen.QueryGenBuilder().setTable("linking").setCols("id_linking", "text").
                setWhereScopes("text").setWhereStates("are", "is", "am").
                setOrderBy("text").setDescending().build().finish();
        System.out.println(q);

        q = new QueryGen.QueryGenBuilder().setTable("linking").setLimit(5).
                setWhereScopes("id_linking", "text").setWhereStates("22", "is").setWhereAnd().
                build().finish();
        System.out.println(q);

        q = new QueryGen.QueryGenBuilder().setOp(QueryGen.OP.DELETE).setTable("conjunction").
                setWhereScopes("id_linking", "text").setWhereStates("22", "is").
                build().finish();
        System.out.println(q);

        q = new QueryGen.QueryGenBuilder().setOp(QueryGen.OP.INSERT).setTable("linking").
                setCols("text", "singular", "person1").setValues("am", "1", "1").
                build().finish();
        System.out.println(q);

        q = new QueryGen.QueryGenBuilder().setOp(QueryGen.OP.UPDATE).setTable("linking").
                setCols("expand1", "expand2").setValues("could", "not").
                setWhereScopes("text").setWhereStates("couldnt").
                build().finish();
        System.out.println(q);
    }

    public static void combineTables(){// done: push file text and flag to another database table
        DbSearchUtil util = new DbSearchUtil();
        TextSource textSource = new TextSourceFile("irreg.txt");
        textSource.rewind();
        while(textSource.hasNext()){
            String next = textSource.next();
            //System.out.println(next);
            util.insertOrUpdate("word", next, "irreg");
        }
    }

    public static void toTextFile(){// useful: for getting text-only list from db
        DbSearchUtil util = new DbSearchUtil();
        for(WORD_TYPE wordType : WORD_TYPE.values()){
//            if(wordType == WORD){
//                break;
//            }
            util.toTextFile(wordType.toString().toLowerCase());
        }
    }

    public static void toTextFileByFlag(){// useful? used to extract flagged to separate files, duplicates okay
        String[] flags = new String[]{
                "adj", "adv", "conjunction", "determiner", "modal",
                "name", "noun", "num_word", "prep", "prep_adv", "verb"
        };
        for(String flag : flags){
            toTextFileByFlag("word", flag);
        }
    }

    public static void toTextFileByFlag(String table, String flag){// done: used to extract flagged to separate files, duplicates okay
        String fileName = "word_" + flag + ".txt";
        //String fileName = "deletedNames.CSV";
        DbSearch dbSearch = DbSearch.getInstance();
        String q = String.format("SELECT `text` FROM `wordlists`.`%s`  WHERE `%s` = '1' ORDER BY `text` ASC;", table, flag);
        //String q = "SELECT text FROM `word` WHERE name = '1' AND adj <> '1' AND adv <> '1' AND noun <> '1' AND verb <> '1';";
        if(dbSearch.query(q)){
            String[] list = dbSearch.getCol();
            int count = 0;
            try(
                    BufferedWriter file = new BufferedWriter(new FileWriter(fileName))
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
            System.out.printf("%s: Wrote %d of %d \n", flag, count, list.length);
        }
    }

    public static void fieldsToCsv(){// done: used to extract chosen fields from word table
        DbSearch dbSearch = DbSearch.getInstance();
        //String q = String.format("SELECT `text`,`m`,`f` FROM `wordlists`.`%s`  WHERE `%s` = '1' ORDER BY `text` ASC;", "word", "name");
        String[] cols = {"text","verb","noun","adj"};
        String q = new QueryGen.QueryGenBuilder().setTable("irreg").setCols(cols).
                setOrderBy("text").build().finish();
        System.out.println(q);
        if(dbSearch.query(q)){
            String[][] list = dbSearch.getAll();
            disp2d(list);

            int count = 0;
            try(
                    BufferedWriter file = new BufferedWriter(new FileWriter("temp.CSV"))
            ){
                for (String[] row: list) {
                    //System.out.printf("%s, %s, %s \n", row[0],row[1],row[2]);
                    file.write(String.format("%s,%s,%s,%s", row[0],row[1],row[2],row[3]));
                    file.newLine();
                    count++;
                    //break;
                }
            }
            catch(IOException e){
                System.out.println(": exception = " + e);
                System.exit(-1);
            }
            System.out.printf("%s: Wrote %d of %d \n", "fieldsToCsv", count, list.length);
        }
    }

    public static void fileMaxLineLen(String fileName){// useful
        TextSource textSource = new TextSourceFile(fileName);
        textSource.rewind();
        int max = 0;
        while(textSource.hasNext()){
            max = Math.max(textSource.next().trim().length(), max);
        }
        System.out.println("max = " + max);
    }

    public static void fieldsFromCSV(){// done: used to create new 'name' table
        DbSearch dbSearch = DbSearch.getInstance();
        TextSource textSource = new TextSourceFile("temp.CSV");
        textSource.rewind();
        while(textSource.hasNext()){
            String q = "";
            String[] fields = {"text","verb","noun","adj"};
            String[] row = textSource.next().trim().split(",");
            //Commons.disp(fields, "fields");
            //Commons.disp(row, "row");
            int size = 0;
            for(int i = 1; i < row.length; i++){
                if("1".equals(row[i])){
                    size ++;
                }
            }
            if(size > 1){System.out.println("size > 1 : " +row[0]);}
            String[] cols = new String[size];
            String[] vals = new String[size];
            int j = 0;
            for(int i = 1; i < row.length; i++){
                if("1".equals(row[i])){
                    cols[j] = fields[i];
                    vals[j] = "1";
                    j++;
                }
            }
            //Commons.disp(cols, "cols");
            q = new QueryGen.QueryGenBuilder().setOp(QueryGen.OP.UPDATE).setTable("word").
                    setCols(cols).setValues(vals).
                    setWhereScopes("text").setWhereStates(row[0]).
                    build().finish();
            System.out.println(q);
            dbSearch.query(q);
        }
    }

    public static void listDups(){// no dups found
        DbSearch dbSearch = DbSearch.getInstance();

        for(WORD_TYPE wordType : WORD_TYPE.values()){
            if(wordType == SURNAME){
                break;
            }
            if(wordType == CONTRACTION || wordType == PREFIX || wordType == SUFFIX){
                continue;
            }
            String field = wordType.toString().toLowerCase();
            String fileName = field + ".txt";
            TextSource textSource = new TextSourceFile(fileName);
            textSource.rewind();
            System.out.println("start: " + field);
            while(textSource.hasNext()){
                String next = textSource.next();
                String q = String.format("SELECT `text` FROM `word` WHERE `text` = '%s' AND `%s` != '1' ORDER BY `text` LIMIT 1;", next, field);
                //System.out.println(q);
                if(dbSearch.query(q)){
                    System.out.println("found: " + next);
                }
                //dbSearch.query(q);
            }
            System.out.println("finish: " + field);
        }
    }

    public static void emptiesToNull(){// done: used to fix flags != '1' empty strings
        DbSearch dbSearch = DbSearch.getInstance();

        for(WORD_TYPE wordType : WORD_TYPE.values()){
            String tableName = wordType.toString().toLowerCase();
            String[] colNames = dbSearch.getColNames(tableName);
            //Commons.disp(colNames);
            for(int i = 2; i < colNames.length; i++){
                String q = new QueryGen.QueryGenBuilder().setOp(QueryGen.OP.UPDATE).setTable(tableName).
                        setCols(colNames[i]).setValues("NULL").
                        setWhereScopes(colNames[i]).setWhereStates("").
                        build().finish();
                System.out.println(q);
                dbSearch.query(q);
            }
        }
    }

    public static void insertUnique(){
        DbSearchUtil util = new DbSearchUtil();
        System.out.println(util.insertUniqueAll("inserts.txt"));
        //System.out.println(util.insertUnique("word      war      noun verb"));
    }

    public static void toFlagFile(){
        DbSearch dbSearch = DbSearch.getInstance();
        for(WORD_TYPE wordType : WORD_TYPE.values()){
            String wordTypeTable = wordType.toString().toLowerCase();
            System.out.println("writing " + wordTypeTable);
            dbSearch.toFlagFile(wordTypeTable);
        }
    }
}
