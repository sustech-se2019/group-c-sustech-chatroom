package com.example.se_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
    private Button moment;
    private UserAdapter adapter;
    private List<User> userList = AppData.getInstance().getFriendList();
    private TextView NickName;
    private ImageView Portrait;

    protected void onStart(){
        super.onStart();
        refreshView();
    }

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.friend_activity);
        //initFriends();//初始化消息数据
        adapter = new UserAdapter(FriendActivity.this, R.layout.friend_list_layout, userList);
        search = (Button)findViewById(R.id.search_friendlist);
        add = (Button)findViewById(R.id.add_activity);
        userListView = (ListView)findViewById(R.id.friend_list_view);
        moment=(Button)findViewById(R.id.moments);
        userListView.setAdapter(adapter);
        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(FriendActivity.this,ChatActivity.class);
                User chatUser=(User)userListView.getItemAtPosition(position);
                intent.putExtra("ChatUser",chatUser);
                AppData.getInstance().setChattingFriend(chatUser);
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
        moment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendActivity.this,MomentsActivity.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendActivity.this,FriendAddActivity.class);
                startActivity(intent);
            }
        });

        User me  = AppData.getInstance().getMe();
        String name = me.getNickName();
        int portait = me.getProfilePictureID();

        NickName = findViewById(R.id.user_name2);
        Portrait = findViewById(R.id.user_portait);

        NickName.setText(name);
        Portrait.setImageResource(portait);


        AppData.getInstance().setFriendHandler(handler);
    }


    public void refreshView(){
        adapter.notifyDataSetChanged();//当有消息时刷新
    }

    /**
     * Handle the chat in message in {@link JSONObject} type.
     */
    @SuppressLint("HandlerLeak")
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            JSONObject result = (JSONObject)msg.obj;
            switch (result.getIntValue("status")) {
                case 800:
                    refreshView();
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }



    private void searchFriendByName(String name){
    }


    private void startChat(User user){
        Intent intent = new Intent(FriendActivity.this,ChatActivity.class);
        startActivity(intent);
    }
}
