package com.example.se_project;

import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class User implements Serializable {
    private String name;
    private double gpa;
    private String id;
    private String nickName;
    private int potraitnum; //头像ID
    private int profilePictureID;
    private ArrayList<User> friendList=new ArrayList();
    private ArrayList<Moments> momentsList=new ArrayList<>();
    public User(){

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
        profilePictureID= R.drawable.gpa0;
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
        final String request_url = "http://10.21.72.100:8081" + "/addFriendRequest?myUserId="+AppData.getInstance().getMe().getId()+"&friendUsername="+user.getName();
        final String request_url_1 = "http://10.21.72.100:8081" + "/search?myUserId="+AppData.getInstance().getMe().getId()
                +"&friendUsername="+user.getName();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                try {
                    JSONObject json_data = new JSONObject();
                    message.obj = HttpRequest.jsonRequest(request_url, json_data);
                    JSONObject result = (JSONObject)message.obj;
                    Log.d("111    ",result.toString());
                    if(result.getInteger("status")!= 200){
                        Log.d("111    ",result.toString());
                        return;
                    }
                    Log.d("555    ",result.toString());


                    Message message1 = new Message();
                    JSONObject json_data1 = new JSONObject();
                    message.obj = HttpRequest.jsonRequest(request_url_1, json_data1);
                    JSONObject result1 = (JSONObject)message.obj;

                    Log.d("556     ",result1.toString());
                    String accept = JSONObject.parseObject(result1.getString("data")).getString("id");

                    Log.d("557     ",accept.toString());
                    String request_url_2 = "http://10.21.72.100:8081"+ "/operFriendRequest?acceptUserId="+accept
                            +"&sendUserId="+AppData.getInstance().getMe().getId()+"&operType=1";
                    Message message2 = new Message();
                    JSONObject json_data2 = new JSONObject();
                    message.obj = HttpRequest.jsonRequest(request_url_2, json_data2);
                    refreshFriendList();
                } catch (Exception e) {
                    JSONObject result_json = new JSONObject();
                    result_json.put("status",500);
                    result_json.put("msg","连接服务器失败...");
                    message.obj = result_json;
                    e.printStackTrace();
                }
            }
        }).start();
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

    private void refreshFriends(){
        if (AppData.getInstance().getFriendHandler() == null)
            return;
        Message message = new Message();
        JSONObject result_json = new JSONObject();
        result_json.put("status",800);
        message.obj = result_json;
        AppData.getInstance().getFriendHandler().sendMessage(message);
    }

    private void refreshFriendList(){
        final String request_url = "http://10.21.72.100:8081" + "/myFriends?userId="+AppData.getInstance().getMe().getId();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                try {
                    JSONObject json_data = new JSONObject();
                    message.obj = HttpRequest.jsonRequest(request_url, json_data);
                    JSONObject result = (JSONObject)message.obj;
                    Log.d("get friend list",result.toString());
                    JSONArray jsonArray = (JSONArray) JSONArray.parse(result.getString("data"));
                    friendList.clear();
                    for (Object item:jsonArray) {
                        JSONObject jsonItem = JSONObject.parseObject((String)item);
                        User user = new User();
                        user.setId(jsonItem.getString("id"));
                        user.setGpa(4.0);
                        user.setName(jsonItem.getString("friendUsername"));
                        friendList.add(user);
                    }
                    refreshFriends();
                } catch (Exception e) {
                    JSONObject result_json = new JSONObject();
                    result_json.put("status",500);
                    result_json.put("msg","连接服务器失败...");
                    message.obj = result_json;
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public boolean addGood(Moments moment){
        double a=Math.random();
        if(a>0.5){
            moment.addGood();
            return true;
        }else{
            return false;
        }
    }
    public Moments sendMoments(String text){
        Moments moment=null;
        double a=Math.random();
        if(a>0.5){
            moment=new Moments(this,text,0);
            this.momentsList.add(0,moment);
            return moment;
        }else{
            return moment;
        }
    }
    public List<Moments> getMomentsList(){
        return momentsList;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
