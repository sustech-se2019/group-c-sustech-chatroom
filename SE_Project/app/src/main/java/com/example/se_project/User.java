package com.example.se_project;

import android.graphics.drawable.Drawable;

import java.io.Serializable;


public class User implements Serializable {
    private String name;
    private double gpa;
    private String id;
    private int potraitnum; //头像ID
    private int profilePictureID;
    public User(){}

    public User(String name,double gpa){
        this.gpa=gpa;
        this.name=name;
        this.setPotraitnum();
        this.setProfilePictureID();
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

    private void setPotraitnum(){
        if (this.gpa>=3.7){
            potraitnum = 3;
        }else if (this.gpa>=3.2){
            potraitnum = 2;
        }else if (this.gpa>=2.8){
            potraitnum = 1;
        }else{
            potraitnum = 0;
        }
    }
    private void setProfilePictureID(){
        profilePictureID=R.drawable.gpa0;
        if(potraitnum==3)
            profilePictureID=R.drawable.gpa3;
        else if(potraitnum==2)
            profilePictureID=R.drawable.gpa2;
        else if(potraitnum==1)
            profilePictureID=R.drawable.gpa1;
        else if(potraitnum==0)
            profilePictureID=R.drawable.gpa0;
    }
    public int getProfilePictureID(){
        return profilePictureID;
    }
    public int getPotraitnum(){
        return potraitnum;
    }
}
