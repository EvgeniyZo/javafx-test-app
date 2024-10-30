package com.example.javafxtestapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:13306/javafxTest", "javafxTest", "changeme");
        return con;
    }
}
