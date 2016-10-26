package cn.luojiandong.douban.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by 95250 on 2016/10/18.
 */

public class MyApplication extends Application {
    private static Context mContext;
    public static Context getContext(){
        return mContext;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
