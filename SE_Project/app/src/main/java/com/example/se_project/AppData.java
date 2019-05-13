package com.example.se_project;


import com.example.se_project.Chat.ChatHistory;
import com.example.se_project.Chat.WSClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AppData {

    private static AppData singletonData = new AppData();

    public static AppData getInstance(){
        return singletonData;
    }

    private User me = new User();
    private WSClient wsClient;
    private User chattingFriend;
    private Map<String, ChatHistory> chatHistory = new HashMap<>();

    public void sendChatMsg(){

    }

    public void reciveChatMsg(String friendId, String msg, String msgId, Date time){
        ChatHistory history = chatHistory.get(friendId);
        if (history == null)
        {
            history = new ChatHistory();
            history.setFriendId(friendId);
            history.setMyId(me.getId());
            history.setLastTime(new Date(System.currentTimeMillis()));
            history.setMsgList(new ArrayList<Msg>());
        }

        Msg m = new Msg(msg, Msg.TYPE_RECEIVED, time);
        history.getMsgList().add(m);
    }

    public User getMe() {
        return me;
    }

    public WSClient getWsClient() {
        return wsClient;
    }

    public void setWsClient(WSClient wsClient) {
        this.wsClient = wsClient;
    }

    public void setMe(User me) {
        this.me = me;
    }
}
