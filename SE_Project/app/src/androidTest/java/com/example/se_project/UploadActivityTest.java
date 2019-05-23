package com.example.se_project;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertNull;

public class UploadActivityTest {

    @Rule
    public ActivityTestRule<UploadActivity> rule = new ActivityTestRule<UploadActivity>(UploadActivity.class);

    @Test
    @UiThreadTest
    public void testOnClick1(){
        try{
            UploadActivity uploadActivity = rule.getActivity();View view = new View(uploadActivity);
            view.setId(R.id.choose_image);
            uploadActivity.onClick(view);
            Field field = UploadActivity.class.getDeclaredField("imgPath");
            field.setAccessible(true);
            String imp = (String)field.get(uploadActivity);
            assertNull(imp);
        }catch(NoSuchFieldException|IllegalAccessException e){
            e.printStackTrace();
        }
    }

/*   @Test
    @UiThreadTest
    public void testOnClick2(){
        try{
            UploadActivity uploadActivity = rule.getActivity();View view = new View(uploadActivity);
            view.setId(R.id.upload_image);
        }catch(NoSuchFieldException|IllegalAccessException e){
            e.printStackTrace();
        }
    }
    */


}
