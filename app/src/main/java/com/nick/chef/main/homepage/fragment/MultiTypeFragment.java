package com.nick.chef.main.homepage.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.nick.chef.MainActivity;
import com.nick.chef.R;
import com.nick.chef.api.Constants;
import com.nick.chef.bean.HomePageBean;
import com.nick.chef.main.homepage.activity.DetailActivity;
import com.nick.chef.main.homepage.adapter.HomePageAdapter;
import com.nick.chef.main.homepage.adapter.HumanityAdapter;
import com.nick.chef.main.homepage.adapter.LoreAdapter;
import com.nick.chef.main.homepage.adapter.MapAdapter;
import com.nick.chef.main.homepage.adapter.TopAdaper;
import com.nick.chef.utils.OKHttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import okhttp3.Call;

/**
 * Created by FG on 2016/10/28.
 */

public class MultiTypeFragment extends Fragment {
    private int mTabId;//用于区分是哪个tab
    private int mPage=1; //用于指示展示的页数
    private RecyclerView mRecyclerView;
    private String mUrl;
    private PtrFrameLayout pullDown_Refresh;
    private TopAdaper topAdaper;
    private LoreAdapter loreAdapter;
    private HumanityAdapter humanityAdapter;
    private MapAdapter mapAdapter;
    private Handler handler = new Handler();
    private List<HomePageBean.DatasBean.ArticleListBean> mDataSource=new ArrayList<HomePageBean.DatasBean.ArticleListBean>();
    public static Fragment getInstance(int tabId){
        MultiTypeFragment fragment=new MultiTypeFragment();
        Log.i("666666","实例化多类型碎片");
        Bundle bundle=new Bundle();
        bundle.putInt("tabId",tabId);
        Log.i("666666","传递fragment参数"+tabId);
        fragment.setArguments(bundle);
        Log.i("666666","返回本类对象");
        return fragment;//返回本类对象                                                                                                                                                                                                                                                                         - x信息回本类对象、“ ，
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments=getArguments();
        mTabId=arguments.getInt("tabId");
        Log.i("666666","onCreate（）获取参数"+mTabId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_multype,container,false);
        pullDown_Refresh= (PtrFrameLayout) view.findViewById(R.id.ptr_down);
        Log.i("666666","onCreateView（）页面"+(mTabId-1));
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("666666","onViewCreated（）");
        mRecyclerView= (RecyclerView) view.findViewById(R.id.recycler_view);
        //从网络获取到数据源，实例化adapter，并设置给recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //根据tab的类型来实例化与之相匹配的adapter
        switch (mTabId){
            case 2:
                topAdaper=new TopAdaper(getActivity(),mRecyclerView);
                mRecyclerView.setAdapter(topAdaper);
                Log.i("666666","onViewCreated（）---给页面1绑定适配器");
                //设置item的点击监听器
                topAdaper.setOnItemClickListener(new TopAdaper.OnItemClickListener() {
                    @Override
                    public void onItemClickListener(String articleId) {
                        Intent intent = new Intent(getActivity(),DetailActivity.class);
                        intent.putExtra("mTabId",mTabId);//后加的
                        intent.putExtra("articleId",articleId);
                        startActivity(intent);
                    }
                });
                //设置加载更多的监听器
                topAdaper.setOnMoreDataLoadListener(new TopAdaper.LoadMoreDataListener() {
                    @Override
                    public void loadMoreData() {
                        Log.i("20155555","滑到滴滴滴滴滴滴滴滴滴了");
                        //mDataSource是全局变量，存有上次加载的数据
                        mDataSource.clear();//之前少了这一句，全局变量的数据源是还没滑到底之前有了10个数据，所以，前10个item会出现两次
                        mDataSource.add(null);
                        topAdaper.setDataSource(mDataSource,false);//给adapter设置一个空数据源，为了让了让进度条出现
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //移除刷新的progressBar
                                mPage++;
                                requesteDataSource(true,mPage);
                            }
                        },2000);
                    }
                });
                break;
            case 3:
                loreAdapter=new LoreAdapter(getActivity(),mRecyclerView);
                mRecyclerView.setAdapter(loreAdapter);
                Log.i("666666","onViewCreated（）---给页面2绑定适配器");
                loreAdapter.setOnItemClickListener(new LoreAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClickListener(String articleId) {
                        Intent intent = new Intent(getActivity(), DetailActivity.class);
                        intent.putExtra("mTabId",mTabId);
                        intent.putExtra("articleId",articleId);
                        startActivity(intent);
                    }
                });
                loreAdapter.setOnMoreDataLoadListener(new LoreAdapter.LoadMoreDataListener() {
                    @Override
                    public void loadMoreData() {
                        mPage++;
                        requesteDataSource(true,mPage);
                    }
                });
                break;
            case 4:
                humanityAdapter=new HumanityAdapter(getActivity(),mRecyclerView);
                humanityAdapter.setOnItemClickListener(new HumanityAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClickListener(String articleId) {
                        Intent intent = new Intent(getActivity(), DetailActivity.class);
                        intent.putExtra("mTabId",mTabId);
                        intent.putExtra("articleId",articleId);
                        startActivity(intent);
                    }
                });
                humanityAdapter.setOnMoreDataLoadListener(new HumanityAdapter.LoadMoreDataListener() {
                    @Override
                    public void loadMoreData() {
                        mPage++;
                        requesteDataSource(true,mPage);
                    }
                });
                Log.i("666666","onViewCreated（）---给页面3绑定适配器");
                break;
            case 5:
                mapAdapter=new MapAdapter(getActivity(),mRecyclerView);
                mapAdapter.setOnItemClickListener(new MapAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClickListener(String articleId) {
                        Intent intent = new Intent(getActivity(), DetailActivity.class);
                        intent.putExtra("mTabId",mTabId);
                        intent.putExtra("articleId",articleId);
                        startActivity(intent);
                    }
                });
                mapAdapter.setOnMoreDataLoadListener(new MapAdapter.LoadMoreDataListener() {
                    @Override
                    public void loadMoreData() {
                        mPage++;
                        requesteDataSource(true,mPage);
                    }
                });
                Log.i("666666","onViewCreated（）---给页面4绑定适配器");
                break;
        }
        initPdTRefreash();//初始化下拉刷新界面所需要的参数
        //进入界面就自动加载
        requesteDataSource(false,mPage);//这个是true，会报空指针异常
      //  Log.i("OOOOOO",mDataSource.size()+""); 为什么出来就是空的了，啊啊啊啊啊
    }

    private void initPdTRefreash() {
        StoreHouseHeader header = new StoreHouseHeader(getActivity());
        header.initWithString("i COOKING");
        header.setTextColor(Color.RED);
        pullDown_Refresh.setHeaderView(header);
        pullDown_Refresh.addPtrUIHandler(header);
        pullDown_Refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //刷新逻辑
                mPage=1;
                requesteDataSource(false,mPage);
                pullDown_Refresh.refreshComplete();
            }
        });
    }
    /*为Fragment的适配器请求网络数据，获得数据源，数据源是App界面中的图片，文字的来源*/
    private void requesteDataSource(boolean loadMore,int page) {
        //mTabId就是传入的2,3,4,5，它们也是获得与之对应Fragment的API接口（url）的重要参数
        mUrl= Constants.getHomeUrl(mTabId,page);//传入mTabId，获取访问网络的url
        if (!loadMore) {//下拉刷新
            //使用Url和OKHttp网络请求框架来获取网络上的数据
            OKHttpUtils.newInstance(getActivity()).getAsyncData(mUrl, new OKHttpUtils.OnReusltListener() {
                @Override
                public void onFailure(Call call, IOException e) {

                }
                @Override                       //请求到的字符串类型的网络数据，本质是Json字符串
                public void onSucces(Call call, String response) {
                    //把response这个Json字符串转化为实体类，方便我们使用其中的数据
                    HomePageBean homePageBean = new Gson().fromJson(response, HomePageBean.class);
                    mDataSource = homePageBean.getDatas().getArticle_list();//数据源到
                    switch (mTabId) {
                        case 2:
                            topAdaper.setDataSource(mDataSource,true);
                            break;
                        case 3:
                            loreAdapter.setDataSource(mDataSource,true);
                            break;
                        case 4:
                            humanityAdapter.setDataSource(mDataSource,true);
                            mRecyclerView.setAdapter(humanityAdapter);
                            break;
                        case 5:
                            mapAdapter.setDataSource(mDataSource,true);
                            mRecyclerView.setAdapter(mapAdapter);
                    }

                }
            });

        }else {
            OKHttpUtils.newInstance(getActivity()).getAsyncData(mUrl, new OKHttpUtils.OnReusltListener() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onSucces(Call call, String response) {
                    HomePageBean homePageBean = new Gson().fromJson(response, HomePageBean.class);
                    mDataSource = homePageBean.getDatas().getArticle_list();
                    switch (mTabId) {
                        case 2:
                            topAdaper.removeProgressBar();
                            topAdaper.setDataSource(mDataSource,false);
                            topAdaper.setLoaded();
                            break;
                        case 3:
                            loreAdapter.setDataSource(mDataSource,false);
                            loreAdapter.setLoaded();
                            break;
                        case 4:
                            humanityAdapter.setDataSource(mDataSource,false);
                            humanityAdapter.setLoaded();
                            break;
                        case 5:
                            mapAdapter.setDataSource(mDataSource,false);
                            mapAdapter.setLoaded();
                            break;
                    }

                }
            });
        }
    }



}
