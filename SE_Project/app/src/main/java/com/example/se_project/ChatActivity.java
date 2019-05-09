package com.example.se_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by cpj on 2016/3/15.
 */
public class ChatActivity extends AppCompatActivity {

    private ListView msgListView;
    private EditText inputText;
    private Button send;
    private MsgAdapter adapter;
    private List<Msg> msgList = new ArrayList<Msg>();

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.chat_activity);

        initMsgs();//初始化消息数据
        adapter = new MsgAdapter(ChatActivity.this, R.layout.msg_layout, msgList);
        inputText = (EditText)findViewById(R.id.input_msg);
        send = (Button)findViewById(R.id.send_msg);
        msgListView = (ListView)findViewById(R.id.msg_list_view);
        msgListView.setAdapter(adapter);

        //发送按钮的点击事件
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if(!content.equals("")){
                    Msg msg;
                    msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyDataSetChanged();//当有消息时刷新
                    msgListView.setSelection(msgList.size());//将ListView定位到最后一行
                    inputText.setText("");//清空输入框的内容
                }
            }
        });
        findViewById(R.id.camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                view.findViewById(R.id.entry).setVisibility(View.GONE);
                getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.outside, CameraFragment.newInstance()).commit();
            }
        });

    }

    /**
     * 初始化消息数据
     * */
    private void initMsgs(){
        Msg msg1 = new Msg("Hello cpj.", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello Who is that?", Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("This is pengpeng,Nice talking to you.", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public void onBackPressed() {
        super.onBackPressed();
        this.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}