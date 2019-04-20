package com.example.se_project;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.view.WindowManager;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChatActivityTest {

    @Rule
    public ActivityTestRule<ChatActivity> rule = new ActivityTestRule<ChatActivity>(ChatActivity.class);


    @Test
    @UiThreadTest
    public void testOnBackPressedRtn(){

        OutsideFragment outsideFragment = (OutsideFragment) rule.getActivity().getSupportFragmentManager().findFragmentById(R.id.outside);
        Assert.assertTrue(outsideFragment.onBackPressed());
    }


    @Test
    @UiThreadTest
    public void testOnBackPressedFlags(){
        OutsideFragment outsideFragment = (OutsideFragment) rule.getActivity().getSupportFragmentManager().findFragmentById(R.id.outside);
        outsideFragment.onBackPressed();
        Assert.assertEquals(0,rule.getActivity().getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}