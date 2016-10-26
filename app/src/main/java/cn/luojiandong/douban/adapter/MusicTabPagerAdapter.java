package cn.luojiandong.douban.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import cn.luojiandong.douban.factory.MusicTabFragmentFactory;

/**
 * Created by 95250 on 2016/10/18.
 */

public class MusicTabPagerAdapter extends FragmentPagerAdapter {

    private List<String> mTabTitles;

    public MusicTabPagerAdapter(FragmentManager fm, List<String> tabTitles) {
        super(fm);
        mTabTitles = tabTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return MusicTabFragmentFactory.createMusicTabFragment(position);
    }

    @Override
    public int getCount() {
        return mTabTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles.get(position);
    }
}
