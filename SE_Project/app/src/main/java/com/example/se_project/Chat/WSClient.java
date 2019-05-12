package com.example.se_project.Chat;

import com.alibaba.fastjson.JSONObject;
import com.example.se_project.AppData;
import com.example.se_project.Utils;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class WSClient extends WebSocketClient {

    public WSClient(URI serverURI ) {
        super( serverURI );
    }

    private String sendId = AppData.getInstance().getMe().getId();
    private int heartBeatTime = 10000;

    @Override
    public void onOpen( ServerHandshake handshakedata ) {

        JSONObject chatMsg = Utils.getChatMsg(sendId,"","");
        JSONObject jsonMsg = Utils.getJsonMsg(1, chatMsg, null);
        System.out.println("Send onOpen Msg: " + jsonMsg);
        send(jsonMsg.toString());
        // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
    }

    @Override
    public void onMessage( String message ) {
        System.out.println(sendId + " received: " + message );
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

    private Runnable heartBeat = new Runnable() {
        @Override
        public void run(){
            try{
                Thread.sleep(heartBeatTime);
                sendPing();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    };


    public void sendMsg( String msg ){

        JSONObject chatMsg = Utils.getChatMsg(sendId,"1",msg);
        JSONObject jsonMsg = Utils.getJsonMsg(2, chatMsg, null);
        System.out.println("Send Msg: " + jsonMsg);
        send(jsonMsg.toString());
    }

}
