package com.example.se_project;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MomentEditActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText inputMoments;
    private final MomentEditActivity momentEdit = this;
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.moments_send_activity);
        inputMoments = (EditText) findViewById(R.id.input_moments);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void onClick(View view) {
        if( view.getId() == R.id.send_moments ){
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
    }
}
