package com.employee.employeecrud;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateDb {

    public static void createNewDatabase(String fileName) {

        String SQCONN = "jdbc:sqlite:employees.sqlite";

        try {
            Connection conn = DriverManager.getConnection(SQCONN);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        createNewDatabase("users.db");
    }
}  