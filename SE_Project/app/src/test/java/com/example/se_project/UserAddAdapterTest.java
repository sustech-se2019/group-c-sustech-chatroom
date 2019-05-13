package com.example.se_project;

import org.junit.Test;


import static org.junit.Assert.*;
public class UserAddAdapterTest {
    @Test
    public void testUserAddGetViewLayer(){
        try {
            Class<?> clazz = UserAddAdapter.class;// 获取PrivateClass整个类
            UserAddAdapter userAddAdapter = (UserAddAdapter) clazz.newInstance();// 创建一个实例
            int position = 0;
            UserAddAdapter.ViewHolder vh = (UserAddAdapter.ViewHolder)userAddAdapter.getView(position,null,null).getTag();
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
    public void testUserAddGetViewName(){
        try {
            Class<?> clazz = UserAddAdapter.class;// 获取PrivateClass整个类
            UserAddAdapter userAddAdapter = (UserAddAdapter) clazz.newInstance();// 创建一个实例
            int position = 0;
            User user = userAddAdapter.getItem(position);
            UserAddAdapter.ViewHolder vh = (UserAddAdapter.ViewHolder)userAddAdapter.getView(position,null,null).getTag();
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
