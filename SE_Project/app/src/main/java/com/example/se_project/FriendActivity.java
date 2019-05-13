package com.example.se_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FriendActivity extends AppCompatActivity {
    private ListView userListView;
    private EditText inputText;
    private Button search;
    private Button add;
    private UserAdapter adapter;
    private List<User> userList = new ArrayList<User>();

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.friend_activity);
        initFriends();//初始化消息数据
        adapter = new UserAdapter(FriendActivity.this, R.layout.friend_list_layout, userList);
        inputText = (EditText)findViewById(R.id.input_old_friend_name);
        search = (Button)findViewById(R.id.search_friendlist);
        add = (Button)findViewById(R.id.add_activity);
        userListView = (ListView)findViewById(R.id.friend_list_view);
        userListView.setAdapter(adapter);
        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(FriendActivity.this,ChatActivity.class);
                User chatUser=(User)userListView.getItemAtPosition(position);
                intent.putExtra("ChatUser",chatUser);
                startActivity(intent);
            }
        });
        //发送按钮的点击事件
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                searchFriendByName(content);    //update userList
                adapter = new UserAdapter(FriendActivity.this, R.layout.friend_list_layout, userList);
                userListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();//当有消息时刷新
                userListView.setSelection(0);//将ListView定位到最后一行
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendActivity.this,FriendAddActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initFriends(){
        final String request_url = this.getString(R.string.IM_Server_Url) + "/myFriends?userId="+AppData.getInstance().getMe();
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
                    userList = jsonArray.toJavaList(User.class);
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
    private void searchFriendByName(String name){
    }
    private void startChat(User user){
        Intent intent = new Intent(FriendActivity.this,ChatActivity.class);
        startActivity(intent);
    }
}
