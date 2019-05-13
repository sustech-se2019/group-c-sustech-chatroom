package com.example.se_project;
import org.junit.Test;


import static org.junit.Assert.*;

public class MsgAdapterTest {
    @Test
    public void testMsgGetViewReceived(){
        try {
            Class<?> clazz = MsgAdapter.class;// 获取PrivateClass整个类
            MsgAdapter msgAdapter = (MsgAdapter) clazz.newInstance();// 创建一个实例
            MsgAdapter.ViewHolder vh = (MsgAdapter.ViewHolder)msgAdapter.getView(0,null,null).getTag();
            assertEquals(0,vh.leftLayout.getVisibility());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testMsgGetViewSend(){
        try {
            Class<?> clazz = MsgAdapter.class;// 获取PrivateClass整个类
            MsgAdapter msgAdapter = (MsgAdapter) clazz.newInstance();// 创建一个实例
            MsgAdapter.ViewHolder vh = (MsgAdapter.ViewHolder)msgAdapter.getView(1,null,null).getTag();
            assertEquals(0,vh.rightLayout.getVisibility());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }
}
