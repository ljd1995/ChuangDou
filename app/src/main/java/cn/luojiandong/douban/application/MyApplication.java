package cn.luojiandong.douban.application;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;

/**
 * Created by 95250 on 2016/10/18.
 */

public class MyApplication extends Application {
    private static Context mContext;
    public static Context getContext(){
        return mContext;
    }
    public static final String APPID = "b2b14e5ca0f31cdc775d469b78fc506d";
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Bmob.initialize(this, APPID);
    }
}
