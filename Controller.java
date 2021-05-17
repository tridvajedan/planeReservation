package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.sql.*;
import java.util.Scanner;

public class Controller {
    @FXML private Pane pane;
    @FXML private Spinner<Integer> ageSpinner;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private RadioButton maleButton;
    @FXML private RadioButton femaleButton;
    final int initialValue = 1;
    SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,initialValue);
    private Connection connection;
public void initialize()
{
    ageSpinner.setValueFactory(svf);
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    try {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb","root","root");
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
}
public void onButtonPress(ActionEvent event) throws SQLException {
        Statement statement = connection.createStatement();
        String gender = "";
        gender = maleButton.isSelected() ? "'Male'" : "'Female'";
        System.out.println("INSERT INTO users (username,password,age,gender) VALUES('"+usernameField.getText()+"','"+passwordField.getText()+"','"+ageSpinner.getValue()+"','"+gender+"')");
        statement.executeUpdate("INSERT INTO users (username,password,age,gender) VALUES('"+usernameField.getText()+"','"+passwordField.getText()+"','"+ageSpinner.getValue()+"',"+gender+")");
    }
}
