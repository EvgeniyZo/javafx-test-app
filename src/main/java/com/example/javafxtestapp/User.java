package com.example.javafxtestapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
    public int id;
    public String login;
    public String name;
    public int groupId;
    public int roleId;

    public static User load(String login, String password) {
        try {
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from User where login='" + login + "' and password='" + password + "'");
            if (rs.next() == false) {
                return null;
            } else {
                User user = new User();
                user.id = rs.getInt("id");
                user.login = login;
                user.name = rs.getString("name");
                user.groupId = rs.getInt("groupId");
                user.roleId = rs.getInt("roleId");
                return user;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
