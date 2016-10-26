package cn.luojiandong.douban.factory;

import cn.luojiandong.douban.fragment.music.AncientryFragment;
import cn.luojiandong.douban.fragment.music.BaseMusicFragment;
import cn.luojiandong.douban.fragment.music.CantoneseFragment;
import cn.luojiandong.douban.fragment.music.ChineseFragment;
import cn.luojiandong.douban.fragment.music.MusicPopularFragment;
import cn.luojiandong.douban.fragment.music.ReminiscenceFragment;
import cn.luojiandong.douban.fragment.music.RockFragment;

/**
 * 项目名：Douban
 * 包名：cn.luojiandong.douban.factory
 * 文件名: MusicTabFragmentFactory
 * 创建者: JackLuo
 * 创建时间: 2016-10-23 12:38
 * 描述：
 */
public class MusicTabFragmentFactory {
    public static final int FRAGMENT_MUSIC_POPULAR = 0;
    public static final int FRAGMENT_MUSIC_CHINESE = 1;
    public static final int FRAGMENT_MUSIC_CANTONESE = 2;
    public static final int FRAGMENT_MUSIC_ROCK = 3;
    public static final int FRAGMENT_MUSIC_ANCIENTRY = 4;
    public static final int FRAGMENT_MUSIC_REMINISCENCE = 5;

    public static BaseMusicFragment createMusicTabFragment(int position) {
        BaseMusicFragment fragment = null;
        switch (position) {
            case FRAGMENT_MUSIC_POPULAR:
                fragment = new MusicPopularFragment();
            break;
            case FRAGMENT_MUSIC_CHINESE:
                fragment = new ChineseFragment();
            break;
            case FRAGMENT_MUSIC_CANTONESE:
                fragment = new CantoneseFragment();
            break;
            case FRAGMENT_MUSIC_ROCK:
                fragment = new RockFragment();
            break;
            case FRAGMENT_MUSIC_ANCIENTRY:
                fragment = new AncientryFragment();
            break;
            case FRAGMENT_MUSIC_REMINISCENCE:
                fragment = new ReminiscenceFragment();
            break;
            default:
                break;
        }
        return fragment;
    }
}
