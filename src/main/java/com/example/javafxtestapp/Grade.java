package com.example.javafxtestapp;

public class Grade {
    private int id;

    private String name;
    private String course;
    private String value;

    public Grade(int id, String name, String course, String value) {
        this.id =id;
        this.name = name;
        this.course = course;
        this.value = value;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public String getValue() {
        return value;
    }

}
