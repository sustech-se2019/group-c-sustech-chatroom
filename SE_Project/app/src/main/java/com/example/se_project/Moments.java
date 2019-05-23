package com.example.se_project;


import java.io.Serializable;

/**
 * Moments class
 */
public class Moments implements Serializable {
    private final User user;
    private final String text;
    private int goodNum;
    public Moments(User user, String text,int goodNum){
        this.user=user;
        this.text=text;
        this.goodNum=goodNum;
    }
    public User getUser() {
        return user;
    }
    public String getText(){
        return text;
    }
    public int getGoodnum(){
        return goodNum;
    }
    public void addGood(){
        this.goodNum++;
    }
}
