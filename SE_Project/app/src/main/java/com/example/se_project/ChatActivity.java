package com.example.se_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cpj on 2016/3/15.
 */
public class ChatActivity extends AppCompatActivity {



    protected static final int CONTEXTMENU_DELETEITEM = 0;
    protected static final int CONTEXTMENU_TRANSLATION = 1;

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
        registerForContextMenu(msgListView);

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

        initListView();
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


    private void refreshFavListItems() {
        adapter = new MsgAdapter(ChatActivity.this, R.layout.msg_layout, msgList);
        msgListView.setAdapter(adapter);
    }



    private void initListView() {
        /* Loads the items to the ListView. */

        refreshFavListItems();

        /* Add Context-Menu listener to the ListView. */
        msgListView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

            public void onCreateContextMenu(ContextMenu conMenu, View view , ContextMenuInfo info) {
                //ChatActivity.super.onCreateContextMenu(conMenu,view,info);
               // AdapterView.AdapterContextMenuInfo am = (AdapterView.AdapterContextMenuInfo) info;

                conMenu.add(0, 0, 0, "删除");
                conMenu.add(1, 1, 1, "翻译");


                /* Add as many context-menu-options as you want to. */
            }
        });


      //暂时用不到，显示点击的内容
      /*  msgListView.setOnItemClickListener(new OnItemClickListener()
        {
            @SuppressLint("WrongConstant")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(ChatActivity.this, "111111111111", 200).show() ;
            }
        });*/


    }



    @Override
    public boolean onContextItemSelected(MenuItem aItem) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) aItem.getMenuInfo();

        /* Switch on the ID of the item, to get what the user selected. */
        switch (aItem.getItemId()) {
            case CONTEXTMENU_DELETEITEM:{
                /* Get the selected item out of the Adapter by its position. */
                int favContexted = (int) msgListView.getAdapter()
                        .getItemId(menuInfo.position);
                /* Remove it from the list.*/
                msgList.remove(favContexted);

                refreshFavListItems();

                return true; /* true means: "we handled the event". */
            }
            case CONTEXTMENU_TRANSLATION: {
                //请求翻译
                int pos = menuInfo.position;
                String content = msgList.get(pos).getContent(); //待翻译内容
                //Log.d("内容",content);

                //得到翻译内容：
                String translated = "哈哈哈";
                msgList.get(pos).setContent(translated);
                refreshFavListItems();
            }

        }
        return false;
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

    /**
     * Get information of translate and send to NMT server.
     *//*
    void translate(final String sentence) {
        final String server_url = this.getString(R.string.NMT_Server_Url);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                String request_url = server_url;
                if (Utils.checkString(sentence))
                {
                    request_url += "/en2zh";
                }else{
                    request_url += "/zh2en";
                }
                try {
                    JSONObject json_data = new JSONObject();
                    json_data.put("sentence", sentence);

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

    }*/






}