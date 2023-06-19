package com.employee.employeecrud;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;


public class DbManager {
    public PreparedStatement pst;
    public Connection con;
    public Statement stmt;


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

    public void connect() {
        String SQCONN = "jdbc:sqlite:employee.sqlite";
        try {
            con = DriverManager.getConnection(SQCONN);
            System.out.println("Application created successfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public JTable tableLoad(JTable table) {
        try {
            pst = con.prepareStatement("SELECT * FROM employee");
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
    }
}
