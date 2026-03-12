
//TO DO: 
//Store Rows and columns metadata
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Table {
    private String tableName;
    private List<String> col;
    private List<Row> rows;

    public Table(String tabName) {
        tableName = tabName;
        col = new ArrayList<>();
        rows = new ArrayList<>();

    }

    public void load() {
        String fileName = tableName + ".csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String header = reader.readLine();
            if (header == null) {
                throw new IOException("Empty File");
            }
            String[] colHeader = header.split(",");
            for (int i = 0; i < colHeader.length; i++) {
                col.add(colHeader[i]);
            }
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> rowData = new HashMap<>();
                for (int i = 0; i < col.size() && i < values.length; i++) {
                    String colName = col.get(i);
                    String data = values[i];
                    rowData.put(colName, data);
                }
                Row row = new Row(rowData);
                rows.add(row);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Row> getRows() {
        return rows;

    }

    public List<String> getColumns() {
        return col;

    }

    public String getTableName() {
        return tableName;
    }

}
