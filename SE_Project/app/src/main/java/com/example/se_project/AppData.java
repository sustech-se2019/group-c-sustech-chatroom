package com.example.se_project;


import com.example.se_project.Chat.WSClient;

public class AppData {

    private static AppData singletonData = new AppData();

    public static AppData getInstance(){
        return singletonData;
    }

    private User me = new User();
    private WSClient ws;

    public User getMe() {
        return me;
    }

    public WSClient getWs() {
        return ws;
    }

    public void setWs(WSClient ws) {
        this.ws = ws;
    }

    public void setMe(User me) {
        this.me = me;
    }
}
