package com.nick.chef.main.recipe.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.nick.chef.R;
import com.nick.chef.api.Constants;
import com.nick.chef.bean.RecipeBean;
import com.nick.chef.main.recipe.adapter.RecipeAdapter;
import com.nick.chef.utils.OKHttpUtils;
import com.nick.chef.utils.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import okhttp3.Call;

/**
 * *********************************************************************
 * Author: Nick
 * Created on 2016/10/27  11:01
 * desc:
 * <p>
 * *********************************************************************
 */

public class RecipeFragment extends Fragment {
    @BindView(R.id.sv_recipe)
    SearchView mSvRecipe;
    @BindView(R.id.rv_recip)
    RecyclerView mRvRecip;
    @BindView(R.id.ptr_recipe)
    PtrFrameLayout mPtrRecipe;
    private RecipeAdapter mAdapter;
    int page = 1;
//    int random;
    boolean isRefresh = false;
    private List<RecipeBean.ResultBean.DataBean> mDataBeen;
    private List<RecipeBean.ResultBean.DataBean> mDatas = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        ButterKnife.bind(this, view);

        mRvRecip.setHasFixedSize(true);
        mRvRecip.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        initDatas(page);  //初始化
        mAdapter = new RecipeAdapter(getActivity());
        initPtr();  //下拉刷新
        setOnlistener();  //item的监听器
        mRvRecip.addOnScrollListener(mOnScrollListener);   //上拉加载

        mRvRecip.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();

        initSearch();   //搜索
        return view;
    }

    /**
     * 搜索
     */
    private void initSearch() {
        mSvRecipe.setSubmitButtonEnabled(true);
        mSvRecipe.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ToastUtil.showToast(getActivity(), "您的选择是" + query);
                //处理搜索的逻辑
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("key", query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    /**
     * 设置item的监听器
     * 详情页
     */
    private void setOnlistener() {
        if (mAdapter == null) {
            return;
        }
        mAdapter.setOnItemClickListener(new RecipeAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position, View view) {
                //处理被点击的item的逻辑
//                ToastUtil.showToast(getActivity(), "我是" + position);
                String id = mDatas.get(position).getId();    //
                Intent intent = new Intent(getActivity(), RecipeDetailActivity.class);
                intent.putExtra("position", id);
                startActivity(intent);
            }
        });
    }

    /**
     * 加载更多
     */
    private EndlessRecyclerOnScrollListener mOnScrollListener = new EndlessRecyclerOnScrollListener() {
        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);
            //添加刷新view
//            if (isRefresh == true) {
//                initDatas(++random);
//                isRefresh = false;
//            }
            initDatas(++page);

        }
    };

    /**
     * 下拉刷新
     */
    private void initPtr() {
        StoreHouseHeader header = new StoreHouseHeader(getActivity());
        header.initWithString("i COOKING");
        header.setTextColor(Color.RED);
        mPtrRecipe.setHeaderView(header);
        mPtrRecipe.addPtrUIHandler(header);
        mPtrRecipe.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //刷新逻辑
//                int random = (int) (Math.random() * 50 + 1);
                initDatas(2);
                mPtrRecipe.refreshComplete();
            }
        });
        isRefresh = true;
    }

    /**
     * 初始化
     *
     * @param page 请求那一页 数据
     */
    private void initDatas(int page) {
        String url = Constants.getNewRecipeUrl(page);
        OKHttpUtils.newInstance(getActivity()).getAsyncData(url, new OKHttpUtils.OnReusltListener() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onSucces(Call call, String response) {
                RecipeBean recipeBean = new Gson().fromJson(response, RecipeBean.class);
                mDataBeen = recipeBean.getResult().getData();
                mDatas.addAll(mDataBeen);
                mAdapter.setDatas(mDataBeen);
            }
        });
    }
}
