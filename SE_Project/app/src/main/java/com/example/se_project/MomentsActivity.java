package com.example.se_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MomentsActivity extends AppCompatActivity {
    private ArrayAdapter adapter;
    private final List<Moments> momentsList=AppData.getInstance().getMe().getMomentsList();;
    AlertDialog alertdialog1;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.moments_activity);
        adapter = new MomentsAdapter(MomentsActivity.this, R.layout.moments_layout, momentsList);
        TextView userName = findViewById(R.id.user_name);
        ImageView portrait = findViewById(R.id.user_portrait);
        Button editMoments = findViewById(R.id.edit_moment);
        ListView momentsListView = (ListView)findViewById(R.id.moments_list_view);
        momentsListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //发送按钮的点击事件
        editMoments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MomentsActivity.this, MomentEditActivity.class);
                startActivity(intent);
            }
        });
        User me  = AppData.getInstance().getMe();
        String name = me.getNickName();
        int portraitID = me.getProfilePictureID();


        ImageView Portrait = findViewById(R.id.user_portait_moments);


        Portrait.setImageResource(portraitID);


    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
