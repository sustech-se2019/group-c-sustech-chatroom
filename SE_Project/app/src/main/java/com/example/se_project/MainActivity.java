package com.example.se_project;

import android.os.Debug;
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
                setContentView(R.layout.register);
                regist("789","789",4.0);
                break;
//            case R.id.ensure_button:
////                register
//                setContentView(R.layout.activity_main);
//
//                break;
        }
    }

    private void login(final String username, final String password ) {
        final String request_url = this.getString(R.string.IM_Server_Url) + "/login";
        new Thread(new Runnable() {
            @Override
            public void run() {
                                try {
                                    URL url = new URL(request_url);
                                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                    connection.setRequestMethod("POST");
                                    connection.setDoOutput(true);
                                    connection.setDoInput(true);
                                    connection.setUseCaches(false);
                                    connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");//设置参数类型是json格式
                                    connection.connect();

                                    String body = "{\"username\":" + username + ",\"password\":" + password + "}";
                                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                                    writer.write(body);
                                    writer.close();

                                    int responseCode = connection.getResponseCode();
                                    InputStream inputStream = connection.getInputStream();
                                    String result = inputStream2String(inputStream);//将流转换为字符串。
                                    JSONObject result_json = JSONObject.parseObject(result);

                                    if (result_json.getIntValue("status") == HttpURLConnection.HTTP_OK) {

                                        Log.d("result: ", result_json.getString("data"));

                                    } else {
                                        Log.d("result: ", result_json.getString("msg"));
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
            }
        }).start();

    }

    private void regist(final String username, final String password, final double gpa) {
        final String request_url = this.getString(R.string.IM_Server_Url) + "/regist";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(request_url);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");//设置参数类型是json格式
                    connection.connect();

                    String body = "{\"username\":" + username + ",\"password\":" + password + ",\"gpa\":" + gpa + "}";
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                    writer.write(body);
                    writer.close();

                    int responseCode = connection.getResponseCode();
                    InputStream inputStream = connection.getInputStream();
                    String result = inputStream2String(inputStream);//将流转换为字符串。
                    JSONObject result_json = JSONObject.parseObject(result);

                    if (result_json.getIntValue("status") == HttpURLConnection.HTTP_OK) {

                        Log.d("result: ", result_json.getString("data"));

                    } else {
                        Log.d("result: ", result_json.getString("msg"));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public String inputStream2String (InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;) {
            out.append(new String(b,0,n));
        }
        return out.toString();
    }
}
