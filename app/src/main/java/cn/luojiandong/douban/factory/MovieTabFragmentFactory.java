package cn.luojiandong.douban.factory;

import android.support.v4.app.Fragment;

import cn.luojiandong.douban.fragment.movie.InTheatersFragment;
import cn.luojiandong.douban.fragment.movie.TopFragment;

/**
 * Created by 95250 on 2016/10/18.
 */
public class MovieTabFragmentFactory {

    public static final int FRAGMENT_TOP250 = 0;
    public static final int FRAGMENT_IN_THEATERS = 1;

    public static Fragment createMovieTabFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case FRAGMENT_IN_THEATERS:
            fragment = new InTheatersFragment();
            break;
            case FRAGMENT_TOP250:
            fragment = new TopFragment();
            default:
                break;
        }
        return fragment;
    }
}
