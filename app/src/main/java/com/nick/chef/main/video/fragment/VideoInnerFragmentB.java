package com.nick.chef.main.video.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nick.chef.R;
import com.nick.chef.api.Constants;
import com.nick.chef.bean.VideoLinearBeanB;
import com.nick.chef.main.video.activity.VideoActivity;
import com.nick.chef.main.video.adapter.VideoBAdapter;
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
 * Created on 2016/10/29  9:33
 * desc:
 * <p>
 * *********************************************************************
 */

public class VideoInnerFragmentB extends Fragment {


    @BindView(R.id.video_recyclerview)
    RecyclerView mVideoRecyclerview;
    @BindView(R.id.video_ptr)
    PtrFrameLayout mVideoPtr;
    private VideoBAdapter mAdapter;

    private Map<String, String> mMap;
    private int mPage = 1;
    private boolean isLoadingMore = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.videoinnerfragment, container, false);

        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPtr();
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false);
        mVideoRecyclerview.setLayoutManager(linearLayoutManager);
        mAdapter = new VideoBAdapter(getActivity());

        loadData(1);
        mVideoRecyclerview.setAdapter(mAdapter);

        //滑动监听
        mVideoRecyclerview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = linearLayoutManager.getItemCount();
                if (mPage < 12 && lastVisibleItem >= totalItemCount - 3 &&!isLoadingMore ) {

//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
                    loadData(++mPage);

//                        }
//                    },2000);
                    isLoadingMore = true;

                    if (mPage == 13) {
                        Toast.makeText(getActivity(), "end", Toast.LENGTH_SHORT).show();
                    }
                }




            }
        });

        mAdapter.setOnItemClickListener(new VideoBAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String vurl, View view) {
                Intent intent = new Intent(getActivity(), VideoActivity.class);
                intent.putExtra("vurl", vurl);
                startActivity(intent);

            }
        });






    }

    /**
     * 网络请求获取数据
     */
    private void loadData(int page) {
        mMap = new HashMap<>();
        mMap.put("lon", "");
        mMap.put("source", "android");
        mMap.put("sort","2");
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
                    VideoLinearBeanB linearBeanB = new Gson().fromJson(response, VideoLinearBeanB.class);
                    mAdapter.setVideoDataListB(linearBeanB.getObj().getVideo_list());
                isLoadingMore = false;

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
