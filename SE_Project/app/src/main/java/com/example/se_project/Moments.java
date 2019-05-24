package com.example.se_project;


import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import java.util.Date;
import java.io.Serializable;

/**
 * Moments class
 */
public class Moments implements Serializable {
    private User user;
    private String text;
    private String momentId;
    private int goodNum;

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    private Date sendTime;
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

    public void addGood(){
        this.goodNum++;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMomentId() {
        return momentId;
    }

    public void setMomentId(String momentId) {
        this.momentId = momentId;
    }

    public int getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(int goodNum) {
        this.goodNum = goodNum;
    }




}
