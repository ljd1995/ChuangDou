package cn.luojiandong.douban.http;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import cn.luojiandong.douban.bean.BookBean;
import cn.luojiandong.douban.bean.BookInfo;
import cn.luojiandong.douban.bean.MovieBean;
import cn.luojiandong.douban.bean.MovieDetailBean;
import cn.luojiandong.douban.bean.MusicBean;
import cn.luojiandong.douban.bean.MusicsInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 95250 on 2016/10/19.
 */

public class RetrofitTask<T> {
    private Call<MovieBean<T>> mCall;
    private Call<BookBean<T>> mBookCall;
    private Call<MovieDetailBean> mDetailCall;
    private Call<BookInfo> mBookInfoCall;
    private Call<MusicsInfo> mMusicsInfoCall;
    private Call<MusicBean<T>> mMusicCall;
    private Context mContext;
    private final String TAG = "response";


    public RetrofitTask(Context context, Call call) {
        mMusicCall = call;
        mBookCall = call;
        mDetailCall = call;
        mBookInfoCall = call;
        mCall = call;
        mContext = context;
    }

    public void handleMovieResponse(final ResponseListener listener) {
        mCall.enqueue(new Callback<MovieBean<T>>() {
            @Override
            public void onResponse(Call<MovieBean<T>> call, Response<MovieBean<T>> response) {
                if (response.isSuccessful() && response.errorBody() == null) {
                    listener.onSuccess(response.body().getSubjects());
                }
            }

            @Override
            public void onFailure(Call<MovieBean<T>> call, Throwable t) {
                listener.onFail();
                Log.d(TAG, "error:" + t.getMessage());
                Toast.makeText(mContext, "加载失败咯，检查一下网络", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void handleBookResponse(final ResponseListener listener) {
        mBookCall.enqueue(new Callback<BookBean<T>>() {
            @Override
            public void onResponse(Call<BookBean<T>> call, Response<BookBean<T>> response) {
                if (response.isSuccessful() && response.errorBody() == null) {
                    listener.onSuccess(response.body().getBooks());
                }
            }

            @Override
            public void onFailure(Call<BookBean<T>> call, Throwable t) {
                listener.onFail();
                Log.d(TAG, "error:" + t.getMessage());
                Toast.makeText(mContext, "加载失败咯，检查一下网络", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void handleBookDetailResponse(final ResponseListener listener) {
        mBookInfoCall.enqueue(new Callback<BookInfo>() {
            @Override
            public void onResponse(Call<BookInfo> call, Response<BookInfo> response) {
                if (response.isSuccessful() && response.errorBody() == null) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<BookInfo> call, Throwable t) {
                listener.onFail();
                Log.d(TAG, "error:" + t.getMessage());
                Toast.makeText(mContext, "加载失败咯，检查一下网络", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void handleMovieDetailResponse(final ResponseListener listener) {
        mDetailCall.enqueue(new Callback<MovieDetailBean>() {
            @Override
            public void onResponse(Call<MovieDetailBean> call, Response<MovieDetailBean> response) {
                if (response.isSuccessful() && response.errorBody() == null) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieDetailBean> call, Throwable t) {
                listener.onFail();
                Log.d(TAG, "error:" + t.getMessage());
                Toast.makeText(mContext, "加载失败咯，检查一下网络", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void handleMusicResponse(final ResponseListener listener) {
        mMusicCall.enqueue(new Callback<MusicBean<T>>() {
            @Override
            public void onResponse(Call<MusicBean<T>> call, Response<MusicBean<T>> response) {
                if (response.isSuccessful() && response.errorBody() == null) {
                    listener.onSuccess(response.body().getMusics());

                }
            }

            @Override
            public void onFailure(Call<MusicBean<T>> call, Throwable t) {
                listener.onFail();
                Log.d(TAG, "error:" + t.getMessage());
                Toast.makeText(mContext, "加载失败咯，检查一下网络", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void handleMusicDetailResponse(final ResponseListener listener) {
        mMusicsInfoCall.enqueue(new Callback<MusicsInfo>() {
            @Override
            public void onResponse(Call<MusicsInfo> call, Response<MusicsInfo> response) {
                if (response.isSuccessful() && response.errorBody() == null) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<MusicsInfo> call, Throwable t) {
                listener.onFail();
                Log.d(TAG, "error:" + t.getMessage());
                Toast.makeText(mContext, "加载失败咯，检查一下网络", Toast.LENGTH_LONG).show();
            }
        });
    }
    public interface ResponseListener<T> {
        void onSuccess(T t);

        void onFail();
    }

}
