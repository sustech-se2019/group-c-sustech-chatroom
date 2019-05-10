package com.example.se_project;

public class Utils {

    /**
     * 判定输入的是否是英文
     *
     * @param ch
     *  被校验的字符
     * @return true代表是英文
     */
    //英文占1byte，非英文（可认为是中文）占2byte，根据这个特性来判断字符
    public static boolean checkChar(char ch) {
        if ((ch + "").getBytes().length == 1) {
            return true;//英文
        } else {
            return false;//不是英文
        }
    }

    public static boolean checkString(String str) {
        if (str != null) {
            for (int i = 0; i < str.length(); i++) {
                if (!checkChar(str.charAt(i))) {
                    return false;//不是英文
                }
            }
        }
        return true;//英文
    }

}
