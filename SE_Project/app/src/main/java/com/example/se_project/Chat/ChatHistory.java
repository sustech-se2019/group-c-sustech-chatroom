package com.example.se_project.Chat;

import com.example.se_project.Msg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatHistory {

    private String friendId;
    private String myId;
    private Date lastTime;
    private List<Msg> msgList = new ArrayList<Msg>(){
        @Override
        public boolean add(Msg e){
            lastTime = e.getSendTime();
            return super.add(e);
        }
    };

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public List<Msg> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<Msg> msgList) {
        this.msgList = msgList;
    }
}