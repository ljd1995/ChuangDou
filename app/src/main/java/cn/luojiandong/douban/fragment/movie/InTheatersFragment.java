package cn.luojiandong.douban.fragment.movie;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.luojiandong.douban.R;
import cn.luojiandong.douban.activity.MovieDetailActivity;
import cn.luojiandong.douban.adapter.InTheaterAdapter;
import cn.luojiandong.douban.base.BaseFragment;
import cn.luojiandong.douban.bean.SubjectsInfo;
import cn.luojiandong.douban.bean.MovieBean;
import cn.luojiandong.douban.http.ApiFactory;
import cn.luojiandong.douban.http.RetrofitTask;
import cn.luojiandong.douban.utils.UIUtils;
import retrofit2.Call;

/**
 * Created by 95250 on 2016/10/18.
 */
public class InTheatersFragment extends BaseFragment implements RetrofitTask.ResponseListener,InTheaterAdapter.OnItemClickListener {

    @BindView(R.id.inTheaters_recycleview)
    RecyclerView mInTheatersRecycleview;
    @BindView(R.id.inTheaters_pager_error)
    View mPager_error;
    @BindView(R.id.inTheaters_pager_loading)
    View mPager_loading;
    @BindView(R.id.include_title_bar)
    View mTitleBar;
    private GridLayoutManager mLayoutManager;
    private List<SubjectsInfo> mDatas;
    @Override
    public View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_intheater, null, false);
        ButterKnife.bind(this, view);
        mInTheatersRecycleview.setVisibility(View.GONE);
        mPager_error.setVisibility(View.GONE);
        mTitleBar.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void initData() {
        mLayoutManager = new GridLayoutManager(UIUtils.getContext(),3);
        getNetWorkRequest();
    }
    /**
     * 进行网络请求
     */
    @NonNull
    private void getNetWorkRequest() {
        Call<MovieBean<SubjectsInfo>> call = ApiFactory.INSTANCE.getDoubanApi().getTheatersMovie(0, 26);
        RetrofitTask<Object> retrofitTask = new RetrofitTask<>(UIUtils.getContext(), call);
        retrofitTask.handleMovieResponse(this);
    }

    @Override
    public void onSuccess(Object o) {
        mPager_loading.setVisibility(View.GONE);
        mInTheatersRecycleview.setVisibility(View.VISIBLE);
        mDatas = (List<SubjectsInfo>) o;
        InTheaterAdapter adapter = new InTheaterAdapter(mDatas);
        mInTheatersRecycleview.setLayoutManager(mLayoutManager);
        mInTheatersRecycleview.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

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
