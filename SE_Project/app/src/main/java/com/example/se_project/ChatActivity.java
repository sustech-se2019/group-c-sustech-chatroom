package com.example.se_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
/**
 * A {@link AppCompatActivity} is a Activaty component and this class is chat window
 */
public class ChatActivity extends AppCompatActivity {
    /**
     * The Outside fragment.
     */
    private OutsideFragment outsideFragment = OutsideFragment.newInstance();

    @Override
    /**
     * {@inheritDoc}
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_main);
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
    /**
     * {@inheritDoc}
     */
    public void onBackPressed() {
//        super.onBackPressed();
        if(outsideFragment != null && outsideFragment.onBackPressed()){super.onBackPressed();}
    }

}
