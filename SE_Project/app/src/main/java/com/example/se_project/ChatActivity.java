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
import android.widget.ImageView;
import android.widget.ListView;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.example.se_project.Chat.ChatHistory;

import java.io.File;
import java.util.Date;
import java.util.List;

import static com.example.se_project.R.id.Image;

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
    private List<Msg> msgList;
    private int pos;
    private User chatUser;
    private File mPhotoFile;
    private String mPhotoPath;
    private Button library;
    private Button camera;
    private ImageView mImageView;
    private CameraFragment cameraFragment;
    private static final int REQUEST_SYSTEM_PIC = 1;
    private static final int CAMERA_RESULT = 2;
    private TextView NickName;


    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        AppData.getInstance().setChatContext(this);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.chat_activity);
        chatUser=AppData.getInstance().getChattingFriend();
        initMsgs();//初始化消息数据
        adapter = new MsgAdapter(ChatActivity.this, R.layout.msg_layout, msgList,chatUser);
        inputText = (EditText)findViewById(R.id.input_msg);
        send = (Button)findViewById(R.id.send_msg);
        msgListView = (ListView)findViewById(R.id.msg_list_view);
        msgListView.setAdapter(adapter);
        registerForContextMenu(msgListView);


        User me  = AppData.getInstance().getMe();
        String name = me.getNickName();
        int portait = me.getProfilePictureID();

        NickName = findViewById(R.id.Chat_who);


        NickName.setText(name);


        camera = (Button)findViewById( R.id.camera );
        library = (Button) findViewById(Image);
        library.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            switch (v.getId()){
                case R.id.Image:
                    Intent intent = new Intent();
                    intent.setClass(ChatActivity.this, UploadActivity.class);
                    ChatActivity.this.startActivity(intent);
            }
            }
        });




        //发送按钮的点击事件
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if(!content.equals("")){
                    AppData.getInstance().sendChatMsg(content);

                    refreshView();

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
        AppData.getInstance().setChatHandler(handler);
    }


    public void refreshView(){
        adapter.notifyDataSetChanged();//当有消息时刷新
        msgListView.setSelection(msgList.size());//将ListView定位到最后一行
    }

    /**
     * Handle the login in message in {@link JSONObject} type.
     */
    @SuppressLint("HandlerLeak")
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            JSONObject result = (JSONObject)msg.obj;

            switch (result.getIntValue("status")) {
                case 200:
                    //Log.d("内容",content);
                    //得到翻译内容：
                    String translated = result.getString("data");
                    msgList.get(pos).setContent(translated);
                    refreshFavListItems();
                    break;
                case 500:
                    Log.d("result", result.getString("msg"));
                    break;
                case 800:
                    refreshView();
                    break;
                default:
                    Log.d("result", result.getString("msg"));
                    break;
            }
        }
    };

    /**
     * 初始化消息数据
     * */
    private void initMsgs(){
        ChatHistory history = AppData.getInstance().getChatHistory().get(chatUser.getId());
        if (history == null)
        {
            history = new ChatHistory();
            history.setFriendId(chatUser.getId());
            history.setMyId(AppData.getInstance().getMe().getId());
            history.setLastTime(new Date(System.currentTimeMillis()));
//            history.setMsgList(new ArrayList<Msg>());
            AppData.getInstance().getChatHistory().put(chatUser.getId(),history);
        }
        msgList = history.getMsgList();

    }


    private void refreshFavListItems() {
        adapter = new MsgAdapter(ChatActivity.this, R.layout.msg_layout, msgList,chatUser);
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
                pos = menuInfo.position;
                String content = msgList.get(pos).getContent(); //待翻译内容
                //Log.d("内容",content);
                translate(content);

            }

        }
        return false;
    }


    @Override
    /**
     * {@inheritDoc}
     */
    public void onBackPressed() {
        if (cameraFragment != null ) cameraFragment.onBackPressed();
        else finish();
    }

    /**
     * Get information of translate and send to NMT server.
     */
    private void translate(final String sentence) {
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
                    result_json.put("status",500);
                    result_json.put("msg","请求失败...");
                    message.obj = result_json;
                    handler.sendMessage(message);
                    e.printStackTrace();
                }
            }
        }).start();

    }


    public ListView getMsgListView() {
        return msgListView;
    }

    public void setMsgListView(ListView msgListView) {
        this.msgListView = msgListView;
    }

    public MsgAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(MsgAdapter adapter) {
        this.adapter = adapter;
    }



}