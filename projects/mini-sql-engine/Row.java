import java.util.Map;

public class Row {
    private Map<String, String> values;

    public Row(Map<String, String> values) {
        this.values = values;
    }

    public String getValues(String column) {
        return values.get(column);
    }

    public Map<String, String> getValue() {
        return values;
    }
}
