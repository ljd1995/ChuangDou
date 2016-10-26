package cn.luojiandong.douban.api;

import cn.luojiandong.douban.bean.BookBean;
import cn.luojiandong.douban.bean.BookInfo;
import cn.luojiandong.douban.bean.MovieBean;
import cn.luojiandong.douban.bean.MovieDetailBean;
import cn.luojiandong.douban.bean.MusicBean;
import cn.luojiandong.douban.bean.MusicsInfo;
import cn.luojiandong.douban.bean.SubjectsInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 95250 on 2016/10/19.
 */

public interface DoubanApi {

    /**
     * 获取电影榜单top250
     *
     * @param
     * @return
     */
    @GET("movie/top250")
    Call<MovieBean<SubjectsInfo>> getMovie(@Query("start") int start, @Query("count") int count);

    /**
     * 获取电影热映榜
     *
     * @param
     * @return
     */
    @GET("movie/in_theaters")
    Call<MovieBean<SubjectsInfo>> getTheatersMovie(@Query("start") int start, @Query("count") int count);

    /**
     * 获取电影条目信息
     *
     * @param id
     * @return
     */
    @GET("movie/subject/{id}")
    Call<MovieDetailBean> getMovieDetail(@Path("id") String id);

    /**
     * 根据tag查询图书
     *
     * @param tag 图书类型
     * @return
     */
    @GET("book/search")
    Call<BookBean<BookInfo>> getBook(@Query("tag") String tag);

    /**
     * 根据id查询图书具体信息
     *
     * @param id
     * @return
     */
    @GET("book/{id}")
    Call<BookInfo> getBookDetail(@Path("id") String id);

    /**
     * 根据tag查询音乐
     *
     * @param tag 音乐类型
     * @return
     */
    @GET("music/search")
    Call<MusicBean<MusicsInfo>> getMusic(@Query("tag") String tag,@Query("start") int start, @Query("count") int count);

    /**
     * 根据id查询音乐具体信息
     *
     * @param id
     * @return
     */
    @GET("music/{id}")
    Call<MusicsInfo> getMusicDetail(@Path("id") String id);
}
