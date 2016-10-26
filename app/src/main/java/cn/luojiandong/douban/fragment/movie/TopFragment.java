package cn.luojiandong.douban.fragment.movie;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.luojiandong.douban.R;
import cn.luojiandong.douban.activity.MovieDetailActivity;
import cn.luojiandong.douban.adapter.TopMovieAdapter;
import cn.luojiandong.douban.base.BaseFragment;
import cn.luojiandong.douban.bean.SubjectsInfo;
import cn.luojiandong.douban.bean.MovieBean;
import cn.luojiandong.douban.http.ApiFactory;
import cn.luojiandong.douban.http.RetrofitTask;
import cn.luojiandong.douban.utils.UIUtils;
import retrofit2.Call;

/**
 * create by JackLuo on 2016/10/18
 *
 * @description Movie Tab下top250内容展示
 */
public class TopFragment extends BaseFragment implements RetrofitTask.ResponseListener, SwipeRefreshLayout.OnRefreshListener, TopMovieAdapter.OnItemClickListener {
    @BindView(R.id.top_recycleview)
    RecyclerView mTopRecycleview;
    @BindView(R.id.top_refresh)
    SwipeRefreshLayout mTopRefresh;
    @BindView(R.id.pager_error)
    View mPager_error;
    @BindView(R.id.pager_loading)
    View mPager_loading;

    private LinearLayoutManager mLayoutManager;
    private Unbinder mBind;
    private TopMovieAdapter mAdapter;
    private List<SubjectsInfo> mDatas;
    //将start设为静态变量，则每次修改start值后都会随之改变
    private static int start = 0;
    private int count = 20;
    private int requestState;
    private int firstRequestState = 1;
    private int loadingState = 2;
    private int refreshState = 3;

    /**
     * 初始化视图
     *
     * @return
     */
    @Override
    public View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_top, null, false);
        mBind = ButterKnife.bind(this, view);
        mTopRecycleview.setVisibility(View.GONE);
        mPager_error.setVisibility(View.GONE);
        return view;
    }

    /**
     * 使用Retrofit进行网络请求数据
     */
    @Override
    public void initData() {
        mLayoutManager = new LinearLayoutManager(UIUtils.getContext(), LinearLayoutManager.VERTICAL, false);
        getNetWorkRequest();
        requestState = firstRequestState;
        mTopRefresh.setOnRefreshListener(this);
    }


    /**
     * 网络请求成功回调，获得网络响应的数据
     *
     * @param o 网络请求回调的body，这里指response.body().getSubjects()
     */
    @Override
    public void onSuccess(Object o) {
        mPager_loading.setVisibility(View.GONE);
        mTopRecycleview.setVisibility(View.VISIBLE);
        switch (requestState) {
            case 1:
                mDatas = (List<SubjectsInfo>) o;
                setAdapter();
                break;
            case 2:
                mAdapter.setLoaded();
                mDatas.remove(mDatas.size() - 1);
                mAdapter.notifyItemRemoved(mDatas.size());
                List<SubjectsInfo> lists = (List<SubjectsInfo>) o;
                for (SubjectsInfo list : lists) {
                    mDatas.add(list);
                }
                setAdapter();
                break;
            case 3:
                List<SubjectsInfo> data = (List<SubjectsInfo>) o;
                mDatas.addAll(0, data);
                setAdapter();
                break;
            default:
                break;
        }
        mAdapter.setOnItemClickListener(this);
    }

    /**
     * 网络请求失败回调
     */
    @Override
    public void onFail() {
        mPager_loading.setVisibility(View.GONE);
        mPager_error.setVisibility(View.VISIBLE);
        //网络加载错误重试
        mPager_error.findViewById(R.id.error_btn_retry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager_loading.setVisibility(View.VISIBLE);
                mPager_error.setVisibility(View.GONE);
                initData();
            }
        });
    }

    /**
     * 进行网络请求
     */
    @NonNull
    private void getNetWorkRequest() {
        Call<MovieBean<SubjectsInfo>> call = ApiFactory.INSTANCE.getDoubanApi().getMovie(start, count);
        RetrofitTask<Object> retrofitTask = new RetrofitTask<>(UIUtils.getContext(), call);
        retrofitTask.handleMovieResponse(this);
    }

    /**
     * recycleView设置适配器和设置上拉加载更多
     */
    private void setAdapter() {
        if (mAdapter == null) {
            mAdapter = new TopMovieAdapter(mDatas, mTopRecycleview);
            mTopRecycleview.setLayoutManager(mLayoutManager);
            mTopRecycleview.setAdapter(mAdapter);
            mAdapter.setOnLoadMoreListener(new TopMovieAdapter.OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    mDatas.add(null);
                    mAdapter.notifyItemInserted(mDatas.size() - 1);
                    loadMoreData(true);
                    mAdapter.setLoading();
                }
            });
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 上拉加载更多数据
     *
     * @param isMore
     */
    private void loadMoreData(boolean isMore) {
        if (isMore) {
            //如果数据返回到250条，start将不再增加
            if (start <= 250 - count) {
                start += 20;
            }
            getNetWorkRequest();
            requestState = loadingState;
        }

    }

    /**
     * 下拉刷新请求并更新数据
     */
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (start <= 250 - count) {
                    start += 20;
                }
                getNetWorkRequest();
                requestState = refreshState;
                mTopRefresh.setRefreshing(false);
            }
        }, 2000);
    }

    /**
     * ButterKnife在fragment销毁时解绑
     */
    @Override
    public void onDestroy() {
        mBind.unbind();
        super.onDestroy();
    }

    /**
     * item点击事件
     * @param position
     */
    @Override
    public void onClick(int position) {
        Intent intent = new Intent(getActivity(),MovieDetailActivity.class);
        String movieId = mDatas.get(position).getId();
        intent.putExtra("movieId",movieId);
        startActivity(intent);
    }

    /**
     * item长按事件
     * @param position
     */
    @Override
    public void onLongClick(int position) {

    }
}
