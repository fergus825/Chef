package com.nick.chef.main.video.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.nick.chef.R;
import com.nick.chef.api.Constants;
import com.nick.chef.bean.VideoChannelBean;
import com.nick.chef.main.video.adapter.VideoBAdapter;
import com.nick.chef.main.video.adapter.VideoChannelAdapter;
import com.nick.chef.utils.OKHttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class ChannelActivity extends AppCompatActivity {


    @BindView(R.id.video_channel_recyclerview)
    RecyclerView mVideoChannelRecyclerview;
    private LinearLayoutManager mLinearLayoutManager;

    private Map<String, String> mMap;
    private int mPage = 1;
    private VideoChannelAdapter mAdapter;
    private String mId ;

    boolean isLoadingMore = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        mId = intent.getStringExtra("id");

        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mVideoChannelRecyclerview.setLayoutManager(mLinearLayoutManager);

        mAdapter = new VideoChannelAdapter(this);
        loadData(mPage);
        mVideoChannelRecyclerview.setAdapter(mAdapter);


        mAdapter.setOnItemClickListener(new VideoBAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String vurl, View view) {
                Intent intent = new Intent(ChannelActivity.this,VideoActivity.class);
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
        mMap.put("lat", "");
        mMap.put("id", mId);
        mMap.put("page", page+"");
        mMap.put("format", "json");
        //Toast.makeText(getActivity(), "besuccess", Toast.LENGTH_SHORT).show();
        OKHttpUtils.newInstance(this).postAsnycData(mMap, Constants.VIDEO_CHANNEL_URL, new OKHttpUtils.OnReusltListener() {
            @Override
            public void onFailure(Call call, IOException e) {
                //Toast.makeText(), "net err", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucces(Call call, String response) {
                VideoChannelBean bean = new Gson().fromJson(response, VideoChannelBean.class);
                mAdapter.setTitle(bean.getObj().getImg(), bean.getObj().getName(), bean.getObj().getDescribtion());
                if (bean.getObj().getVideo_list() != null) {
                    mAdapter.setChannelList(bean.getObj().getVideo_list());
                    if (bean.getObj().getVideo_list().size() < 10) {
                        isLoadingMore = false;
                    }

                }




            }
        });


    }
}
