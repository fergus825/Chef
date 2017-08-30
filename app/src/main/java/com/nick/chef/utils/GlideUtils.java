package com.nick.chef.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nick.chef.R;

/**
 * ***********************************************************
 * Created by Administrator on 2016/10/29.
 * name:
 * desc:
 * step:
 * *************************************************************
 */
public class GlideUtils {
    public static void glide(Context context, String url, ImageView imageView){
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.wait)
                .error(R.mipmap.wait)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}
