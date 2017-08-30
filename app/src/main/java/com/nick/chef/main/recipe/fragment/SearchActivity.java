package com.nick.chef.main.recipe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.nick.chef.R;
import com.nick.chef.api.Constants;
import com.nick.chef.bean.RecipeBean;
import com.nick.chef.main.recipe.adapter.RecipeAdapter;
import com.nick.chef.utils.OKHttpUtils;
import com.nick.chef.utils.ToastUtil;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;


/**
 * ***********************************************************
 * Created by Administrator on 2016/10/31.
 * name:
 * desc:
 * step:
 * *************************************************************
 */
public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.rv_search)
    RecyclerView mRvSearch;
    private String mUrl;
    private RecipeAdapter mAdapter;
    private List<RecipeBean.ResultBean.DataBean> mSerachBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        String key = getIntent().getStringExtra("key");
        mUrl = Constants.getRecipeSearchRst(key);
        initDatas();
        mRvSearch.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        mAdapter = new RecipeAdapter(this);
        setOnlistener();
    }

    /**
     * 设置item的监听器
     */
    private void setOnlistener() {
        if (mAdapter == null) {
            return;
        }
        mAdapter.setOnItemClickListener(new RecipeAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position, View view) {
                // 处理被点击的item的逻辑
                ToastUtil.showToast(SearchActivity.this, "我是" + position);
                String id = mSerachBean.get(position).getId();
                Intent intent = new Intent(SearchActivity.this, RecipeDetailActivity.class);
                intent.putExtra("position",id);
                startActivity(intent);
            }
        });
    }
    private void initDatas() {
        OKHttpUtils.newInstance(this).getAsyncData(mUrl, new OKHttpUtils.OnReusltListener() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onSucces(Call call, String response) {
                RecipeBean bean = new Gson().fromJson(response, RecipeBean.class);
                mSerachBean = bean.getResult().getData();
                mAdapter.setDatas(mSerachBean);
                mRvSearch.setAdapter(mAdapter);
            }
        });
    }
}
