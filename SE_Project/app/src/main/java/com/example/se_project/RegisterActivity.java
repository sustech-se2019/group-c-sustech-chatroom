package com.example.se_project;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;

import java.net.HttpURLConnection;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        regist("789","789",4.0);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            JSONObject result = (JSONObject)msg.obj;
            switch (result.getIntValue("status")) {
                case HttpURLConnection.HTTP_OK:
                    //注册成功
                    Log.d("result: ", result.getString("data"));
                    break;
                default:
                    Log.d("result: ", result.getString("msg"));
                    break;
            }
        }
    };

    private void regist(final String username, final String password, final double gpa) {
        final String request_url = this.getString(R.string.IM_Server_Url) + "/regist";
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                try {
                    String data = "{\"username\":" + username + ",\"password\":" + password + ",\"gpa\":" + gpa + "}";
                    JSONObject json_data = JSONObject.parseObject(data);

                    message.obj = HttpRequest.jsonRequest(request_url, json_data);;
                    handler.sendMessage(message);

                } catch (Exception e) {
                    JSONObject result_json = new JSONObject();
                    result_json.put("status",0);
                    result_json.put("msg","连接服务器失败...");
                    message.obj = result_json;
                    handler.sendMessage(message);
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
