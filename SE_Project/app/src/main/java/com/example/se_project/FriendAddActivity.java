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

public class FriendAddActivity extends AppCompatActivity {
    private ListView userListView;
    private EditText inputText;
    private Button search;
    private Button back;
    private UserAddAdapter adapter;
    private List<User> userList = new ArrayList<User>();

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.friend_add_activity);
        initUsers();//初始化消息数据
        adapter = new UserAddAdapter(FriendAddActivity.this, R.layout.friend_add_layout, userList);
        inputText = (EditText)findViewById(R.id.input_user_name);
        search = (Button)findViewById(R.id.search_user);
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
        Intent intent = new Intent(FriendAddActivity.this,FriendActivity.class);
        startActivity(intent);
        finish();
    }

    private void initUsers(){
        User user1 = new User("a", 1);
        userList.add(user1);
        User user2 = new User("b", 2);
        userList.add(user2);
        User user3 = new User("c", 3);
        userList.add(user3);
        User user4 = new User("d", 4);
        userList.add(user4);
    }
    private void searchUserByName(String name){
    }
}