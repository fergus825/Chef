package com.nick.chef.main.recipe.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nick.chef.R;
import com.nick.chef.api.Constants;
import com.nick.chef.bean.RecipeDetailBean;
import com.nick.chef.main.recipe.GlideUtils;
import com.nick.chef.main.recipe.SQL.Recipe;
import com.nick.chef.main.recipe.SQL.RecipeDao;
import com.nick.chef.utils.OKHttpUtils;
import com.nick.chef.utils.ToastUtil;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * ***********************************************************
 * Created by Jerry on 2016/10/28.
 * name: 菜品详情页
 * desc:
 * step:
 * *************************************************************
 */
public class RecipeDetailActivity extends AppCompatActivity {

    @BindView(R.id.img_recipe_detail_albums)
    ImageView mImgRecipeDetailAlbums;
    @BindView(R.id.txt_recipe_detail_name)
    TextView mTxtRecipeDetailName;
    @BindView(R.id.txt_recipe_detail_tags)
    TextView mTxtRecipeDetailTags;
    @BindView(R.id.txt_recipe_detail_burden)
    TextView mTxtRecipeDetailBurden;
    @BindView(R.id.txt_recipe_detail_ingredients)
    TextView mTxtRecipeDetailIngredients;
    @BindView(R.id.ll_recipe_detail)
    LinearLayout mLlRecipeDetail;
    @BindView(R.id.recipe_detail_fab)
    FloatingActionButton mRecipeDetailFab;

    //详情的id
    private String mPos;
    private RecipeDetailBean.ResultBean.DataBean mBean;
    private RecipeDao mDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipedetail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mPos = intent.getStringExtra("position");
        ToastUtil.showToast(this, mPos);
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        int id = Integer.parseInt(mPos);
        OKHttpUtils.newInstance(this).getAsyncData(Constants.getRecipeDetail(id), new OKHttpUtils.OnReusltListener() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onSucces(Call call, String response) {
                RecipeDetailBean recipeDetailBean = new Gson().fromJson(response, RecipeDetailBean.class);
                mBean = recipeDetailBean.getResult().getData().get(0);
                String urlName = mBean.getAlbums().get(0);
                mTxtRecipeDetailName.setText(mBean.getTitle());
                mTxtRecipeDetailTags.setText("标签: " + mBean.getTags());
                mTxtRecipeDetailIngredients.setText("分量: " + mBean.getIngredients());
                mTxtRecipeDetailBurden.setText("食材: " + mBean.getBurden());
                GlideUtils.glide(RecipeDetailActivity.this, urlName, mImgRecipeDetailAlbums);
                List<RecipeDetailBean.ResultBean.DataBean.StepsBean> steps = mBean.getSteps();
                for (int i = 0; i < steps.size(); i++) {
                    ImageView imageView = new ImageView(RecipeDetailActivity.this);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    imageView.setPadding(8, 8, 8, 8);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 650);
                    mLlRecipeDetail.addView(imageView, layoutParams);
                    GlideUtils.glide(RecipeDetailActivity.this, steps.get(i).getImg(), imageView);
                    TextView textView = new TextView(RecipeDetailActivity.this);
                    textView.setPadding(8, 8, 8, 18);
                    textView.setTextSize(18);
                    mLlRecipeDetail.addView(textView);
                    textView.setText(steps.get(i).getStep());
                }
            }


        });

    }

    @OnClick(R.id.recipe_detail_fab)
    public void onClick() {
//        Snackbar.make(mRecipeDetailFab, "snackbar...", Snackbar.LENGTH_INDEFINITE).show();
        Snackbar snackbar = Snackbar.make(mRecipeDetailFab, "爱就任性，喜欢就收藏", Snackbar.LENGTH_INDEFINITE);

        //先获取到视图，在设置
        snackbar.getView().setBackgroundColor(Color.parseColor("#ffffff")); //设置背景颜色
        //通过查看源码知道id，findviewbyid()就可以设置属性了
        ((TextView) snackbar.getView().findViewById(R.id.snackbar_text)).setTextColor(Color.parseColor("#ff0000"));
//                ((TextView)snackbar.getView().findViewById(R.id.snackbar_action)).setTextColor(Color.parseColor("#0000ff"));
        snackbar.setAction("点击收藏", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //是否收藏，完成收藏的逻辑
//                Toast.makeText(RecipeDetailActivity.this, "已收藏", Toast.LENGTH_SHORT).show();
                insertData(); //收藏

            }
        })
                .setActionTextColor(Color.parseColor("#ff0000"))
                .show();
    }

    /**
     * 插入一条数据
     */
    private void insertData() {
        mDao = new RecipeDao(this);
        //TODO 获取当前时间，添加到bean对象中
        Recipe recipe = new Recipe(mBean.getTitle(), mBean.getTags(), mPos, mBean.getAlbums().get(0));

        if (! mDao.isExist(recipe)){
            mDao.add(recipe);
            ToastUtil.showToast(this,"收藏成功");
            startActivity(new Intent(RecipeDetailActivity.this,CollectActivity.class));
        }else {
            ToastUtil.showToast(this,"已经收藏了，快去我的收藏查看吧! 亲");
        }

    }
}
