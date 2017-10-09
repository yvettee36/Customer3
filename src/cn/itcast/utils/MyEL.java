package cn.itcast.utils;

//字符串操作
public class MyEL {
    public static String sub(String str) {
        if (str.length() > 10) {
            return str.substring(0, 10) + "......";
        }
        return str;
    }
}
