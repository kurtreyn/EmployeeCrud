package com.employee.employeecrud;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class EmployeeDatabaseApp{
    private JPanel Main;
    private JTextField txtName;
    private JTextField txtSalary;
    private JTextField txtMobile;
    private JButton saveButton;
    private JTable table1;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JTextField txtid;
    private JScrollPane employee_table;
    PreparedStatement pst;
    Connection con;
    Statement stmt;


    public static void main(String[] args) {
        DbManager db = new DbManager();
        db.createNewDatabase("employee.sqlite");
        db.createNewTable();
        JFrame frame = new JFrame("Employee Database App");
        frame.setContentPane(new EmployeeDatabaseApp().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //    CONSTRUCTOR
    public EmployeeDatabaseApp() {
        connect();
        loadTable();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee employee = new Employee();

                String name, salary, mobile;
                name = txtName.getText();
                salary = txtSalary.getText();
                mobile = txtMobile.getText();

                try {
                    pst = con.prepareStatement("INSERT INTO employee(name,salary,mobile)VALUES(?,?,?)");
                    pst.setString(1, name);
                    pst.setString(2, salary);
                    pst.setString(3, mobile);

                    int addedRows = pst.executeUpdate();
                    if (addedRows > 0) {
                        employee.name = name;
                        employee.salary = salary;
                        employee.mobile = mobile;
                    }

//                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Added");
                    txtName.setText("");
                    txtSalary.setText("");
                    txtMobile.setText("");
                    txtName.requestFocus();

                    closeConnection();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });
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

    public void loadTable() {
        String query = "SELECT * FROM employee";
        try {
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            int columnCount = rsmd.getColumnCount();
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = rsmd.getColumnName(i);
            }
            model.setColumnIdentifiers(columnNames);
            String name, salary, mobile;
            while (rs.next()) {
                name = rs.getString("name");
                salary = rs.getString("salary");
                mobile = rs.getString("mobile");
                model.addRow(new Object[]{name, salary, mobile});
            }
//            pst = con.prepareStatement("SELECT * FROM employee");
//            ResultSet rs = pst.executeQuery();
//            employee_table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
