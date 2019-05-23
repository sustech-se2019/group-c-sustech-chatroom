package com.example.se_project.Chat;

import android.util.JsonReader;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.se_project.AppData;
import com.example.se_project.MainActivity;
import com.example.se_project.Utils;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class WSClient extends WebSocketClient {

//    CONNECT(1, "第一次(或重连)初始化连接"),
//    CHAT(2, "聊天消息"),
//    SIGNED(3, "消息签收"),
//    KEEPALIVE(4, "客户端保持心跳"),
//    PULL_FRIEND(5, "拉取好友");

    public static final int CONNECT = 1;//第一次(或重连)初始化连接
    public static final int CHAT = 2;//聊天消息
    public static final int SIGNED = 3;//消息签收
    public static final int KEEPALIVE = 4;//客户端保持心跳
    public static final int PULL_FRIEND = 5;//拉取好友

    public WSClient(URI serverURI ) {
        super( serverURI );
    }

    private String myId = AppData.getInstance().getMe().getId();
    private int heartBeatTime = 10000;

    @Override
    public void onOpen( ServerHandshake handshakedata ) {

        JSONObject chatMsg = Utils.getChatMsg(myId,"","");
        JSONObject jsonMsg = Utils.getJsonMsg(CONNECT, chatMsg, null);
        System.out.println("Send onOpen Msg: " + jsonMsg);
        send(jsonMsg.toString());
        startHeartBeat();
        // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
    }

    @Override
    public void onMessage( String message ) {
        JSONObject json = JSONObject.parseObject(message);
        if (json.getIntValue("action") == WSClient.CHAT)
        {
            JSONObject chatMsg = JSONObject.parseObject(json.getString("chatMsg"));
            if (chatMsg.getString("receiverId").equals(myId))
            {
                AppData.getInstance().reciveChatMsg(chatMsg.getString("senderId"),
                                                    chatMsg.getString("msg"),
                                                    chatMsg.getString("msgId"));
                List<String> list = new ArrayList<>();
                list.add(chatMsg.getString("msgId"));
                AppData.getInstance().getWsClient().signMsg(list);
            }
        }
        System.out.println(myId + " received: " + message );
    }

    @Override
    public void onClose( int code, String reason, boolean remote ) {
        // The codecodes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println( "Connection closed by " + ( remote ? "remote peer" : "us" ) + " Code: " + code + " Reason: " + reason );
    }

    @Override
    public void onError( Exception ex ) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

    private int checkConnection(){
        if (isClosed())
        {
            try{
                URI Uri = new URI("ws://10.21.72.100:8088/ws");
                AppData.getInstance().setWsClient(new WSClient(Uri));
                AppData.getInstance().getWsClient().connect();
                Log.d("creadWebSocket","finish");
                return 0;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return 1;
    }

    /**
     * Get information of login and send to server.
     */
    private void startHeartBeat( ) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while(true){
                        Log.d("startHeartBeat","");
                        Thread.sleep(heartBeatTime);
                        if (checkConnection() == 1)
                            sendPing();
                        else
                            break;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }


    public void sendMsg(String friendId, String msg ){

        JSONObject chatMsg = Utils.getChatMsg(myId,friendId,msg);
        JSONObject jsonMsg = Utils.getJsonMsg(CHAT, chatMsg, "null");
        System.out.println("Send Msg: " + jsonMsg);
        checkConnection();
        send(jsonMsg.toString());

    }

    public void signMsg(List<String> list){
        JSONArray msgIdList = new JSONArray();
        msgIdList.addAll(list);
        JSONObject chatMsg = Utils.getChatMsg(myId,null,"");
        JSONObject jsonMsg = Utils.getJsonMsg(SIGNED, chatMsg, msgIdList.toString());
        System.out.println("Send Msg: " + jsonMsg);
        checkConnection();
        send(jsonMsg.toString());
    }

}
