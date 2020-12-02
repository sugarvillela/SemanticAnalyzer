package search;

import erlog.Erlog;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DbSearch {
    private static DbSearch instance;

    public static DbSearch getInstance(){
        return (instance == null)? (instance = new DbSearch()) : instance;
    }

    /*==============================================================================================*/

    private static final String URL = "jdbc:mysql://localhost:3306/wordlists?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection dbc;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private DbSearch(){
        //System.out.println("DbSearch");
        try{
            //System.out.println("try");
            dbc = DriverManager.getConnection(URL, USER, PASSWORD);
            //System.out.println("dbc good");
        }catch(SQLException ex){
            Erlog.kill("Initial database connection failed: \n" + ex);
        }
    }

    /**Calling this is a pre-condition for all methods except getColNames
     * @param q SQL determines what gets returned */
    public boolean query(String q){
        //System.out.println(q);
        try {
            preparedStatement = dbc.prepareStatement(q);
            if(preparedStatement.execute()){
                resultSet = preparedStatement.getResultSet();
                //System.out.println("select");
                return resultSet.isBeforeFirst();
            }
            else{
                //System.out.println("insert update");
                return preparedStatement.getUpdateCount() > 0;
            }
        }
        catch (SQLException ex) {
            Erlog.kill("query failed: \n" + ex, q);
        }
        return false;
    }

    public int getColCount(){// expects query already run
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            return metaData.getColumnCount();
        }
        catch (SQLException ex) {
            Erlog.kill("getColCount failed: \n" + ex);
            return 0;
        }
    }

    public String[] getColNames(String tableName) {
        String q = String.format("SELECT * FROM %s LIMIT 1;", tableName);
        this.query(q);
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int len = metaData.getColumnCount();
            String[] out = new String[len];
            for (int i = 0; i < len; i++) {
                out[i] = metaData.getColumnName(i+1);
            }
            return out;
        }
        catch (SQLException ex) {
            Erlog.kill("getColNames failed: \n" + ex, q);
            return null;
        }
    }

    public boolean hasResult(){// expects query already run
        try {
            return resultSet.isBeforeFirst();
        }
        catch(Exception ex)  {
            Erlog.kill("hasResult() failed: \n" + ex);
            return false;
        }
    }

    public boolean exists(String table, String text){
        return query(String.format("SELECT `id_%s` FROM `%s` WHERE `text` = '%s' LIMIT 1;", table, table, text));
    }

    public String getOne(){// after single-item query LIMIT 1
        try {
            if (resultSet.next()) {
                return resultSet.getString(1);
            }

        } catch (SQLException ex) {
            Erlog.kill("get1D() failed: \n" + ex);
        }
        return null;
    }

    public String[] getCol(){// after single-item query
        ArrayList<String> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
            return list.toArray(new String[list.size()]);

        } catch (SQLException ex) {
            Erlog.kill("get1D() failed: \n" + ex);
        }
        return null;
    }

    public String[] getRow(){
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            return getRow(metaData.getColumnCount());
        }
        catch (SQLException ex) {
            Erlog.kill("getRow() failed: \n" + ex);
            return null;
        }
    }

    public String[] getRow(int colCount){// after * query LIMIT 1
        try {
            String[] out = new String[colCount];
            if (resultSet.next()) {
                for (int i = 0; i < colCount; i++) {
                    out[i] = resultSet.getString(i + 1);
                }
            }
            return out;
        }
        catch (SQLException ex) {
            Erlog.kill("getRow(n) failed: \n" + ex);
            return null;
        }
    }

    public boolean[] getRowBoolean(int startCol){
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            return getRowBoolean(metaData.getColumnCount(), startCol);
        }
        catch (SQLException ex) {
            Erlog.kill("getRow() failed: \n" + ex);
            return null;
        }
    }

    public boolean[] getRowBoolean(int colCount, int startCol){// after * query LIMIT 1
        try {
            boolean[] out = new boolean[colCount-startCol + 1];
            //System.out.println(colCount + " -> " + out.length);
            if (resultSet.next()) {
                for (int i = startCol; i < colCount; i++) {
                    //System.out.println(i);
                    out[i - startCol] = resultSet.getBoolean(i);
                }
            }
            return out;
        }
        catch (SQLException ex) {
            Erlog.kill("getRow(n) failed: \n" + ex);
            return null;
        }
    }

    public String[][] getAll(){
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            return getAll(metaData.getColumnCount());
        }
        catch (SQLException ex) {
            Erlog.kill("getAll() failed: \n" + ex);
            return null;
        }
    }

    public String[][] getAll(int colCount){
        ArrayList<String[]> list = new ArrayList<>();
        String[] row;
        try {
            while (resultSet.next()) {
                row = new String[colCount];
                for (int i = 0; i < colCount; i++) {
                    row[i] = resultSet.getString(i + 1);
                }
                list.add(row);
            }
            return list.toArray(new String[list.size()][]);

        } catch (SQLException ex) {
            Erlog.kill("getAll(n) failed: \n" + ex);
        }
        return null;
    }

    public void toFlagFile(String table){
        String[] colNames = getColNames(table);
        String q = String.format("SELECT * FROM `wordlists`.`%s` ORDER BY `text` ASC;", table);
        if(query(q)){
            String fileName = "flags_" + table + ".txt";
            ArrayList<String> row = new ArrayList<>();
            int colCount = colNames.length;
            int count = 0;

            try(
                    BufferedWriter file = new BufferedWriter(new FileWriter(fileName))
            ){
                file.write(table);
                file.newLine();
                try {
                    while (resultSet.next()) {
                        row.add(resultSet.getString(2));
                        for (int i = 2; i < colCount; i++) {
                            if("1".equals(resultSet.getString(i + 1))){
                                row.add(colNames[i]);
                            }
                        }
                        //System.out.println(String.join(" ", row));
                        file.write(String.join(" ", row));
                        file.newLine();
                        count++;
                        row.clear();
                    }
                }
                catch (SQLException ex) {
                    Erlog.kill("toFlagFile() failed: \n" + ex);
                    //return null;
                }
            }
            catch(IOException e){
                System.out.println(table + ": exception = " + e);
                System.exit(-1);
            }
            System.out.printf("%s: Wrote %d items \n", table, count);
        }
    }

    public ResultSet getResultSet(){
        return resultSet;
    }

    public void rollback() {
        try {
            if (dbc != null) {
                dbc.rollback();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (dbc != null) {
                dbc.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
