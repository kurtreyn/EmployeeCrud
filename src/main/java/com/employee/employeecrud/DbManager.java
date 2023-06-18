package com.employee.employeecrud;
import java.sql.*;

public class DbManager {

    public static void createNewDatabase(String fileName) {
        String SQCONN = "jdbc:sqlite:employee.sqlite";
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
        String url = "jdbc:sqlite:employee.sqlite";
        String createTable = "CREATE TABLE IF NOT EXISTS employee (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " salary text NOT NULL,\n"
                + " mobile text NOT NULL\n"
                + ");";

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(createTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
