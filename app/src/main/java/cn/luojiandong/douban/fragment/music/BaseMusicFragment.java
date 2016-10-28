package cn.luojiandong.douban.fragment.music;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.luojiandong.douban.R;
import cn.luojiandong.douban.activity.MoreDetailActivity;
import cn.luojiandong.douban.adapter.MusicAdapter;
import cn.luojiandong.douban.base.BaseFragment;
import cn.luojiandong.douban.bean.MusicBean;
import cn.luojiandong.douban.bean.MusicsInfo;
import cn.luojiandong.douban.http.ApiFactory;
import cn.luojiandong.douban.http.RetrofitTask;
import cn.luojiandong.douban.utils.UIUtils;
import retrofit2.Call;

/**
 * 项目名：Douban
 * 包名：cn.luojiandong.douban.fragment.apiType.music
 * 文件名: MusicPopularFragment
 * 创建者: JackLuo
 * 创建时间: 2016-10-23 12:47
 * 描述：
 */
public abstract class BaseMusicFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, MusicAdapter.OnItemClickListener, RetrofitTask.ResponseListener {

    @BindView(R.id.music_recycleview)
    RecyclerView mMusicRecycleview;
    @BindView(R.id.music_refresh)
    SwipeRefreshLayout mMusicRefresh;
    @BindView(R.id.music_pager_error)
    View mMusicPagerError;
    @BindView(R.id.music_pager_loading)
    View mMusicPagerLoading;

    private LinearLayoutManager mLayoutManager;
    private Unbinder mBind;
    private MusicAdapter mAdapter;
    private List<MusicsInfo> mDatas;
    //将start设为静态变量，则每次修改start值后都会随之改变
    private static int start = 0;
    private int count = 20;
    private int requestState;
    private int firstRequestState = 1;
    private int loadingState = 2;
    private int refreshState = 3;
    private String[] mMusicTabTitle;

    /**
     * 初始化视图
     *
     * @return
     */
    @Override
    public View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_music, null, false);
        mBind = ButterKnife.bind(this, view);
        mMusicTabTitle = UIUtils.getStringArray(R.array.musicTabTitle);
        mMusicRecycleview.setVisibility(View.GONE);
        mMusicPagerError.setVisibility(View.GONE);
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
        mMusicRefresh.setOnRefreshListener(this);
    }

    /**
     * 进行网络请求
     */
    @NonNull
    private void getNetWorkRequest() {
        Call<MusicBean<MusicsInfo>> call = ApiFactory.INSTANCE.getDoubanApi().getMusic(mMusicTabTitle[getMusicType()], start, count);
        RetrofitTask<Object> retrofitTask = new RetrofitTask<>(UIUtils.getContext(), call);
        retrofitTask.handleMusicResponse(this);
    }

    public abstract int getMusicType();

    /**
     * 网络请求成功回调，获得网络响应的数据
     *
     * @param o 网络请求回调的body，这里指response.body().getMusics()
     */
    @Override
    public void onSuccess(Object o) {
        mMusicPagerLoading.setVisibility(View.GONE);
        mMusicRecycleview.setVisibility(View.VISIBLE);
        switch (requestState) {
            case 1:
                mDatas = (List<MusicsInfo>) o;
                setAdapter();
                break;
            case 2:
                mAdapter.setLoaded();
                mDatas.remove(mDatas.size() - 1);
                mAdapter.notifyItemRemoved(mDatas.size());
                List<MusicsInfo> lists = (List<MusicsInfo>) o;
                for (MusicsInfo list : lists) {
                    mDatas.add(list);
                }
                setAdapter();
                break;
            case 3:
                List<MusicsInfo> data = (List<MusicsInfo>) o;
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
        Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
        mMusicPagerLoading.setVisibility(View.GONE);
        mMusicPagerError.setVisibility(View.VISIBLE);
        //网络加载错误重试
        mMusicPagerError.findViewById(R.id.error_btn_retry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMusicPagerLoading.setVisibility(View.VISIBLE);
                mMusicPagerError.setVisibility(View.GONE);
                initData();
            }
        });
    }


    /**
     * recycleView设置适配器和设置上拉加载更多
     */
    private void setAdapter() {
        if (mAdapter == null) {
            mAdapter = new MusicAdapter(mDatas, mMusicRecycleview);
            mMusicRecycleview.setLayoutManager(mLayoutManager);
            mMusicRecycleview.setAdapter(mAdapter);
            mAdapter.setOnLoadMoreListener(new MusicAdapter.OnLoadMoreListener() {
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
            //如果数据返回到200条，start将不再增加
            if (start <= 200 - count) {
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
                if (start <= 200 - count) {
                    start += 20;
                }
                getNetWorkRequest();
                requestState = refreshState;
                mMusicRefresh.setRefreshing(false);
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
     *
     * @param position
     */
    @Override
    public void onClick(int position) {
        Intent intent = new Intent(getActivity(), MoreDetailActivity.class);
        MusicsInfo info = mDatas.get(position);
        String mobileUrl = info.getMobile_link();
        double mRating = info.getRating().getAverage();
        String mTitle = info.getTitle();
        intent.putExtra("mobileUrl", mobileUrl);
        intent.putExtra("rating", mRating);
        intent.putExtra("title", mTitle);
        startActivity(intent);
    }

    /**
     * item长按事件
     *
     * @param position
     */
    @Override
    public void onLongClick(int position) {

    }
}
