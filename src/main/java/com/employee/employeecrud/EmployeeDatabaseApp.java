package com.employee.employeecrud;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EmployeeDatabaseApp {
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
    PreparedStatement pst;
    Connection con;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Employee Database App");
        frame.setContentPane(new EmployeeDatabaseApp().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //    CONSTRUCTOR
    public EmployeeDatabaseApp() {
        connect();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name, salary, mobile;
                name = txtName.getText();
                salary = txtSalary.getText();
                mobile = txtMobile.getText();

                try {
                    pst = con.prepareStatement("INSERT INTO employee(name,salary,mobile)VALUES(?,?,?)");
                    pst.setString(1, name);
                    pst.setString(2, salary);
                    pst.setString(3, mobile);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Added");
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

    public void connect() {
        String SQCONN = "jdbc:sqlite:employee.sqlite";
        try {
            con = DriverManager.getConnection(SQCONN);
            System.out.println("Successs");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

