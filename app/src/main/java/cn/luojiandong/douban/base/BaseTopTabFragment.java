package cn.luojiandong.douban.base;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.luojiandong.douban.R;
import cn.luojiandong.douban.adapter.AllTypeTabPagerAdapter;
import cn.luojiandong.douban.utils.UIUtils;

/**
 * 项目名：Douban
 * 包名：cn.luojiandong.douban.base
 * 文件名: BaseTopTabFragment
 * 创建者: JackLuo
 * 创建时间: 2016-10-23 10:57
 * 描述：顶部三个TabFragment的基类
 */
public abstract class BaseTopTabFragment extends BaseFragment {
    @BindView(R.id.base_top_tab)
    TabLayout mBaseTopTabLayout;
    @BindView(R.id.base_top_tab_viewpager)
    ViewPager mBaseTabViewpager;
    private List<String> mTabTitles;

    @Override
    public View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_base_top_tab, null, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void initData() {
        mTabTitles = new ArrayList<>();
        String[] strings = UIUtils.getStringArray(getTabTitle());
        for (String s:strings) {
            mTabTitles.add(s);
        }
        AllTypeTabPagerAdapter adapter = new AllTypeTabPagerAdapter(getActivity().getSupportFragmentManager(), mTabTitles,getTabType());
        mBaseTabViewpager.setOffscreenPageLimit(2);
        mBaseTabViewpager.setAdapter(adapter);
        mBaseTopTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mBaseTopTabLayout.setupWithViewPager(mBaseTabViewpager);
    }

    protected abstract int getTabType();

    public abstract int getTabTitle();
}
