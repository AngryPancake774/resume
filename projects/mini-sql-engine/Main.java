import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        QueryExecutor executor = new QueryExecutor();

        System.out.println("Welcome to the mini SQL engine!");
        System.out.println("Enter queries in the format:");
        System.out.println("SELECT * FROM table WHERE column>=value");
        System.out.println("Or SELECT col1,col2 FROM table WHERE column=value");
        System.out.println("Type 'exit' to quit.\n");

        while (true) {
            System.out.print("Enter query: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                // Parse user input
                Query query = parser.parse(input);

                // Load table dynamically from tableName.csv
                if (query.getTableName() == null || query.getTableName().isEmpty()) {
                    System.out.println("Error: Table name missing in query.");
                    continue;
                }

                Table table = new Table(query.getTableName());
                table.load(); // loads tableName.csv

                // Execute query
                List<Row> results = executor.execute(query, table);

                // Display results
                if (results.isEmpty()) {
                    System.out.println("No matching rows found.");
                } else {
                    System.out.println("Query results:");
                    for (Row row : results) {
                        if (query.getColumns().size() == 1 && query.getColumns().get(0).equals("*")) {
                            System.out.println(row.getValue()); // prints full row map
                        } else {
                            for (String col : query.getColumns()) {
                                System.out.print(col + ": " + row.getValues(col) + "  ");
                            }
                            System.out.println();
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error processing query: " + e.getMessage());
            }

            System.out.println(); // blank line before next query
        }

        scanner.close();
    }
}