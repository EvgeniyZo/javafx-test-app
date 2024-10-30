package com.example.javafxtestapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;
import java.util.concurrent.Callable;

public class LoginController {

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    public void onLoginClick() {
        String login = loginField.getText();
        String password = passwordField.getText();

        User user = User.load(login, password);

        if (user != null) {
            HelloApplication.loadMainView(user);
        } else {
            messageLabel.setText("Неверный логин или пароль!");
        }
    }
}
