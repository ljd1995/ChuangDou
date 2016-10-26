package cn.luojiandong.douban.base;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.luojiandong.douban.R;
import cn.luojiandong.douban.activity.BookDetailActivity;
import cn.luojiandong.douban.adapter.BookListAdapter;
import cn.luojiandong.douban.adapter.BookMoreAdapter;
import cn.luojiandong.douban.bean.BookBean;
import cn.luojiandong.douban.bean.BookInfo;
import cn.luojiandong.douban.http.ApiFactory;
import cn.luojiandong.douban.http.RetrofitTask;
import cn.luojiandong.douban.utils.UIUtils;
import retrofit2.Call;

/**
 * 项目名：Douban
 * 包名：cn.luojiandong.douban.base
 * 文件名: BaseBookDataController
 * 创建者: JackLuo
 * 创建时间: 2016-10-23 18:35
 * 描述：
 */
public abstract class BaseBookDataController {
    private Context mContext;
    private View mView;
    private String[] bookTypeTitle;
    private List<BookInfo> mBookInfos;
    private RecyclerView mRecyclerView;
    private View mPageLoading;
    private View mPageError;
    private ViewGroup mContainer;

    public BaseBookDataController(Context context, View view, RecyclerView recyclerView, View pageLoading, View pageError, ViewGroup container) {
        mContext = context;
        mView = view;
        mPageLoading = pageLoading;
        mPageError = pageError;
        mContainer = container;
        mRecyclerView = recyclerView;
    }


    public void requestAndSetData() {
        bookTypeTitle = UIUtils.getStringArray(R.array.bookTabTitle);
        TextView bookType = (TextView) mView.findViewById(R.id.books_type);
        bookType.setText(bookTypeTitle[getBookType()]);
        Call<BookBean<BookInfo>> call = ApiFactory.INSTANCE.getDoubanApi().getBook(bookTypeTitle[getBookType()]);
        RetrofitTask<Object> retrofitTask = new RetrofitTask<>(mContext, call);
        retrofitTask.handleBookResponse(new RetrofitTask.ResponseListener() {
            @Override
            public void onSuccess(Object o) {
                mBookInfos = (List<BookInfo>) o;
                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                BookListAdapter adapter = new BookListAdapter(mBookInfos);
                RecyclerView recycleView = (RecyclerView) mView.findViewById(R.id.books_in_recycleview);
                recycleView.setLayoutManager(layoutManager);
                recycleView.setAdapter(adapter);
                mPageLoading.setVisibility(View.GONE);
                mContainer.setVisibility(View.VISIBLE);
                mPageError.setVisibility(View.GONE);
                adapter.setOnItemClickListener(new BookListAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        Intent intent = new Intent(mContext, BookDetailActivity.class);
                        String bookId = mBookInfos.get(position).getId();
                        intent.putExtra("bookId", bookId);
                        mContext.startActivity(intent);
                    }

                    @Override
                    public void onLongClick(int position) {

                    }
                });
            }

            @Override
            public void onFail() {
                mPageLoading.setVisibility(View.GONE);
                mPageError.setVisibility(View.VISIBLE);
                //网络加载错误重试
                mPageError.findViewById(R.id.error_btn_retry).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPageLoading.setVisibility(View.VISIBLE);
                        mPageError.setVisibility(View.GONE);
                        requestAndSetData();
                    }
                });
            }
        });
    }

    public void getBookMoreData() {
        bookTypeTitle = UIUtils.getStringArray(R.array.bookTabTitle);
        Call<BookBean<BookInfo>> call = ApiFactory.INSTANCE.getDoubanApi().getBook(bookTypeTitle[getBookType()]);
        RetrofitTask<Object> retrofitTask = new RetrofitTask<>(mContext, call);
        retrofitTask.handleBookResponse(new RetrofitTask.ResponseListener() {
            @Override
            public void onSuccess(Object o) {
                mBookInfos = (List<BookInfo>) o;
                GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);
                BookMoreAdapter adapter = new BookMoreAdapter(mBookInfos);
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setAdapter(adapter);
                mPageLoading.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                adapter.setOnItemClickListener(new BookMoreAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        Intent intent = new Intent(mContext, BookDetailActivity.class);
                        String bookId = mBookInfos.get(position).getId();
                        intent.putExtra("bookId", bookId);
                        mContext.startActivity(intent);
                    }

                    @Override
                    public void onLongClick(int position) {

                    }
                });
            }

            @Override
            public void onFail() {
                mPageLoading.setVisibility(View.GONE);
                mPageError.setVisibility(View.VISIBLE);
                //网络加载错误重试
                mPageError.findViewById(R.id.error_btn_retry).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPageLoading.setVisibility(View.VISIBLE);
                        mPageError.setVisibility(View.GONE);
                        getBookMoreData();
                    }
                });
            }
        });
    }

    public abstract int getBookType();

}
