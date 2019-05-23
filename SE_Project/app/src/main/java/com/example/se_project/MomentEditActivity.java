package com.example.se_project;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class MomentEditActivity extends AppCompatActivity{
    private Button sendMoments;
    private EditText inputMoments;
    private MomentEditActivity momentEdit=this;
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.moments_send_activity);
        sendMoments = (Button) findViewById(R.id.send_moments);
        inputMoments = (EditText) findViewById(R.id.input_moments);
        sendMoments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Moments moment=AppData.getInstance().getMe().sendMoments(inputMoments.getText().toString());
                if(moment==null){
                    AlertDialog alertDialog1 = new AlertDialog.Builder(momentEdit)
                            .setTitle("Warning")//标题
                            .setMessage("fail")//内容
                            .setIcon(R.mipmap.ic_launcher)//图标
                            .create();
                    alertDialog1.show();
                }else{
                    AlertDialog alertDialog1 = new AlertDialog.Builder(momentEdit)
                            .setTitle("Good News")//标题
                            .setMessage("success")//内容
                            .setIcon(R.mipmap.ic_launcher)//图标
                            .create();
                    alertDialog1.show();
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
