package com.nick.chef.main.homepage.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.nick.chef.R;
import com.nick.chef.api.Constants;
import com.nick.chef.bean.HomeItemDetailBean;
import com.nick.chef.utils.FontColorChanger;
import com.nick.chef.utils.OKHttpUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.PublicKey;

import okhttp3.Call;



/**
 * Created by FG on 2016/10/29.
 */

public class DetailActivity extends Activity{
    private WebView mWvDetail;
    private ImageView mBack,mShare;
    private String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_details);
        FontColorChanger.setWindowStatusBarColor(this,R.color.statudBarBG);//改变状态栏颜色
        FontColorChanger.MIUISetStatusBarLightMode(getWindow(),true);//改变字体颜色为黑色
        mWvDetail= (WebView) findViewById(R.id.wv_detail);
        mBack= (ImageView) findViewById(R.id.iv_back);
        mShare= (ImageView) findViewById(R.id.iv_share);
       // 能够响应JavaScript
//      mWvDetail.getSettings().setJavaScriptEnabled(true);

        Intent intent = getIntent();
        int tabId=intent.getIntExtra("mTabId",0);
        String articleId = intent.getStringExtra("articleId");
        url= Constants.getHomeItemDetail(tabId,Integer.parseInt(articleId));
        mWvDetail.loadUrl(url);
       /* //换了url之后
        OKHttpUtils.newInstance(this).getAsyncData(url, new OKHttpUtils.OnReusltListener() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onSucces(Call call, String response) {
                final HomeItemDetailBean homeItemDetailBean = new Gson().fromJson(response, HomeItemDetailBean.class);
                //把网页数据的Url加载到名为WebView的控件当中
                mWvDetail.loadUrl(homeItemDetailBean.getDatas().getArticle_content());
            }
        });*/

    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
               /* OKHttpUtils.newInstance(this).getAsyncData(url, new OKHttpUtils.OnReusltListener() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onSucces(Call call, String response) {
                        final HomeItemDetailBean homeItemDetailBean = new Gson().fromJson(response, HomeItemDetailBean.class);
                        Intent shareIntent = new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND);
                        shareIntent.putExtra(Intent.EXTRA_TEXT, "给各位吃货们的一个福利，看链接————"+homeItemDetailBean.getDatas().getArticle_content());
                        shareIntent.setType("text/plain");
                        //设置分享列表的标题，并且每次都显示分享列表
                        startActivity(Intent.createChooser(shareIntent, "分享到"));
                    }
                });*/
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "给各位吃货们的一个福利，看链接————"+url);
                shareIntent.setType("text/plain");
                //设置分享列表的标题，并且每次都显示分享列表
                startActivity(Intent.createChooser(shareIntent, "分享到"));

                break;
        }
    }

}
