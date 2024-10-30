package com.example.javafxtestapp;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.*;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainController {
    @FXML
    private TableView gradeTable;

    private User user;

    public void loadStudentData(User user) {
        this.user = user;
        gradeTable.getItems().clear();
        ArrayList<Grade> grades;
        if (user.roleId == 1) {
            grades = getAllGrades();
        } else {
            grades = getGrades();
        }

        for (var grade : grades) {
            gradeTable.getItems().add(grade);
        }
    }

    @FXML
    public void onExportClick() {
        ArrayList<Grade> grades;
        if (user.roleId == 1) {
            grades = getAllGrades();
        } else {
            grades = getGrades();
        }

        try {
            PrintWriter pw = new PrintWriter("grades.csv");
            for (var grade : grades) {
                pw.println(
                        grade.getId() + ","
                                + grade.getName() + ","
                                + grade.getCourse() + ","
                                + grade.getValue()
                );
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }

    @FXML
    public void onImportClick() {
        ArrayList<Grade> grades = new ArrayList<>();
        try {
            FileReader fr = new FileReader("grades.csv");
            BufferedReader br = new BufferedReader(fr);
            String str;
            while ( (str = br.readLine()) != null ) {
                String[] arr = str.split(",");
                Grade g = new Grade(
                        Integer.parseInt(arr[0]), // id
                        arr[1], // name
                        arr[2],  // course
                        arr[3]); // value
                grades.add(g);
            }
            Connection con = Database.getConnection();
            for (var grade : grades) {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(
                        "SELECT id FROM GradeValue WHERE name='" + grade.getValue() + "'"
                );
                rs.next();
                int gradeId = rs.getInt("id");

                rs = stmt.executeQuery(
                        "SELECT id FROM User WHERE name='" + grade.getName() + "'"
                );
                rs.next();
                int userId = rs.getInt("id");

                rs = stmt.executeQuery(
                        "SELECT id FROM Journal WHERE course='" + grade.getCourse() + "'"
                );
                rs.next();
                int journalId = rs.getInt("id");

                stmt.execute(
                        "INSERT INTO Grade"
                                + " VALUES (null," + journalId
                                + "," + userId
                                + "," + gradeId
                                + ")");
            }
            con.close();
        } catch (IOException e) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Grade> getAllGrades() {
        ArrayList<Grade> grades = new ArrayList<>();
        try {
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT g.id, u.name as student, j.course AS course, gv.name AS value"
                            + " FROM Grade g JOIN GradeValue gv ON g.value=gv.id"
                            + " JOIN Journal j ON g.journalId=j.id"
                            + " JOIN User u ON g.userId=u.id");
            while (rs.next()) {
                Grade g = new Grade(
                        rs.getInt("id"),
                        rs.getString("student"),
                        rs.getString("course"),
                        rs.getString("value"));
                grades.add(g);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return grades;
    }

    private ArrayList<Grade> getGrades() {
        ArrayList<Grade> grades = new ArrayList<>();
        try {
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            int id = user.id;
            ResultSet rs = stmt.executeQuery(
                    "SELECT g.id, j.course AS course, gv.name AS value"
                            + " FROM Grade g JOIN GradeValue gv ON g.value=gv.id"
                            + " JOIN Journal j ON g.journalId=j.id WHERE g.userId=" + id);
            while (rs.next()) {
                Grade g = new Grade(
                        rs.getInt("id"),
                        user.name,
                        rs.getString("course"),
                        rs.getString("value"));
                grades.add(g);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return grades;
    }
}