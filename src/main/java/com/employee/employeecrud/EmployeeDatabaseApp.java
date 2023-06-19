package com.employee.employeecrud;

import javax.swing.*;
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
        DbManager db = new DbManager();
        db.connect();
        db.tableLoad(table1);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Employee employee = new Employee();

                String name, salary, mobile;
                name = txtName.getText();
                salary = txtSalary.getText();
                mobile = txtMobile.getText();

                try {
                    pst = db.con.prepareStatement("INSERT INTO employee(name,salary,mobile)VALUES(?,?,?)");
                    pst.setString(1, name);
                    pst.setString(2, salary);
                    pst.setString(3, mobile);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Added");
                    db.tableLoad(table1);
                    txtName.setText("");
                    txtSalary.setText("");
                    txtMobile.setText("");
                    txtName.requestFocus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtid.getText();
                try {
                    pst = db.con.prepareStatement("SELECT * FROM employee WHERE id = ?");
                    pst.setString(1, id);
                    ResultSet rs = pst.executeQuery();
                    if (rs.next() == true) {
                        String name = rs.getString(2);
                        String salary = rs.getString(3);
                        String mobile = rs.getString(4);

                        txtName.setText(name);
                        txtSalary.setText(salary);
                        txtMobile.setText(mobile);
                    } else {
                        txtName.setText("");
                        txtSalary.setText("");
                        txtMobile.setText("");
                        JOptionPane.showMessageDialog(null, "Invalid ID");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id;
                id = txtid.getText();
                try {
                    pst = db.con.prepareStatement("DELETE FROM employee WHERE id = ?");
                    pst.setString(1, id);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleted");
                    db.tableLoad(table1);
                    txtName.setText("");
                    txtSalary.setText("");
                    txtMobile.setText("");
                    txtName.requestFocus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name, salary, mobile, id;
                name = txtName.getText();
                salary = txtSalary.getText();
                mobile = txtMobile.getText();
                id = txtid.getText();

                try {
                    pst = db.con.prepareStatement("UPDATE employee SET name = ?, salary = ?, mobile = ? WHERE id = ?");
                    pst.setString(1, name);
                    pst.setString(2, salary);
                    pst.setString(3, mobile);
                    pst.setString(4, id);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updated");
                    db.tableLoad(table1);
                    txtName.setText("");
                    txtSalary.setText("");
                    txtMobile.setText("");
                    txtName.requestFocus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

}

