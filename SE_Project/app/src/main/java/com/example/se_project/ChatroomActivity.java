package com.example.se_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ChatroomActivity extends AppCompatActivity {

    private OutsideFragment outsideFragment = OutsideFragment.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        getSupportFragmentManager().beginTransaction().replace(R.id.outside, outsideFragment).commit();
//        outsideFragment.setOnButtonClick(new OutsideFragment.OnButtonClick() {
//            @Override
//            public void onClick(View view) {
//                getSupportActionBar().hide();
//                view.findViewById(R.id.entry).setVisibility(View.GONE);
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.outside, CameraFragment.newInstance()).addToBackStack(null).commit();
//            }
//        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if(outsideFragment != null && outsideFragment.onBackPressed()){super.onBackPressed();}
    }

}
