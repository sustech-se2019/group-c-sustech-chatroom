package com.example.se_project;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class User implements Serializable {
    private String name;
    private double gpa;
    private String id;
    private int potraitnum; //头像ID
    private int profilePictureID;
    private ArrayList<User> friendList=new ArrayList();
    public User(){
        User user1 = new User("a", 1);
        friendList.add(user1);
        User user2 = new User("b", 2);
        friendList.add(user2);
        User user3 = new User("c", 3);
        friendList.add(user3);
    }

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
        this.setPotraitnum();
        this.setProfilePictureID();
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
    public boolean addFriend(User user){
        this.refreshFriendList();
        for(int i=0;i<friendList.size();i++) {
            if (user.getName().equals( friendList.get(i).getName())) {
                return false;
            }
        }
        friendList.add(user);
        return true;
    }
    public boolean deleteFriend(User user){
        this.refreshFriendList();
        for(int i=0;i<friendList.size();i++) {
            if (user.getId() == friendList.get(i).getId()) {
                friendList.remove(i);
                //add
                return true;
            }
        }
        return false;
    }
    public ArrayList<User> searchFriend(String name){
        this.refreshFriendList();
        Pattern pattern=Pattern.compile(name);
        Matcher matcher;
        ArrayList<User> resultList=new ArrayList<>();
        for(int i=0;i<friendList.size();i++) {
            matcher = pattern.matcher((friendList.get(i)).getName());
            if (matcher.find()) {
                resultList.add(friendList.get(i));
            }
        }
        return resultList;
    }
    public ArrayList<User> getFriendList(){
        this.refreshFriendList();
        return friendList;
    }
    private void refreshFriendList(){
    }
}
