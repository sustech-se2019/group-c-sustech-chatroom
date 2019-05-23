package com.example.se_project;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Utils {

    /**
     * 判定输入的是否是英文
     *
     * @param ch
     *  被校验的字符
     * @return true代表是英文
     */
    //英文占1byte，非英文（可认为是中文）占2byte，根据这个特性来判断字符
    public static boolean checkChar(char ch) {
        if ((ch + "").getBytes().length == 1) {
            return true;//英文
        } else {
            return false;//不是英文
        }
    }

    public static boolean checkString(String str) {
        if (str != null) {
            for (int i = 0; i < str.length(); i++) {
                if (!checkChar(str.charAt(i))) {
                    return false;//不是英文
                }
            }
        }
        return true;//英文
    }

    public static JSONObject getJsonMsg(int action, JSONObject chatMsg, String extand){
        JSONObject json = new JSONObject();
        json.put("action",action);
        json.put("chatMsg",chatMsg);
        json.put("extand",extand);
        return json;
    }

    public static JSONObject getChatMsg(String senderId, String receiverId, JSONArray msg){
        JSONObject json = new JSONObject();
        json.put("msg",msg);
        json.put("receiverId",receiverId);
        json.put("senderId",senderId);
        return json;
    }

    public static JSONObject getChatMsg(String senderId, String receiverId, String msg) {

        JSONObject json = new JSONObject();
        json.put("msg",msg);
        json.put("receiverId",receiverId);
        json.put("senderId",senderId);

        return json;
    }

}
