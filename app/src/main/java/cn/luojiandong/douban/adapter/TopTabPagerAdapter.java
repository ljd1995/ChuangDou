package cn.luojiandong.douban.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.RadioGroup;

import cn.luojiandong.douban.factory.TopTabFragmentFactory;

/**
 * Created by 95250 on 2016/10/18.
 */

public class TopTabPagerAdapter extends FragmentPagerAdapter {

    RadioGroup mRadioGroup;
    public TopTabPagerAdapter(FragmentManager fm,RadioGroup radioGroup) {
        super(fm);
        mRadioGroup = radioGroup;
    }

    @Override
    public Fragment getItem(int position) {
        return TopTabFragmentFactory.createTopTabFragment(position);
    }

    @Override
    public int getCount() {
        return mRadioGroup.getChildCount();
    }
}
