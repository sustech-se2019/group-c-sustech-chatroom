package com.example.se_project;
/**
 * Created by cpj on 2016/3/15.
 */
public class Msg {
    public static final int TYPE_RECEIVED = 0;//收到的消息
    public static final int TYPE_SENT = 1;//发送的消息
    private String content;//消息内容
    private  int type;//消息类型

    public  Msg(String content, int type){
        this.content = content;
        this.type = type;
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