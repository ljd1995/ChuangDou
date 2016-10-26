package cn.luojiandong.douban.fragment.movie;


import cn.luojiandong.douban.R;
import cn.luojiandong.douban.adapter.AllTypeTabPagerAdapter;
import cn.luojiandong.douban.base.BaseTopTabFragment;

/**
 * Created by 95250 on 2016/10/18.
 */
public class TopHomeFragment extends BaseTopTabFragment {


    @Override
    protected int getTabType() {
        return AllTypeTabPagerAdapter.MOVIETYPE;
    }

    @Override
    public int getTabTitle() {
        return R.array.homeTabTitle;
    }
}
