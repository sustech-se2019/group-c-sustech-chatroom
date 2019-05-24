package com.example.se_project;


import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSONObject;
import com.example.se_project.Chat.ChatHistory;
import com.example.se_project.Chat.ImageMsg;
import com.example.se_project.Chat.WSClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AppData {

    private User me = new User();
    private WSClient wsClient;
    private User chattingFriend = new User();
    private Map<String, ChatHistory> chatHistory = new HashMap<>();
    private List<User> friendList = new ArrayList<>();
    private Handler chatHandler;
    private List<Moments> momentsList;
    private Handler friendHandler;

    private Handler friendAddHandler;

    private Handler momentsHandler;


    private Context context;
    private Context chatContext;

    private static AppData singletonData = new AppData();

    public static AppData getInstance() {
        return singletonData;
    }


    public User getFriend(String userId) {
        if (userId.equals(me.getId())) {
            return me;
        } else {
            for (User tmp : friendList) {
                if (tmp.getId().equals(userId)) {
                    return tmp;
                }
            }
            return null;
        }
    }


    private void refreshChat() {
        if (chatHandler == null)
            return;
        Message message = new Message();
        JSONObject result_json = new JSONObject();
        result_json.put("status", 800);
        message.obj = result_json;
        chatHandler.sendMessage(message);
    }

    public List<Moments> getMomentsList() {
        return momentsList;
    }

    public void sendChatMsg(String msg) {
        ChatHistory history = chatHistory.get(chattingFriend.getId());
        if (wsClient.isOpen() && chattingFriend.getId() != null) {
            wsClient.sendMsg(chattingFriend.getId(), msg);
            Msg m = new Msg(msg, Msg.TYPE_SENT, new Date(System.currentTimeMillis()), null);
            history.getMsgList().add(m);
        }
        refreshChat();
    }

    public void reciveChatMsg(String friendId, String msg, String msgId, Date time) {
        ChatHistory history = chatHistory.get(friendId);
        if (history == null) {
            history = new ChatHistory();
            history.setFriendId(friendId);
            history.setMyId(me.getId());
            history.setLastTime(time);
//            history.setMsgList(new ArrayList<Msg>());
            chatHistory.put(friendId, history);
        }
        System.out.println("reciveChatMsg: " + msg);
        Msg m = new Msg(msg, Msg.TYPE_RECEIVED, time, msgId);
        history.getMsgList().add(m);
        refreshChat();
    }

    public void reciveChatMsg(String friendId, String msg, String msgId) {
        ChatHistory history = chatHistory.get(friendId);
        if (history == null) {
            history = new ChatHistory();
            history.setFriendId(friendId);
            history.setMyId(me.getId());
            history.setLastTime(new Date(System.currentTimeMillis()));
//            history.setMsgList(new ArrayList<Msg>());
            chatHistory.put(friendId, history);
        }

        Msg m = new Msg(msg, Msg.TYPE_RECEIVED, msgId);
        history.getMsgList().add(m);
        refreshChat();
    }

    public void sendImageMsg(String msg) {
        ChatHistory history = chatHistory.get(chattingFriend.getId());

        ImageMsg m = new ImageMsg(msg, Msg.TYPE_SENT, new Date(System.currentTimeMillis()), null);
        history.getMsgList().add(m);
        refreshChat();
    }

    public void reciveImageMsg(String friendId, String msg, String msgId, Date time) {

        if (time == null)
            time = new Date(System.currentTimeMillis());
        ChatHistory history = chatHistory.get(friendId);
        if (history == null) {
            history = new ChatHistory();
            history.setFriendId(friendId);
            history.setMyId(me.getId());
            history.setLastTime(time);
            chatHistory.put(friendId, history);
        }
        System.out.println("reciveImageMsg: " + msg);
        Msg m = new ImageMsg(msg, Msg.TYPE_RECEIVED, time, msgId);
        history.getMsgList().add(m);
        refreshChat();
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

    public List<User> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<User> friendList) {
        this.friendList = friendList;
    }

    public User getChattingFriend() {
        return chattingFriend;
    }

    public void setChattingFriend(User chattingFriend) {
        this.chattingFriend = chattingFriend;
    }

    public Map<String, ChatHistory> getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(Map<String, ChatHistory> chatHistory) {
        this.chatHistory = chatHistory;
    }

    public Handler getChatHandler() {
        return chatHandler;
    }

    public void setChatHandler(Handler chatHandler) {
        this.chatHandler = chatHandler;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getChatContext() {
        return chatContext;
    }

    public void setChatContext(Context chatContext) {
        this.chatContext = chatContext;
    }

    public Handler getFriendHandler() {
        return friendHandler;
    }

    public void setFriendHandler(Handler friendHandler) {
        this.friendHandler = friendHandler;
    }

    public Handler getFriendAddHandler() {
        return friendAddHandler;
    }

    public void setFriendAddHandler(Handler friendAddHandler) {
        this.friendAddHandler = friendAddHandler;
    }

    public Handler getMomentsHandler () {
        return momentsHandler;
    }

    public void setMomentsHandler (Handler momentsHandler){
        this.momentsHandler = momentsHandler;

    }
}
