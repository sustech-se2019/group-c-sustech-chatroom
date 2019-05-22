package com.example.se_project;


import java.io.Serializable;

/**
 * Moments class
 */
public class Moments implements Serializable {
    private User user;
    private String text;
    private int goodnum=0;
    public Moments(User user, String text,int goodnum){
        this.user=user;
        this.text=text;
        this.goodnum=goodnum;
    }
    public User getUser() {
        return user;
    }
    public String getText(){
        return text;
    }
    public int getGoodnum(){
        return goodnum;
    }
    public void addGood(){
        this.goodnum++;
    }
}
