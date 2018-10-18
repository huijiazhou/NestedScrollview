package com.zhj.cau.nestedscrollview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by zhj on 2018/10/8.
 */

public class TabAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentListView;
    private List<String> list_title;

    public TabAdapter(FragmentManager fm, List<Fragment> fragmentListView, List<String> list_title) {
        super(fm);
        this.mFragmentListView = fragmentListView;
        this.list_title = list_title;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentListView.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentListView.size();
    }

    //此方法tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {
        return list_title.get(position);
    }
}
