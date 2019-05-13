package com.example.se_project;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FriendAddActivity extends AppCompatActivity {
    private ListView userListView;
    private EditText inputText;
    private Button search;
    private Button back;
    private Button add;
    private UserAddAdapter adapter;
    private List<User> userList = new ArrayList<User>();
    AlertDialog alertdialog1;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.friend_add_activity);
        initFriends();//初始化消息数据
        adapter = new UserAddAdapter(FriendAddActivity.this, R.layout.friend_add_layout, userList);
        inputText = (EditText)findViewById(R.id.input_user_name);
        search = (Button)findViewById(R.id.search_user);
        add = (Button)findViewById(R.id.add_request);
        userListView = (ListView)findViewById(R.id.user_list_view);
        userListView.setAdapter(adapter);

        //发送按钮的点击事件
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                searchUserByName(content);    //update userList
                adapter = new UserAddAdapter(FriendAddActivity.this, R.layout.friend_add_layout, userList);
                userListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();//当有消息时刷新
                userListView.setSelection(0);//将ListView定位到最后一行
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void initFriends(){
        User user1 = new User("a", 1);
        userList.add(user1);
        User user2 = new User("b", 2);
        userList.add(user2);
        User user3 = new User("c", 3);
        userList.add(user3);
        User user4 = new User("d", 4);
        userList.add(user4);
    }

    void showdialogMsg(String msg)
    {
        //Toast.makeText(this,"clickme",Toast.LENGTH_LONG).show();
        AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(this);
        alertdialogbuilder.setMessage(msg);
        alertdialogbuilder.setPositiveButton("确定", click1);
        alertdialog1=alertdialogbuilder.create();
        alertdialog1.show();
    }
    private final DialogInterface.OnClickListener click1=new DialogInterface.OnClickListener()
    {@Override

    public void onClick(DialogInterface arg0,int arg1)
    {
        arg0.cancel();
    }
    };

    private void reflesh(){
        adapter = new UserAddAdapter(FriendAddActivity.this, R.layout.friend_add_layout, userList);
        userListView.setAdapter(adapter);
    }


    @SuppressLint("HandlerLeak")
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            JSONObject result = (JSONObject)msg.obj;


            if(!result.getString("status").equals("200")){
                showdialogMsg(result.getString("msg"));
            }else{
                User user = new User();
                user.setId(JSONObject.parseObject(result.getString("data")).getString("id"));
                user.setGpa(Double.parseDouble(JSONObject.parseObject(result.getString("data")).getString("gpa")));
                user.setName(JSONObject.parseObject(result.getString("data")).getString("username"));
                userList.clear();
                userList.add(user);
                Log.d("加入", user.getName());
                reflesh();
            }

        }
    };

    private void searchUserByName(String name){
        final String request_url = this.getString(R.string.IM_Server_Url) + "/search?myUserId="+AppData.getInstance().getMe()
                +"&friendUsername="+name;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                try {
                    JSONObject json_data = new JSONObject();
                    message.obj = HttpRequest.jsonRequest(request_url, json_data);
                    JSONObject result = (JSONObject)message.obj;

                    handler.sendMessage(message);

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
}
