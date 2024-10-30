package com.example.javafxtestapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage stage;
    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void loadStudentView(User user) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("student-view.fxml"));
        try {
            VBox box = fxmlLoader.load();
            MainController controller = fxmlLoader.getController();
            controller.loadStudentData(user);

            Scene scene = new Scene(box, 320, 240);
            stage.setTitle("Электронный журнал: Студент " + user.name);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void loadTeacherView(User user) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("student-view.fxml"));
        try {
            VBox box = fxmlLoader.load();
            MainController controller = fxmlLoader.getController();
            controller.loadStudentData(user);
            Scene scene = new Scene(box, 320, 240);
            stage.setTitle("Электронный журнал: Преподаватель " + user.name);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void loadMainView(User user) {
        if (user.roleId == 1) {
            loadTeacherView(user);
        } else {
            loadStudentView(user);
        }

    }

    public static void main(String[] args) {
        launch();
    }
}
