import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static final String[] OPERATORS = { ">=", "<=", "!=", "=", ">", "<" };

    public Query parse(String input) {
        input = input.trim();

        String upperInput = input.toUpperCase();
        if (!upperInput.startsWith("SELECT")) {
            throw new IllegalArgumentException("Only SELECT queries are supported");
        }

        // Split SELECT and WHERE
        String[] parts = input.split("(?i)WHERE"); // case-insensitive
        String selectPart = parts[0].trim();
        String wherePart = (parts.length > 1) ? parts[1].trim() : null;

        // Remove SELECT keyword
        String selectColumnsPart = selectPart.substring(6).trim();

        // Extract table name if FROM is used
        String tableName = null;
        if (selectColumnsPart.toUpperCase().contains("FROM")) {
            String[] selectSplit = selectColumnsPart.split("(?i)FROM");
            selectColumnsPart = selectSplit[0].trim(); // columns
            tableName = selectSplit[1].trim(); // table name
        }

        // Parse columns
        List<String> columns = new ArrayList<>();
        if (selectColumnsPart.equals("*")) {
            columns.add("*");
        } else {
            String[] cols = selectColumnsPart.split(",");
            for (String col : cols) {
                columns.add(col.trim());
            }
        }

        // Parse WHERE clause
        String whereColumn = null;
        String operator = null;
        String value = null;

        if (wherePart != null) {
            boolean found = false;
            for (String op : OPERATORS) {
                int index = wherePart.indexOf(op);
                if (index != -1) {
                    whereColumn = wherePart.substring(0, index).trim();
                    operator = op;
                    value = wherePart.substring(index + op.length()).trim();

                    // Remove surrounding quotes if any
                    if ((value.startsWith("\"") && value.endsWith("\"")) ||
                            (value.startsWith("'") && value.endsWith("'"))) {
                        value = value.substring(1, value.length() - 1);
                    }

                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new IllegalArgumentException("Invalid WHERE clause: " + wherePart);
            }
        }

        return new Query(columns, tableName, whereColumn, operator, value);
    }
}