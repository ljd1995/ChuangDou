package cn.luojiandong.douban.http;

import cn.luojiandong.douban.constants.MyConstants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 95250 on 2016/10/19.
 */

public enum RetrofitClient {
    INSTANCE;

    private final Retrofit retrofit;

    RetrofitClient() {
        retrofit = new Retrofit.Builder()
                //设置OkHttpClient
                .client(OkHttpFactory.INSTANCE.getOkHttpClient())
                //baseUrl
                .baseUrl(MyConstants.douBanApi)
                //gson转化器
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
