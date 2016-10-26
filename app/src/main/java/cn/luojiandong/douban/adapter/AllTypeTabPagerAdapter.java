package cn.luojiandong.douban.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import cn.luojiandong.douban.factory.MovieTabFragmentFactory;
import cn.luojiandong.douban.factory.MusicTabFragmentFactory;

/**
 * Created by 95250 on 2016/10/18.
 */

public class AllTypeTabPagerAdapter extends FragmentPagerAdapter {
    private int TABTYPE;
    public static final int MOVIETYPE = 0;
    public static final int MUSICTYPE = 1;

    private List<String> mTabTitles;

    public AllTypeTabPagerAdapter(FragmentManager fm, List<String> tabTitles, int TABTYPE) {
        super(fm);
        mTabTitles = tabTitles;
        this.TABTYPE = TABTYPE;
    }

    @Override
    public Fragment getItem(int position) {
        if (TABTYPE == MOVIETYPE) {
            return MovieTabFragmentFactory.createMovieTabFragment(position);
        } else if (TABTYPE == MUSICTYPE) {
            return MusicTabFragmentFactory.createMusicTabFragment(position);
        } else {
            return null;
        }
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
