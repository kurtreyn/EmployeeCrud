module com.employee.employeecrud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.employee.employeecrud to javafx.fxml;
    exports com.employee.employeecrud;
}