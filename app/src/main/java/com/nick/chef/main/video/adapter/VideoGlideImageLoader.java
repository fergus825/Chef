package com.nick.chef.main.video.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * *********************************************************************
 * Author: Nick
 * Created on 2016/10/28  11:56
 * desc:
 * <p>
 * *********************************************************************
 */

public class VideoGlideImageLoader implements ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }
}
