package com.example.se_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;

import com.alibaba.fastjson.JSONObject;

import org.w3c.dom.Text;

import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    private Button login, register,ensure_register;
    private TextView login_username, login_password,  register_username, register_password, register_repassword, register_GPA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login_button);
        register = findViewById(R.id.register_button);
        //ensure_register = findViewById(R.id.ensure_button);


        login_username = findViewById(R.id.username);
        login_password = findViewById(R.id.password);


        login.setOnClickListener(this);
        register.setOnClickListener(this);
        //ensure_register.setOnClickListener(this);


    }

    public void showdialog_wrong_password()
    {
        //Toast.makeText(this,"clickme",Toast.LENGTH_LONG).show();
        AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(this);
        alertdialogbuilder.setMessage("密码错误");
        alertdialogbuilder.setPositiveButton("确定", click1);
        AlertDialog alertdialog1=alertdialogbuilder.create();
        alertdialog1.show();
    }

    public void showdialog_no_username()
    {
        //Toast.makeText(this,"clickme",Toast.LENGTH_LONG).show();
        AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(this);
        alertdialogbuilder.setMessage("用户名不存在");
        alertdialogbuilder.setPositiveButton("确定", click1);
        AlertDialog alertdialog1=alertdialogbuilder.create();
        alertdialog1.show();
    }

    private DialogInterface.OnClickListener click1=new DialogInterface.OnClickListener()
    {@Override

    public void onClick(DialogInterface arg0,int arg1)
    {
        arg0.cancel();
    }
    };









    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                //login in
                String usname = login_username.getText().toString();
                String psword = login_username.getText().toString();
                login(usname,psword);
                Intent intent1 = new Intent();
                intent1.setClass(MainActivity.this, ChatActivity.class);
                MainActivity.this.startActivity(intent1);

                break;
            case R.id.register_button:
                //register
               Intent intent = new Intent();
               intent.setClass(MainActivity.this, RegisterActivity.class);
               MainActivity.this.startActivity(intent);
                //Intent reg = new Intent(MainActivity.this, RegisterActivity.this);


                break;

        }
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            JSONObject result = (JSONObject)msg.obj;
            Intent intent = new Intent();
            switch (result.getIntValue("status")) {
                case HttpURLConnection.HTTP_OK:
                    //登录成功

                    Intent intent1 = new Intent();
                    intent1.setClass(MainActivity.this, ChatActivity.class);
                    MainActivity.this.startActivity(intent1);
                    Log.d("result: ", result.getString("data"));

                    intent.setClass(MainActivity.this, ChatActivity.class);
                    MainActivity.this.startActivity(intent);
                    break;
                default:
                    //登陆失败
                    showdialog_wrong_password();
                    Log.d("result: ", result.getString("msg"));
//                    intent.setClass(MainActivity.this, ChatActivity.class);
//                    MainActivity.this.startActivity(intent);
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
