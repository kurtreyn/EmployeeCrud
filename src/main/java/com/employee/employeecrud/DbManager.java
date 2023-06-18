package com.employee.employeecrud;
import java.sql.*;

public class DbManager {

    public static void createNewDatabase(String fileName) {
        String SQCONN = "jdbc:sqlite:employees.sqlite";
        try {
            Connection conn = DriverManager.getConnection(SQCONN);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new employee database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:employees.sqlite";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS employees (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " salary text NOT NULL,\n"
                + " mobile text NOT NULL,\n"
                + ");";

        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
