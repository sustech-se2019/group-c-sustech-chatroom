package com.example.se_project;

public class User {
    private String name;
    private double gpa;
    private String id;

    public User(){}

    public User(String name,double gpa){
        this.gpa=gpa;
        this.name=name;
    }

    public String getName(){
        return name;
    }
    public double getGpa(){
        return gpa;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
