package search;

import erlog.Erlog;

import java.util.ArrayList;

public class QueryGen {
    public enum OP{
        SELECT, INSERT, UPDATE, DELETE
    }

    private static final String DEFAULT_DB_NAME = "wordlists";
    private static final String AND = " AND ";
    private static final String OR = " OR ";
    private OP op;
    private String[] cols, values, whereScopes, whereStates;
    private boolean whereAnd, descending;
    private String dbName, table, orderBy, limit;

    ArrayList<String> out;

    public String finish(){
        out = new ArrayList<>();
        if(op == null){
            op = OP.SELECT;
        }
        if(dbName == null){
            dbName = DEFAULT_DB_NAME;
        }
        if(table == null){
            Erlog.kill("table name is required");
        }
        out.add(op.toString());
        switch(op){
            case SELECT:
                genSelect();
                break;
            case DELETE:
                genDelete();
                break;
            case INSERT:
                genInsert();
                break;
            case UPDATE:
                genUpdate();
                break;
        }
        return fixQuotedNull(String.join (" ", out) + ";");
    }

    private void genSelect(){
        if(cols == null){
            out.add("*");
        }
        else{
            out.add("`" + String.join("`, `", cols) + "`");
        }
        out.add("FROM");
        genTableName();
        genWhere();
        if(orderBy != null){
            out.add("ORDER BY");
            out.add("`" + orderBy + "`");
            out.add((descending)? "DESC" : "ASC");
        }
        if(limit != null){
            out.add("LIMIT");
            out.add(limit);
        }
    }

    private void genDelete(){
        out.add("FROM");
        genTableName();
        genWhere();
    }

    private void genInsert(){
        if(cols == null || values == null || cols.length != values.length){
            Erlog.kill("column name or value error");
        }
        out.add("INTO");
        genTableName();
        genInsertCols();
        out.add("VALUES");
        genInsertValues();
    }

    private void genUpdate(){
        if(cols == null || values == null || cols.length != values.length){
            Erlog.kill("column name or value error");
        }
        genTableName();
        out.add("SET");
        genUpdateKeyVals();
        genWhere();
    }

    private void genTableName(){
        out.add("`" + dbName + "`.`" + table  + "`");
    }

    private void genCols(){
        out.add("`" + String.join("`, `", cols) + "`");
    }

    private void genInsertCols(){
        out.add("(`" + String.join("`, `", cols) + "`)");
    }

    private void genInsertValues(){
        out.add("('" + String.join("', '", values) + "')");
    }

    private void genUpdateKeyVals(){
        String[] acc = new String[cols.length];
        for(int i = 0; i < cols.length; i++){
            acc[i] = String.format("`%s` = '%s'", cols[i], values[i]);
        }
        out.add(String.join (", ", acc));
    }

    private void genWhere(){
        if(whereScopes != null && whereStates != null){
            String connector = (whereAnd)? AND : OR;
            int len = Math.max(whereScopes.length, whereStates.length);
            String[] acc = new String[len];
            for(int i = 0; i < len; i++){
                acc[i] = String.format(
                    "`%s` = '%s'",
                    whereScopes[i % whereScopes.length],
                    whereStates[i % whereStates.length]);
            }
            out.add("WHERE");
            out.add(String.join (connector, acc));
        }
    }

    private String fixQuotedNull(String query){
        return query.replaceAll("'NULL'", "NULL");
    }

    public static class QueryGenBuilder {
        private String op;

        private final QueryGen built;

        public QueryGenBuilder() {
            built = new QueryGen();
        }
        public QueryGenBuilder setOp(OP op){
            built.op = op;
            return this;
        }
        public QueryGenBuilder setCols(String... cols){
            built.cols = cols;
            return this;
        }
        public QueryGenBuilder setValues(String... values){
            built.values = values;
            return this;
        }
        public QueryGenBuilder setDbName(String dbName){
            built.dbName = dbName;
            return this;
        }
        public QueryGenBuilder setTable(String table){
            built.table = table;
            return this;
        }
        public QueryGenBuilder setWhereScopes(String... whereScopes){
            built.whereScopes = whereScopes;
            return this;
        }
        public QueryGenBuilder setWhereStates(String... whereStates){
            built.whereStates = whereStates;
            return this;
        }
        public QueryGenBuilder setWhereAnd(){
            built.whereAnd = true;
            return this;
        }
        public QueryGenBuilder setOrderBy(String orderBy){
            built.orderBy = orderBy;
            return this;
        }
        public QueryGenBuilder setDescending(){
            built.descending = true;
            return this;
        }
        public QueryGenBuilder setLimit(int limit){
            built.limit = String.valueOf(limit);
            return this;
        }
        public QueryGen build(){
            return built;
        }
    }
}
