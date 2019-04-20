package com.example.se_project;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import java.net.HttpURLConnection;
/**
 * {@link AppCompatActivity} is an activity type. This class used to control Register activity.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register_username, register_password, register_repassword, register_GPA;
    private Button ensure;
    private String usname, psword, repsword;
    /**
     * The Gpa of a student.
     */
    double gpa;
    /**
     * The alert dialog.
     */
    AlertDialog alertdialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_username = findViewById(R.id.username);
        register_password = findViewById(R.id.password);
        register_repassword = findViewById(R.id.password_ensure);
        register_GPA = findViewById(R.id.gpa);

        ensure = findViewById(R.id.ensure_button);
        ensure.setOnClickListener(this);
    }
    /**
     * If the second password is different from the first one, it will show a different password {@link AlertDialog}.
     */
    private void showdialog_dif_password() {
        //Toast.makeText(this,"clickme",Toast.LENGTH_LONG).show();
        AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(this);
        alertdialogbuilder.setMessage("两次密码不一致");
        alertdialogbuilder.setPositiveButton("确定", click1);
        alertdialog1 = alertdialogbuilder.create();
        alertdialog1.show();
    }
    /**
     * If the user name is already exist, it will show a duplicate user name {@link AlertDialog}.
     */
    private void showdialog_exit_username() {
        //Toast.makeText(this,"clickme",Toast.LENGTH_LONG).show();
        AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(this);
        alertdialogbuilder.setMessage("用户名已存在");
        alertdialogbuilder.setPositiveButton("确定", click1);
        alertdialog1 = alertdialogbuilder.create();
        alertdialog1.show();
    }

    /**
     * If app fail to connect to server , it will show a connect fail {@link AlertDialog}.
     */
    private void showdialog_fail_connect() {
        //Toast.makeText(this,"clickme",Toast.LENGTH_LONG).show();
        AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(this);
        alertdialogbuilder.setMessage("连接服务器失败");
        alertdialogbuilder.setPositiveButton("确定", click1);
        alertdialog1 = alertdialogbuilder.create();
        alertdialog1.show();
    }
    /**
     * If click "确定", {@link AlertDialog} will be canceled.
     */
    private DialogInterface.OnClickListener click1 = new DialogInterface.OnClickListener() {
        @Override

        public void onClick(DialogInterface arg0, int arg1) {
            arg0.cancel();
        }
    };
 
    /**
     * To handle with the register message of {@link JSONObject}.
     */
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            JSONObject result = (JSONObject) msg.obj;
            switch (result.getIntValue("status")) {
                case HttpURLConnection.HTTP_OK:
                    //注册成功
                    Intent intent = new Intent();//场景切换
                    intent.setClass(RegisterActivity.this, MainActivity.class);
                    RegisterActivity.this.startActivity(intent);

                    Log.d("result: ", result.getString("data"));
                    break;
                case HttpURLConnection.HTTP_INTERNAL_ERROR:
                    Log.d("result: ", result.getString("msg"));
                    showdialog_exit_username();
                    break;
                default:
                    //注册失败
                    Log.d("result: ", result.getString("msg"));
                    showdialog_fail_connect();
                    break;
            }
        }
    };
    /**
     * Get the register information and regist it in server...
     */
    private void regist(final String username, final String password, final double gpa) {
        final String request_url = this.getString(R.string.IM_Server_Url) + "/regist";
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                try {

                    JSONObject json_data = new JSONObject();
                    json_data.put("username", username);
                    json_data.put("password", password);
                    json_data.put("gpa", gpa);

                    message.obj = HttpRequest.jsonRequest(request_url, json_data);
                    handler.sendMessage(message);

                } catch (Exception e) {
                    JSONObject result_json = new JSONObject();
                    result_json.put("status", 0);
                    result_json.put("msg", "连接服务器失败...");
                    message.obj = result_json;
                    handler.sendMessage(message);
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ensure_button:
                usname = register_username.getText().toString();
                psword = register_password.getText().toString();
                repsword = register_repassword.getText().toString();
                gpa = Double.parseDouble(register_GPA.getText().toString());
                if(psword.equals(repsword))
                    regist(usname, psword, gpa);
                else {
                    Log.d("PASSWORD: ", psword);
                    Log.d("PASSWORD2: ", repsword);
                    showdialog_dif_password();
                }
        }
    }
}
