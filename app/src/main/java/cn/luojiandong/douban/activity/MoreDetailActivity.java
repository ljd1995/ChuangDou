package cn.luojiandong.douban.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.luojiandong.douban.R;
import cn.luojiandong.douban.utils.ShareUtils;

public class MoreDetailActivity extends Activity {
    @BindView(R.id.title_bar_title)
    TextView mMovieTitle;
    @BindView(R.id.pb_load)
    ProgressBar mPbLoad;
    @BindView(R.id.webview)
    WebView mWebview;
    private double mRating;
    private String mMoblieUrl;
    private String mTitle;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_more_detail);
        mUnbinder = ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mWebview.setVisibility(View.GONE);
        mPbLoad.setVisibility(View.GONE);
        mMoblieUrl = getIntent().getStringExtra("mobileUrl");
        mRating = getIntent().getDoubleExtra("rating",0);
        mTitle = getIntent().getStringExtra("title");
        mMovieTitle.setText(mTitle);
        mWebview.loadUrl(mMoblieUrl);
        mWebview.getSettings().setUseWideViewPort(true);
        mWebview.getSettings().setBuiltInZoomControls(true);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mPbLoad.setProgress(newProgress);
            }
        });
        mWebview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mPbLoad.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mPbLoad.setVisibility(View.GONE);
                mWebview.setVisibility(View.VISIBLE);
                super.onPageFinished(view, url);
            }
        });
    }

    @OnClick(R.id.title_bar_back)
    public void onBack(View v){
        finish();
    }
    @OnClick(R.id.title_bar_share)
    public void onShare(View v){
        ShareUtils.getShare(this,mRating,mTitle,mMoblieUrl);
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }
}
