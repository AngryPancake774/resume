# Mini SQL Engine (Java)

A simple SQL-like engine written in Java that supports:

- `SELECT` statements with multiple columns or `*` for all columns
- Optional `WHERE` clauses
- Numeric and string comparisons
- Multi-word string values using quotes, e.g., `"Computer Science"`

---

## **Files**

- `Main.java` – Entry point and user input loop
- `Table.java` – Represents tables loaded from CSV files
- `Row.java` – Represents individual rows in a table
- `Query.java` – Stores parsed query information
- `Parser.java` – Parses user input SQL into `Query`
- `QueryExecutor.java` – Executes queries on `Table` objects
- `student.csv` / `teachers.csv` – Sample data files

---

## **Setup**

1. Place all Java files and CSV files in the **same folder**.

---

## **How to Run**

```bash
# Compile all Java files
javac *.java

# Run the program
java Main
Example Queries
SELECT * FROM students WHERE gpa>=3.5
SELECT name,gpa FROM students WHERE major="Computer Science"
SELECT name FROM teachers WHERE department='Art History'
SELECT * FROM teachers WHERE salary>=60000

Operators: =, !=, >, >=, <, <=

Strings must use quotes if multi-word: "Computer Science" or 'Art History'

Type exit to quit the program.

Sample CSV Data

students.csv

id,name,major,gpa
1,John,Art,3.2
2,Ava,English,2.8
3,Mark,Computer Science,3.7
4,Claire,Biology,4.0
5,Duke,Dance,3.6

teachers.csv

id,name,department,salary
1,Emily,Mathematics,55000
2,James,Computer Science,72000
3,Sophia,English Literature,50000
4,Michael,Biology,60000
5,Linda,Art History,58000
6,Robert,Computer Science,68000
7,Olivia,Physics,63000
