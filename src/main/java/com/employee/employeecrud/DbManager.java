package com.employee.employeecrud;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.*;


public class DbManager {
    public PreparedStatement pst;
    public Connection con;
    public Statement stmt;
    public JTable table1;

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

    public void closeConnection() {
        String SQCONN = "jdbc:sqlite:employee.sqlite";
        try {
            con = DriverManager.getConnection(SQCONN);
            stmt = con.createStatement();
            con.close();
            stmt.close();
            System.out.println("Application closed successfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void loadTable(JTable table) {
        String query = "SELECT * FROM employee";
        try {
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int columnCount = rsmd.getColumnCount();
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = rsmd.getColumnName(i);
            }
            model.setColumnIdentifiers(columnNames);
            String ID,Name, Salary, Mobile;
            while (rs.next()) {
                ID = rs.getString("id");
                Name = rs.getString("name");
                Salary = rs.getString("salary");
                Mobile = rs.getString("mobile");
                model.addRow(new Object[]{ID,Name, Salary, Mobile});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
