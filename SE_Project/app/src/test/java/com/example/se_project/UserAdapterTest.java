package com.example.se_project;
import android.widget.ArrayAdapter;

import org.junit.Test;


import static org.junit.Assert.*;

public class UserAdapterTest {
    @Test
    public void testUserGetViewLayer(){
        try {
            Class<?> clazz = UserAdapter.class;// 获取PrivateClass整个类
            UserAdapter userAdapter = (UserAdapter) clazz.newInstance();// 创建一个实例
            int position = 0;
            User user = userAdapter.getItem(position);
            UserAdapter.ViewHolder vh = (UserAdapter.ViewHolder)userAdapter.getView(position,null,null).getTag();
            vh.button.performClick();
            assertEquals(0,vh.layout.getVisibility());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testUserGetViewName(){
        try {
            Class<?> clazz = UserAdapter.class;// 获取PrivateClass整个类
            UserAdapter userAdapter = (UserAdapter) clazz.newInstance();// 创建一个实例
            int position = 0;
            User user = userAdapter.getItem(position);
            UserAdapter.ViewHolder vh = (UserAdapter.ViewHolder)userAdapter.getView(position,null,null).getTag();
            vh.button.performClick();
            assertEquals(user.getName().toString(),vh.name.getText().toString());
        } catch (InstantiationException | NullPointerException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }
}
