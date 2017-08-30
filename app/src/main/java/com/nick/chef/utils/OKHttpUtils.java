package com.nick.chef.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * ***********************************************************
 * author: alex
 * time: 16/10/13 下午4:24
 * name:
 * desc:
 * step:
 * *************************************************************
 */
public class OKHttpUtils {

    private static OKHttpUtils mOKHttpUtils;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;

    private OKHttpUtils(Context context) {
        //TODO do sth

        mHandler = new Handler(Looper.getMainLooper());
        int cacheSize = 10 * 1024 * 1024; //10M
        int cache = 10 << 20; //10M
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS) //连接超时时间
                .readTimeout(20, TimeUnit.SECONDS) //读取超时时间
                .writeTimeout(20, TimeUnit.SECONDS) //写入超时时间
                .cache(new Cache(context.getCacheDir(), cache))//开启缓存
                .build();
    }

    /**
     * 单例化
     *
     * @return
     */
    public static OKHttpUtils newInstance(Context context) {
        if (mOKHttpUtils == null) {
            synchronized (OKHttpUtils.class) {
                if (mOKHttpUtils == null) {
                    mOKHttpUtils = new OKHttpUtils(context);
                }
            }
        }
        return mOKHttpUtils;
    }

    //=======================================================================
    // GET异步方式
    //  因为结果集也就是,自带的两个回调方法是在子线程中执行的,所以每次我们调用都得发送到主线程
    // 1. 把结果集想办法发送到主线程中   ---> Hanlder
    // 2. 如何把结果集返回给调用者   ---> 接口回调方式
    //=======================================================================
    public void getAsyncData(String url, final OnReusltListener listener) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null)
                            listener.onFailure(call, e);
                    }
                });

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final String result = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null)
                            listener.onSucces(call, result);
                    }
                });
            }
        });
    }
    public void getData(String url, final OnReusltListener listener) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {


                        if (listener != null)
                            listener.onFailure(call, e);

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final String result = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null)
                            listener.onSucces(call, result);
                    }
                });
            }
        });
    }
    //=======================================================================
    // Post请求方式
    //=======================================================================
    public void postAsnycData(Map<String, String> map, String url, final OnReusltListener listener) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        RequestBody requestBody = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null) {
                            listener.onFailure(call, e);
                        }
                    }
                });
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final String result = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null) {
                            listener.onSucces(call, result);
                        }
                    }
                });
            }
        });
    }

    //=======================================================================
    // 下载
    //=======================================================================
    public void downloadData(final String url, final String filepath) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);



        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) {
                InputStream is = null;
                byte[] buffer = new byte[1024];
                int length = 0;
                FileOutputStream fos = null;

                try {
                    is = response.body().byteStream();
                    File file = new File(filepath, getName(url));
                    fos = new FileOutputStream(file);
                    while ((length = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, length);
                    }
                    fos.flush();
                } catch (IOException e) {

                } finally {

                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }


    /**
     * 通过url获取名字
     *
     * @param path
     * @return
     */
    public String getName(String path) {
        int indexOf = path.lastIndexOf("/");
        if (indexOf < 0) {
            return path;
        } else {
            return path.substring(indexOf + 1);
        }
    }

    //返回给调用者
    public interface OnReusltListener {
        void onFailure(Call call, IOException e);

        void onSucces(Call call, String response);

    }


    /**
     * 取消对应tag值的请求
     *
     * @param tag
     */
    public void cancelTag(Object tag) {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }

        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }


}
