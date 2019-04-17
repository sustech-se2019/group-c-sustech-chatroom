package com.example.se_project;

import android.content.Intent;
import android.os.Debug;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.alibaba.fastjson.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    private Button login, register,ensure_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login_button);
        register = findViewById(R.id.register_button);
        //ensure_register = findViewById(R.id.ensure_button);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
        //ensure_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                //login in
                login("123","123");
                break;
            case R.id.register_button:
                //register
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(intent);
                //Intent reg = new Intent(MainActivity.this, RegisterActivity.this);

                break;
//            case R.id.ensure_button:
////                register
//                setContentView(R.layout.activity_main);
//
//                break;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            JSONObject result = (JSONObject)msg.obj;
            switch (result.getIntValue("status")) {
                case HttpURLConnection.HTTP_OK:
                    //登录成功
                    Log.d("result: ", result.getString("data"));
                    break;
                default:
                    Log.d("result: ", result.getString("msg"));
                    break;
            }
        }
    };


    private void login(final String username, final String password ) {
        final String request_url = this.getString(R.string.IM_Server_Url) + "/login";
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                try {
                    String data = "{\"username\":" + username + ",\"password\":" + password + "}";
                    JSONObject json_data = JSONObject.parseObject(data);

                    message.obj = HttpRequest.jsonRequest(request_url, json_data);
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
