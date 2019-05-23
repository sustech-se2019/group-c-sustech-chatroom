package com.example.se_project.Chat;

import com.alibaba.fastjson.JSONObject;
import com.example.se_project.AppData;
import com.example.se_project.Msg;
import com.example.se_project.R;

import java.util.Date;

public class ImageMsg extends Msg {
    private String SmallImage;
    private String BigImage;

    public ImageMsg(String content, int type, Date sendTime, String msgId) {
        super(content, type, sendTime, msgId);
        JSONObject image = JSONObject.parseObject(content);
        String serverUrl = AppData.getInstance().getContext().getString(R.string.FSFD_Server_Url);
        this.SmallImage = serverUrl + image.getString("SmallImage");
        this.BigImage = serverUrl + image.getString("BigImage");
    }

    public String getSmallImage() {
        return SmallImage;
    }

    public void setSmallImage(String smallImage) {
        SmallImage = smallImage;
    }

    public String getBigImage() {
        return BigImage;
    }

    public void setBigImage(String bigImage) {
        BigImage = bigImage;
    }
}
