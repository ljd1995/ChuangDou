package cn.luojiandong.douban.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.luojiandong.douban.R;
import cn.luojiandong.douban.bean.MovieDetailBean;
import cn.luojiandong.douban.http.ApiFactory;
import cn.luojiandong.douban.http.RetrofitTask;
import cn.luojiandong.douban.utils.ColorUtils;
import cn.luojiandong.douban.utils.GlideUtils;
import cn.luojiandong.douban.utils.ShareUtils;
import cn.luojiandong.douban.utils.UIUtils;
import retrofit2.Call;

public class MovieDetailActivity extends Activity implements RetrofitTask.ResponseListener {

    @BindView(R.id.ll_movie_container)
    LinearLayout mLinearLayout;
    @BindView(R.id.movie_pager_loading)
    View mLoadingView;
    @BindView(R.id.movie_detail_image)
    ImageView mMovieImage;
    @BindView(R.id.title_bar_title)
    TextView mMovieTitle;
    @BindView(R.id.rl_movie_container)
    RelativeLayout mMovieContainer;
    @BindView(R.id.title_bar)
    RelativeLayout mTitle_bar;
    @BindView(R.id.movie_name)
    TextView mMovieName;
    @BindView(R.id.movie_original_title)
    TextView mMovieOriginalTitle;
    @BindView(R.id.movie_rating)
    TextView mMovieRating;
    @BindView(R.id.movie_ratingbar)
    RatingBar mMovieRatingbar;
    @BindView(R.id.movie_count)
    TextView mMovieCount;
    @BindView(R.id.movie_country)
    TextView mMovieCountry;
    @BindView(R.id.expandable_text)
    TextView mExpandableText;
    @BindView(R.id.expand_text_view)
    ExpandableTextView mExpandTextView;
    @BindView(R.id.movie_wish)
    TextView mMovieWish;
    @BindView(R.id.movie_collect)
    TextView mMovieCollect;
    @BindView(R.id.movie_cut)
    View mMovieCut;
    @BindView(R.id.movie_summary)
    TextView mMovieSummary;
    @BindView(R.id.expand_collapse)
    ImageButton mExpandCollapse;
    @BindView(R.id.movie_directors)
    ImageView mMovieDirectors;
    @BindView(R.id.movie_director_name)
    TextView mMovieDirectorName;
    private String id;
    private static final String TAG = "MovieDetailActivity";
    private String mTitle;
    private String mImageUrl;
    private double mRating;
    private String mMobileUrl;
    private ImageView mMovieCasts1;
    private ImageView mMovieCasts2;
    private ImageView mMovieCasts3;
    private ImageView mMovieCasts4;
    private TextView mMovieCasts1Name;
    private TextView mMovieCasts2Name;
    private TextView mMovieCasts3Name;
    private TextView mMovieCasts4Name;
    private TextView mMovieDetail;
    private List<TextView> mTextViewList;
    private List<ImageView> mImageViewList;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        mUnbinder = ButterKnife.bind(this);
        mLinearLayout.setVisibility(View.GONE);
        initView();
        initData();
    }

    private void initView() {
        mMovieCasts1 = (ImageView) findViewById(R.id.movie_casts1);
        mMovieCasts2 = (ImageView) findViewById(R.id.movie_casts2);
        mMovieCasts3 = (ImageView) findViewById(R.id.movie_casts3);
        mMovieCasts4 = (ImageView) findViewById(R.id.movie_casts4);
        mMovieCasts1Name = (TextView) findViewById(R.id.movie_casts1_name);
        mMovieCasts2Name = (TextView) findViewById(R.id.movie_casts2_name);
        mMovieCasts3Name = (TextView) findViewById(R.id.movie_casts3_name);
        mMovieCasts4Name = (TextView) findViewById(R.id.movie_casts4_name);
        mMovieDetail = (TextView) findViewById(R.id.movie_detail);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("movieId");
        }
        getNetWorkRequest();
    }

    /**
     * 进行网络请求
     */
    @NonNull
    private void getNetWorkRequest() {
        Call<MovieDetailBean> call = ApiFactory.INSTANCE.getDoubanApi().getMovieDetail(id);
        RetrofitTask<Object> retrofitTask = new RetrofitTask<>(UIUtils.getContext(), call);
        retrofitTask.handleMovieDetailResponse(this);
    }

    /**
     * 网络请求成功回调，获得网络响应的数据
     *
     * @param o 网络请求回调的body，这里指response.body()
     */
    @Override
    public void onSuccess(Object o) {
        setData((MovieDetailBean) o);
        mLoadingView.setVisibility(View.GONE);
        mLinearLayout.setVisibility(View.VISIBLE);
    }


    /**
     * 网络请求失败回调
     */
    @Override
    public void onFail() {

    }

    /**
     * 对网络回调数据进行设置
     *
     * @param movieDetailBean
     */
    private void setData(MovieDetailBean movieDetailBean) {
        //通过javabean获得数据
        mImageUrl = movieDetailBean.getImages().getLarge();
        mRating = movieDetailBean.getRating().getAverage();
        mTitle = movieDetailBean.getTitle();
        mMobileUrl = movieDetailBean.getMobile_url();
        String year = movieDetailBean.getYear();
        String originalTitle = movieDetailBean.getOriginal_title();
        String country = movieDetailBean.getCountries().get(0);
        String summary = movieDetailBean.getSummary();
        String directorImageUrl = movieDetailBean.getDirectors().get(0).getAvatars().getMedium();
        String directorName = movieDetailBean.getDirectors().get(0).getName();
        int ratingCount = movieDetailBean.getRatings_count();
        int wishCount = movieDetailBean.getWish_count();
        int collectCount = movieDetailBean.getCollect_count();

        List<MovieDetailBean.CastsBean> casts = movieDetailBean.getCasts();
        List<String> genres = movieDetailBean.getGenres();
        mTextViewList = new ArrayList<>();
        mImageViewList = new ArrayList<>();

        mTextViewList.add(mMovieCasts1Name);
        mTextViewList.add(mMovieCasts2Name);
        mTextViewList.add(mMovieCasts3Name);
        mTextViewList.add(mMovieCasts4Name);
        mImageViewList.add(mMovieCasts1);
        mImageViewList.add(mMovieCasts2);
        mImageViewList.add(mMovieCasts3);
        mImageViewList.add(mMovieCasts4);

        //设置标题栏和图片大背景颜色
        setBackground(mImageUrl);

        //兼容低版本RatingBar的progressTint属性
        LayerDrawable ld_stars = (LayerDrawable) mMovieRatingbar.getProgressDrawable();
        ld_stars.getDrawable(2).setColorFilter(UIUtils.getColor(R.color.colorYellow), PorterDuff.Mode.SRC_ATOP);

        //填充数据到view
        mMovieTitle.setText(mTitle);
        mMovieName.setText(mTitle);
        mMovieCountry.setText(country);
        mMovieRating.setText(mRating + "");
        mMovieRatingbar.setProgress((int) mRating);
        mMovieOriginalTitle.setText("原名：" + originalTitle);
        mMovieCount.setText(ratingCount + "人");
        mExpandTextView.setText(summary);
        mMovieWish.setText("想看\n" + wishCount + "人");
        mMovieCollect.setText("看过\n" + collectCount + "人");
        mMovieDirectorName.setText(directorName);
        GlideUtils.loadImageView(this, mImageUrl, mMovieImage);
        GlideUtils.loadImageView(this, directorImageUrl, mMovieDirectors);
        //防止genres数目不确定
        if (genres.size() > 0) {
            if (genres.size() == 1) {
                String genres1 = genres.get(0);
                mMovieDetail.setText(year + " / " + genres1);
            } else if (genres.size() == 2) {
                String genres1 = genres.get(0);
                String genres2 = genres.get(1);
                mMovieDetail.setText(year + " / " + genres1 + " / " + genres2);
            } else if (genres.size() == 3) {
                String genres1 = genres.get(0);
                String genres2 = genres.get(1);
                String genres3 = genres.get(2);
                mMovieDetail.setText(year + " / " + genres1 + " / " + genres2 + " / " + genres3);
            }
        }
        //防止casts.size()==0
        if (casts.size() > 0) {
            for (int i = 0; i < casts.size(); i++) {
                String castName = casts.get(i).getName();
                String medium = casts.get(i).getAvatars().getMedium();
                mTextViewList.get(i).setText(castName);
                GlideUtils.loadImageView(this, medium, mImageViewList.get(i));
            }
        }

    }

    @OnClick(R.id.title_bar_back)
    public void onBack(View v) {
        finish();
    }

    @OnClick(R.id.title_bar_share)
    public void onShare(View v) {
        ShareUtils.getShare(this, mRating, mTitle, mMobileUrl);
    }

    @OnClick(R.id.tv_loadmore)
    public void onLoadMore(View v) {
        Intent intent = new Intent(this, MoreDetailActivity.class);
        intent.putExtra("mobileUrl", mMobileUrl);
        intent.putExtra("rating", mRating);
        intent.putExtra("title", mTitle);
        startActivity(intent);
    }

    /**
     * 根据图片url进行bitmap转换，利用palette进行图片取色，并设置为titleBar和图片container背景
     */
    private void setBackground(String imageUrl) {
        Glide.with(this).load(imageUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Palette.generateAsync(resource, new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        int darkMutedColor = palette.getDarkMutedColor(UIUtils.getColor(R.color.colorGray));
                        mMovieContainer.setBackgroundColor(ColorUtils.colorBurn(darkMutedColor));
                        mTitle_bar.setBackgroundColor(ColorUtils.colorBurn(darkMutedColor));
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }
}
