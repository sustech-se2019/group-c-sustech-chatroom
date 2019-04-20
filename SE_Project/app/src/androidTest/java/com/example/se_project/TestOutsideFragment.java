package com.example.se_project;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import android.support.v4.app.Fragment;
import android.support.test.rule.ActivityTestRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import android.support.test.annotation.UiThreadTest;
import android.view.WindowManager;

//
public class TestOutsideFragment{
    @Rule
    public ActivityTestRule<ChatActivity> rule = new ActivityTestRule<ChatActivity>(ChatActivity.class);

    @Test
    public void newInstanceClassType() {
        Assert.assertSame(OutsideFragment.class,OutsideFragment.newInstance().getClass());

    }

    @Test
    public void newInstanceSuperclassType() {
        Assert.assertSame(Fragment.class,OutsideFragment.newInstance().getClass().getSuperclass());
    }

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

    @Test
    public void onCreateViewHasClickListener() {
        OutsideFragment outsideFragment = (OutsideFragment) rule.getActivity().getSupportFragmentManager().findFragmentById(R.id.outside);
        Assert.assertTrue(outsideFragment.getView().findViewById(R.id.entry).hasOnClickListeners());
    }

    @Test
    @UiThreadTest
    public void onCreateViewPerformClick() {
        OutsideFragment outsideFragment = (OutsideFragment) rule.getActivity().getSupportFragmentManager().findFragmentById(R.id.outside);
        Assert.assertTrue(outsideFragment.getView().findViewById(R.id.entry).performClick());
        Assert.assertEquals(1024,rule.getActivity().getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}