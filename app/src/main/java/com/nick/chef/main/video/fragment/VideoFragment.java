package com.nick.chef.main.video.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nick.chef.R;
import com.nick.chef.main.video.adapter.VideoFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * *********************************************************************
 * Author: Nick
 * Created on 2016/10/27  11:02
 * desc:
 * <p>
 * *********************************************************************
 */

public class VideoFragment extends Fragment {

    @BindView(R.id.video_vp_container)
    ViewPager mVideoVpContainer;
    @BindView(R.id.video_tl)
    TabLayout mVideoTl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.videofragment_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Fragment> fragments = new ArrayList<>();
        Fragment videoInnerFragmentB = new VideoInnerFragmentB();
        Fragment videoInnerFragmentC = new VideoInnerFragmentC();

        fragments.add(VideoInnerFragment.getNewInstance(0));
        fragments.add(videoInnerFragmentB);
        fragments.add(videoInnerFragmentC);



//        for (int i = 0; i < 2; i++) {
//            fragments.add(VideoInnerFragment.getNewInstance(i));
//        }
//        fragments.add(VideoInnerFragment.getNewInstance(1));


        VideoFragmentAdapter adapter = new VideoFragmentAdapter(getChildFragmentManager(),fragments);

        mVideoVpContainer.setAdapter(adapter);

        mVideoTl.setTabGravity(TabLayout.GRAVITY_CENTER);
        mVideoTl.setupWithViewPager( mVideoVpContainer);
        mVideoTl.setSelectedTabIndicatorHeight(0);
        mVideoTl.setTabTextColors(Color.parseColor("#dedede"),Color.parseColor("#ffffff"));
        mVideoTl.setBackgroundColor(Color.parseColor("#FFCE42"));


    }
}
