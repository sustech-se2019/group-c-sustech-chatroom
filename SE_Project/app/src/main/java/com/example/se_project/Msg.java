package com.example.se_project;

import java.util.Date;

/**
 * Created by cpj on 2016/3/15.
 */
public class Msg {
    public static final int TYPE_RECEIVED = 0;//收到的消息
    public static final int TYPE_SENT = 1;//发送的消息
    private String content;//消息内容
    private  int type;//消息类型
    private Date sendTime;
    private String msgId;

    public  Msg(String content, int type){
        this.content = content;
        this.type = type;
        this.sendTime = new Date(System.currentTimeMillis());
    }

    public Msg(String content, int type, Date sendTime, String msgId) {
        this.content = content;
        this.type = type;
        this.sendTime = sendTime;
        this.msgId = msgId;
    }

    public Msg(String content, int type, String msgId) {
        this.content = content;
        this.type = type;
        this.msgId = msgId;
        this.sendTime = new Date(System.currentTimeMillis());
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Msg(){
        this.content = null;
        this.type = 0;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setType(int type){
        this.type = type;
    }

}