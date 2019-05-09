package com.example.se_project;

public class User {
    private String name;
    private double gpa;
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
}
