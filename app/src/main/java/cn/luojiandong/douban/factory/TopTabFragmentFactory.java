package cn.luojiandong.douban.factory;

import android.support.v4.app.Fragment;

import cn.luojiandong.douban.fragment.book.TopBookFragment;
import cn.luojiandong.douban.fragment.movie.TopHomeFragment;
import cn.luojiandong.douban.fragment.music.TopMusicFragment;

/**
 * Created by 95250 on 2016/10/18.
 */
public class TopTabFragmentFactory {
    public static final int FRAGMENT_TOPHOME = 0;
    public static final int FRAGMENT_BOOK = 1;
    public static final int FRAGMENT_MUSIC= 2;

    public static Fragment createTopTabFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case FRAGMENT_TOPHOME:
                fragment = new TopHomeFragment();
                break;
            case FRAGMENT_BOOK:
                fragment = new TopBookFragment();

                break;
            case FRAGMENT_MUSIC:
                fragment = new TopMusicFragment();
                break;
            default:
                break;
        }
        return fragment;
    }
}
