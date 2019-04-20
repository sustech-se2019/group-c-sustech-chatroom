package com.example.se_project;

import android.support.test.rule.ActivityTestRule;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {
    
    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<MainActivity>(MainActivity.class);
    
    @Test
    public void onClickLoginSuccess() {
        MainActivity mainActivity = rule.getActivity();
        Button login, register;
        TextView login_username, login_password;
        login = mainActivity.findViewById(R.id.login_button);
        register = mainActivity.findViewById(R.id.register_button);
        login_username = mainActivity.findViewById(R.id.username);
        login_password = mainActivity.findViewById(R.id.password);
        login_username.setText("test");
        login_password.setText("123456");
        login.performClick();
        Assert.assertNull(mainActivity.alertdialog1);
    }

    @Test
    public void onClickLoginFail() {
        MainActivity mainActivity = rule.getActivity();
        Button login, register;
        TextView login_username, login_password;
        login = mainActivity.findViewById(R.id.login_button);
        register = mainActivity.findViewById(R.id.register_button);
        login_username = mainActivity.findViewById(R.id.username);
        login_password = mainActivity.findViewById(R.id.password);
        login_username.setText("test");
        login_password.setText("1234567");
        login.performClick();
        Assert.assertTrue(mainActivity.alertdialog1.isShowing());
    }



}