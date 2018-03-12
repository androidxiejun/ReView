package com.example.refragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by XJ on 2018/2/24 0024.
 */

public class MyAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;
    public MyAdapter(FragmentManager fm, List<Fragment>list) {
        super(fm);
        fragmentList=list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList==null?0:fragmentList.size();
    }
}
