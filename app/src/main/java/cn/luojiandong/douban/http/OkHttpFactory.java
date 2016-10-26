package cn.luojiandong.douban.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by 95250 on 2016/10/19.
 */

public enum OkHttpFactory {
    INSTANCE;

    private final OkHttpClient okHttpClient;

    private static final int TIMEOUT_READ = 25;
    private static final int TIMEOUT_CONNECTION = 25;

    OkHttpFactory() {

        okHttpClient = new OkHttpClient.Builder()
                //失败重连
                .retryOnConnectionFailure(true)
                //time out
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .build();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
