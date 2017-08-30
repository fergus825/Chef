package com.nick.chef.main.video.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nick.chef.R;
import com.nick.chef.api.Constants;
import com.nick.chef.bean.VideoGridBean;
import com.nick.chef.main.video.activity.ChannelActivity;
import com.nick.chef.main.video.adapter.VideoCAdapter;
import com.nick.chef.utils.OKHttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import okhttp3.Call;

/**
 * *********************************************************************
 * Author: Nick
 * Created on 2016/10/29  10:50
 * desc:
 * <p>
 * *********************************************************************
 */

public class VideoInnerFragmentC extends Fragment {
    @BindView(R.id.video_recyclerview)
    RecyclerView mVideoRecyclerview;
    @BindView(R.id.video_ptr)
    PtrFrameLayout mVideoPtr;

    private Map<String, String> mMap;
    private VideoCAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.videoinnerfragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPtr();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        mVideoRecyclerview.setLayoutManager(gridLayoutManager);
        mAdapter = new VideoCAdapter(getActivity());


        mVideoRecyclerview.setAdapter(mAdapter);

        loadData(1);
        mAdapter.setOnItemClickListener(new VideoCAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String id, View view) {
                Intent intent = new Intent(getActivity(), ChannelActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);

            }
        });

        for (int i = 2; i < 6; i++) {
            loadData(i);

        }








    }


    /**
     * 网络请求获取数据
     */
    private void loadData(int page) {
        mMap = new HashMap<>();
        mMap.put("lon", "");
        mMap.put("source", "android");
        mMap.put("sort","3");
        mMap.put("lat", "");
        mMap.put("page", page+"");
        mMap.put("format", "json");
        //Toast.makeText(getActivity(), "besuccess", Toast.LENGTH_SHORT).show();
        OKHttpUtils.newInstance(getActivity()).postAsnycData(mMap, Constants.VIDEO_BASE_URL, new OKHttpUtils.OnReusltListener() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getActivity(), "net err", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucces(Call call, String response) {
                VideoGridBean bean = new Gson().fromJson(response, VideoGridBean.class);
                mAdapter.setVideoDataListC(bean.getObj().getRecommend_list());

            }
        });


    }
    /**
     * 下拉刷新
     */
    private void initPtr() {
        StoreHouseHeader header = new StoreHouseHeader(getActivity());
        header.initWithString("i COOKING");
        header.setTextColor(Color.RED);
        mVideoPtr.setHeaderView(header);
        mVideoPtr.addPtrUIHandler(header);
        mVideoPtr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                loadData(1);
                mVideoPtr.refreshComplete();

            }
        });


    }
}

