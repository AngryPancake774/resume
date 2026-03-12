
import java.util.List;

public class Query {

    private List<String> columns;
    private String tableName;

    private String whereColumn;
    private String operator;
    private String value;

    public Query(List<String> columns, String tableName,
            String whereColumn, String operator, String value) {

        this.columns = columns;
        this.tableName = tableName;
        this.whereColumn = whereColumn;
        this.operator = operator;
        this.value = value;
    }

    public List<String> getColumns() {
        return columns;
    }

    public String getTableName() {
        return tableName;
    }

    public String getWhereColumn() {
        return whereColumn;
    }

    public String getOperator() {
        return operator;
    }

    public String getValue() {
        return value;
    }
}