import java.util.ArrayList;
import java.util.List;

public class QueryExecutor {

    public List<Row> execute(Query query, Table table) {
        List<Row> rows = table.getRows();
        List<Row> result = new ArrayList<>();

        for (Row row : rows) {
            // If there is no WHERE clause, include all rows
            boolean matches = true;

            if (query.getWhereColumn() != null) {
                String rowValue = row.getValues(query.getWhereColumn());
                String queryValue = query.getValue();

                matches = compare(rowValue, queryValue, query.getOperator());
            }

            if (matches) {
                result.add(row);
            }
        }

        return result;
    }

    private boolean compare(String rowValue, String queryValue, String operator) {
        boolean isNumeric = false;
        double rowNum = 0, queryNum = 0;

        // Detect numeric comparison
        try {
            rowNum = Double.parseDouble(rowValue);
            queryNum = Double.parseDouble(queryValue);
            isNumeric = true;
        } catch (NumberFormatException e) {
            isNumeric = false;
        }

        if (isNumeric) {
            switch (operator) {
                case "=":
                    return rowNum == queryNum;
                case "!=":
                    return rowNum != queryNum;
                case ">":
                    return rowNum > queryNum;
                case ">=":
                    return rowNum >= queryNum;
                case "<":
                    return rowNum < queryNum;
                case "<=":
                    return rowNum <= queryNum;
            }
        } else {
            // String comparison only supports = and !=
            switch (operator) {
                case "=":
                    return rowValue.equalsIgnoreCase(queryValue);
                case "!=":
                    return !rowValue.equalsIgnoreCase(queryValue);
                default:
                    throw new IllegalArgumentException(
                            "Operator " + operator + " not supported for string comparison");
            }
        }
        return false;
    }
}