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
/**
 * Main activity of the application.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    private Button login, register,ensure_register;
    private TextView login_username, login_password;
    AlertDialog alertdialog1;
    @Override
    /**
     * {@inheritDoc}
     */
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
    /**
     * To show wrong password dialog.
     */
    private void showdialog_wrong_password()
    {
        //Toast.makeText(this,"clickme",Toast.LENGTH_LONG).show();
        AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(this);
        alertdialogbuilder.setMessage("密码错误");
        alertdialogbuilder.setPositiveButton("确定", click1);
        alertdialog1=alertdialogbuilder.create();
        alertdialog1.show();
    }
    /**
     * To show username not exist dialog.
     */
    private void showdialog_no_username()
    {
        //Toast.makeText(this,"clickme",Toast.LENGTH_LONG).show();
        AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(this);
        alertdialogbuilder.setMessage("用户名不存在");
        alertdialogbuilder.setPositiveButton("确定", click1);
        alertdialog1=alertdialogbuilder.create();
        alertdialog1.show();
    }
    /**
     * When click "确定",cancle dialog.
     */
    private DialogInterface.OnClickListener click1=new DialogInterface.OnClickListener()
    {@Override

    public void onClick(DialogInterface arg0,int arg1)
    {
        arg0.cancel();
    }
    };

    @Override
    /**
     * {@inheritDoc}
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                //login in
                String usname = login_username.getText().toString();
                String psword = login_password.getText().toString();
                login(usname,psword);
                break;
            case R.id.register_button:
                //register
               Intent intent = new Intent();
               intent.setClass(MainActivity.this, RegisterActivity.class);
               MainActivity.this.startActivity(intent);
               break;
        }
    }
    /**
     * Handle the login in message in {@link JSONObject} type.
     */
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
                    break;
                default:
                    //登陆失败
                    showdialog_wrong_password();
                    Log.d("result", result.getString("msg"));

                    break;
            }
        }
    };
  
    /**
     * Get information of login and send to server.
     */
    private void login(final String username, final String password ) {
        final String request_url = this.getString(R.string.IM_Server_Url) + "/login";
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                try {
                    JSONObject json_data = new JSONObject();
                    json_data.put("username", username);
                    json_data.put("password", password);

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
