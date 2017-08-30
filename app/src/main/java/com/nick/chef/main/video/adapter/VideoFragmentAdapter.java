package com.nick.chef.main.video.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nick.chef.main.video.fragment.VideoInnerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * *********************************************************************
 * Author: Nick
 * Created on 2016/10/27  17:23
 * desc:
 * <p>
 * *********************************************************************
 */

public class VideoFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    private String[] titeles = {"最新","最热","专辑"};

    public VideoFragmentAdapter(FragmentManager fm,List<Fragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titeles[position];
    }
}
