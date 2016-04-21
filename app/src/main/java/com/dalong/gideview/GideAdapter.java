package com.dalong.gideview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouweilong on 16/4/21.
 */
public class GideAdapter extends FragmentPagerAdapter {

    List<Fragment> mFragments = new ArrayList<Fragment>();

    public GideAdapter(FragmentManager fm,List<Fragment> mFragments) {
        super(fm);
        this.mFragments=mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
