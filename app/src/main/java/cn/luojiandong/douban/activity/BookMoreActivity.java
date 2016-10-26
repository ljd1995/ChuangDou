package cn.luojiandong.douban.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.luojiandong.douban.R;
import cn.luojiandong.douban.controller.BookClassicsController;
import cn.luojiandong.douban.controller.BookCultureController;
import cn.luojiandong.douban.controller.BookLifeController;
import cn.luojiandong.douban.controller.BookLiteratureController;
import cn.luojiandong.douban.controller.BookPopularController;
import cn.luojiandong.douban.utils.UIUtils;

/**
 * 项目名：Douban
 * 包名：cn.luojiandong.douban.activity
 * 文件名: BookMoreActivity
 * 创建者: JackLuo
 * 创建时间: 2016-10-24 14:31
 * 描述：
 */
public class BookMoreActivity extends Activity {
    @BindView(R.id.inTheaters_recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.inTheaters_pager_error)
    View mPager_error;
    @BindView(R.id.inTheaters_pager_loading)
    View mPager_loading;
    @BindView(R.id.include_title_bar)
    View mTitleBar;
    @BindView(R.id.title_bar_title)
    TextView mBookTypeTitle;
    @BindView(R.id.title_bar_share)
    ImageButton mShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_intheater);
        ButterKnife.bind(this);
        mRecyclerView.setVisibility(View.GONE);
        mPager_error.setVisibility(View.GONE);
        mShare.setVisibility(View.GONE);
        initData();
    }

    private void initData() {
        BookClassicsController classicsController = new BookClassicsController(BookMoreActivity.this, null, mRecyclerView,mPager_loading,mPager_error,null);
        BookLiteratureController literatureController = new BookLiteratureController(BookMoreActivity.this, null, mRecyclerView,mPager_loading,mPager_error,null);
        BookPopularController popularController = new BookPopularController(BookMoreActivity.this, null, mRecyclerView,mPager_loading,mPager_error,null);
        BookCultureController cultureController = new BookCultureController(BookMoreActivity.this, null, mRecyclerView,mPager_loading,mPager_error,null);
        BookLifeController lifeController = new BookLifeController(BookMoreActivity.this, null, mRecyclerView,mPager_loading,mPager_error,null);
        Intent intent = getIntent();
        String[] bookTitle = UIUtils.getStringArray(R.array.bookTabTitle);
        if (intent != null) {
            int position = intent.getIntExtra("position", 0);
            switch (position) {
                case 0:
                    classicsController.getBookMoreData();
                    mBookTypeTitle.setText("图书--"+bookTitle[position]);
                    break;
                case 1:
                    literatureController.getBookMoreData();
                    mBookTypeTitle.setText("图书--"+bookTitle[position]);
                    break;
                case 2:
                    popularController.getBookMoreData();
                    mBookTypeTitle.setText("图书--"+bookTitle[position]);
                    break;
                case 3:
                    cultureController.getBookMoreData();
                    mBookTypeTitle.setText("图书--"+bookTitle[position]);
                    break;
                case 4:
                    lifeController.getBookMoreData();
                    mBookTypeTitle.setText("图书--"+bookTitle[position]);
                    break;
                default:
                    break;
            }
        }
    }
    @OnClick(R.id.title_bar_back)
    public void onBack(View v){
        finish();
    }

}
