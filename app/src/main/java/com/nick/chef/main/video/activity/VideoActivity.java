package com.nick.chef.main.video.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nick.chef.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoActivity extends AppCompatActivity {

    @BindView(R.id.video_webview)
    WebView mVideoWebview;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_refresh)
    ImageView mIvRefresh;
    @BindView(R.id.iv_share)
    ImageView mIvShare;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    private String mProgress;
    private String mUrlPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mUrlPath = intent.getStringExtra("vurl");
        initSetting();
        loadWeb(mUrlPath);


    }


    @OnClick({R.id.iv_back, R.id.iv_refresh, R.id.iv_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_refresh:
                loadWeb(mUrlPath);
                break;
            case R.id.iv_share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "[iCooking]点击链接，查看美食视频教程哦~ " + mUrlPath);
                shareIntent.setType("text/plain");

                //设置分享列表的标题，并且每次都显示分享列表
                startActivity(Intent.createChooser(shareIntent, "分享到"));
                break;
        }
    }


    private void initSetting() {
        WebSettings webSettings = mVideoWebview.getSettings();
        mVideoWebview.requestFocusFromTouch();//支持获取手势焦点，输入用户名、密码或其他
        mVideoWebview.requestFocus();//触摸焦点起作用

        webSettings.setJavaScriptEnabled(true);  //支持js 【重要】


        //TODO 设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true);  //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        WebChromeClient mWebChromeClient = new WebChromeClient() {
            //获得网页的加载进度，显示在右上角的TextView控件中
            @Override
            public void onProgressChanged(WebView mWebTest, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }

            }
        };
        mVideoWebview.setWebChromeClient(mWebChromeClient);
    }


    /**
     * 加载网页
     */
    private void loadWeb(String urlPath) {



        mVideoWebview.loadUrl(urlPath);
        mVideoWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }


}
