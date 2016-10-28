package cn.luojiandong.douban.fragment.music;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.luojiandong.douban.R;
import cn.luojiandong.douban.adapter.MusicTabPagerAdapter;
import cn.luojiandong.douban.base.BaseFragment;
import cn.luojiandong.douban.utils.UIUtils;

/**
 * Created by 95250 on 2016/10/18.
 */
public class TopMusicFragment extends BaseFragment {

    @BindView(R.id.top_music_tab)
    TabLayout mTopMusicTab;
    @BindView(R.id.top_tab_music_viewpager)
    ViewPager mTopTabMusicViewpager;
    private List<String> mTabTitles;
    private Unbinder mUnbinder;

    @Override
    public View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_top_music, null, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        mTabTitles = new ArrayList<>();
        String[] strings = UIUtils.getStringArray(R.array.musicTabTitle);
        for (String s : strings) {
            mTabTitles.add(s);
        }
        MusicTabPagerAdapter adapter = new MusicTabPagerAdapter(getActivity().getSupportFragmentManager(), mTabTitles);
        mTopTabMusicViewpager.setOffscreenPageLimit(6);
        mTopTabMusicViewpager.setAdapter(adapter);
        mTopMusicTab.setTabMode(TabLayout.MODE_FIXED);
        mTopMusicTab.setupWithViewPager(mTopTabMusicViewpager);
    }

    @Override
    public void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }
}
