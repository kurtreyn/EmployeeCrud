package com.employee.employeecrud;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EmployeeController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}