package cn.luojiandong.douban.utils;

import android.content.Context;
import android.content.res.Resources;

import cn.luojiandong.douban.application.MyApplication;


/**
 * Created by JackLuo on 2016/9/2.
 */
public class UIUtils {
    /**
     * 得到上下文
     **/
    public static Context getContext() {
        return MyApplication.getContext();
    }

    /**
     * 得到Resource对象
     **/
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 得到String.xml中的字符
     **/
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 得到StringArray.xml中的字符数组
     **/
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 得到Color.xml中的颜色值
     **/
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 得到应用程序的包名
     **/
    public static String getPackageName() {
        return getContext().getPackageName();
    }


}
