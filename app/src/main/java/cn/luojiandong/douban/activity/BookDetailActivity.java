package cn.luojiandong.douban.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.luojiandong.douban.R;
import cn.luojiandong.douban.bean.BookInfo;
import cn.luojiandong.douban.http.ApiFactory;
import cn.luojiandong.douban.http.RetrofitTask;
import cn.luojiandong.douban.utils.ColorUtils;
import cn.luojiandong.douban.utils.GlideUtils;
import cn.luojiandong.douban.utils.ShareUtils;
import cn.luojiandong.douban.utils.UIUtils;
import retrofit2.Call;

public class BookDetailActivity extends Activity implements RetrofitTask.ResponseListener {

    @BindView(R.id.title_bar_title)
    TextView mTitleBarTitle;
    @BindView(R.id.movie_detail_image)
    ImageView mBookDetailImage;
    @BindView(R.id.movie_name)
    TextView mBookName;
    @BindView(R.id.movie_detail)
    TextView mBookDetail;
    @BindView(R.id.movie_original_title)
    TextView mBookPublisher;
    @BindView(R.id.movie_country)
    TextView mBookPubdate;
    @BindView(R.id.movie_rating)
    TextView mBookRating;
    @BindView(R.id.movie_ratingbar)
    RatingBar mBookRatingbar;
    @BindView(R.id.movie_count)
    TextView mBookCount;
    @BindView(R.id.ll_book_container)
    LinearLayout mLlBookContainer;
    @BindView(R.id.book_pager_loading)
    View mLoading;
    @BindView(R.id.detail_topimage)
    View mImageContainer;
    @BindView(R.id.book_title_bar)
    View mTitleBar;
    @BindView(R.id.detail_synopsis)
    View mSynopsis;
    @BindView(R.id.detail_author_synopsis)
    View mAuthorSynopsis;
    @BindView(R.id.movie_wish)
    TextView mBookWish;
    @BindView(R.id.movie_collect)
    TextView mBookLoadMore;
    private BookInfo mData;
    private double mRating;
    private String mTitle;
    private String mDetailUrl;
    private String mBookId;
    private ExpandableTextView mSynopsisTextView;
    private ExpandableTextView mAuthorSynopsisTextView;
    private TextView mBookSummary;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        mUnbinder = ButterKnife.bind(this);
        mLlBookContainer.setVisibility(View.GONE);
        initView();
        initData();
    }

    private void initView() {
        mSynopsisTextView = (ExpandableTextView) mSynopsis.findViewById(R.id.expand_text_view);
        mAuthorSynopsisTextView = (ExpandableTextView) mAuthorSynopsis.findViewById(R.id.expand_text_view);
        mBookSummary = (TextView) mAuthorSynopsis.findViewById(R.id.movie_summary);
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mBookId = intent.getStringExtra("bookId");
            getNetWorkRequest();
        }
    }

    /**
     * 进行网络请求
     */
    @NonNull
    private void getNetWorkRequest() {
        Call<BookInfo> call = ApiFactory.INSTANCE.getDoubanApi().getBookDetail(mBookId);
        RetrofitTask<Object> retrofitTask = new RetrofitTask<>(UIUtils.getContext(), call);
        retrofitTask.handleBookDetailResponse(this);
    }

    @Override
    public void onSuccess(Object o) {
        mData = (BookInfo) o;
        setData(mData);
        mLoading.setVisibility(View.GONE);
        mLlBookContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFail() {

    }

    private void setData(BookInfo info) {
        mTitle = info.getTitle();
        mRating = info.getRating().getAverage();
        String publisher = info.getPublisher();
        String pubDate = info.getPubdate();
        String largeImageUrl = info.getImages().getLarge();
        int numRaters = info.getRating().getNumRaters();
        String summary = info.getSummary();
        String authorName = info.getAuthor().get(0);
        String authorIntro = info.getAuthor_intro();
        mDetailUrl = info.getAlt();
        mBookSummary.setText("作者简介");
        mTitleBarTitle.setText(mTitle);
        mAuthorSynopsisTextView.setText(authorIntro);
        mSynopsisTextView.setText(summary);
        mBookRatingbar.setProgress((int) mRating);
        mBookPubdate.setText("出版时间：" + pubDate);
        mBookPublisher.setText("出版社：" + publisher);
        mBookCount.setText(numRaters + "人");
        mBookDetail.setText("作者：" + authorName);
        mBookRating.setText(mRating + "");
        mBookName.setText(mTitle);
        mBookWish.setText("想看");
        mBookLoadMore.setText("更多信息");
        GlideUtils.loadImageView(UIUtils.getContext(), largeImageUrl, mBookDetailImage);
        setBackground(largeImageUrl);
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
                        mImageContainer.setBackgroundColor(ColorUtils.colorBurn(darkMutedColor));
                        mTitleBar.setBackgroundColor(ColorUtils.colorBurn(darkMutedColor));
                    }
                });
            }
        });
    }


    @OnClick(R.id.title_bar_back)
    public void onBack(View v) {
        finish();
    }

    @OnClick(R.id.title_bar_share)
    public void onShare(View v) {
        ShareUtils.getShare(this, mRating, mTitle, mDetailUrl);
    }

    @OnClick({R.id.movie_wish, R.id.movie_collect})
    public void onLoadMore(View v) {
        Intent intent = new Intent(this, MoreDetailActivity.class);
        intent.putExtra("mobileUrl", mDetailUrl);
        intent.putExtra("rating", mRating);
        intent.putExtra("title", mTitle);
        startActivity(intent);
    }
    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }}
