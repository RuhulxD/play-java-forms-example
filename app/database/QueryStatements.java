package database;

import java.util.Arrays;
import java.util.List;

public class QueryStatements {
    private final String sAll;
    private final String del;
    private final String idColumn;
    private final String TABLE;

    public QueryStatements(String table, String idColumn) {
        sAll = "select a from " + table + " a ";
        del = "delete from " + table;
        this.idColumn = idColumn;
        this.TABLE = table;
    }

    public String selectALLq() {
        return sAll;
    }

    public String selectOne() {
        return sAll + " where " + idColumn + " =?1 ";
    }

    public String selectOneq(String... columns) {
        return selectOneq(Arrays.asList(columns));
    }

    public String selectOneq(List<String> columns) {
        System.out.println(columns);
        StringBuilder builder = new StringBuilder().append(selectOne());
        appendConditions(builder, " and ", columns);
        return builder.toString();
    }

    private void appendConditions(StringBuilder builder, String andOr, List<String> columns) {
        for (int i = 0; i < columns.size(); i++) {
            builder.append(andOr).append(columns.get(i)).append(" =?").append(i + 1);
        }
    }

    private void appendConditions(StringBuilder builder, String andOr, String... columns) {
        appendConditions(builder, andOr, Arrays.asList(columns));
    }

//    public String selectListq(int start, int limit) {
//        return new StringBuilder().append(selectOne()).append(" limit ").append(start).append(" , ").append(limit).toString();
//    }

    public String deleteAllQuery() {
        return del;
    }

    public String deleteQuery(String... columns) {
        return deleteQuery(Arrays.asList(columns));
    }

    public String deleteQuery(List<String> columns) {
        StringBuilder builder = new StringBuilder().append(del).append(" where ").append(columns.get(0)).append("=?1 ");
        appendConditions(builder, " and ", columns);
        return builder.toString();
    }

    public String deleteQuery() {
        StringBuilder builder = new StringBuilder().append(del).append(" where ").append(idColumn).append("=?1 ");
        return builder.toString();
    }
}
