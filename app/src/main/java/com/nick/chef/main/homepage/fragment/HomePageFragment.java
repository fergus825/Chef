package com.nick.chef.main.homepage.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nick.chef.R;
import com.nick.chef.main.homepage.adapter.HomePageAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * *********************************************************************
 * Author: Nick
 * Created on 2016/10/27  10:59
 * desc:
 * <p>
 * *********************************************************************
 */

public class HomePageFragment extends Fragment {
    private List<Fragment> mFragments;
    public HomePageFragment(){}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= LayoutInflater.from(getActivity()).inflate(R.layout.fragement_homepage,container,false);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tl_container = (TabLayout) view.findViewById(R.id.tl_container);
        ViewPager vp_container = (ViewPager) view.findViewById(R.id.vp_container);
        mFragments=new ArrayList<Fragment>();//用于存放四个Fragment的集合
        for (int i=1;i<5;i++){
            //传入2,3，4,5，分别获得榜单、知识、人文、地图 展示界面的Fragment
            Log.i("666666","执行初始化Fragment集合的操作");
            mFragments.add(MultiTypeFragment.getInstance(i+1));
        }
        vp_container.setAdapter(new HomePageAdapter(getChildFragmentManager(),mFragments));
        //美化标签栏
        tl_container.setTabGravity(TabLayout.GRAVITY_CENTER);
        tl_container.setupWithViewPager(vp_container);
        tl_container.setSelectedTabIndicatorHeight(0);
        tl_container.setTabTextColors(Color.parseColor("#dedede"),Color.parseColor("#ffffff"));
        tl_container.setBackgroundColor(Color.parseColor("#FFCE42"));
    }
}
