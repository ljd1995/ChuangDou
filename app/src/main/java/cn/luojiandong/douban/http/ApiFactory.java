package cn.luojiandong.douban.http;

import cn.luojiandong.douban.api.DoubanApi;

/**
 * Created by 95250 on 2016/10/19.
 */

public enum ApiFactory {
    INSTANCE;
    private final DoubanApi mDoubanApi;

    ApiFactory() {
        mDoubanApi = RetrofitClient.INSTANCE.getRetrofit().create(DoubanApi.class);
    }

    public DoubanApi getDoubanApi() {
        return mDoubanApi;
    }
}
