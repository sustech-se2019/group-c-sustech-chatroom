package com.example.se_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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
        User user1 = new User("a", 1);
        userList.add(user1);
        User user2 = new User("b", 2);
        userList.add(user2);
        User user3 = new User("c", 3);
        userList.add(user3);
    }
    private void searchFriendByName(String name){
    }
}