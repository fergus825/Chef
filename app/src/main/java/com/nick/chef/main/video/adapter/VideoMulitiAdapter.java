package com.nick.chef.main.video.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.nick.chef.R;
import com.nick.chef.bean.VideoJump;
import com.nick.chef.bean.VideoLinearBean;
import com.nick.chef.bean.VideoLinearBeanB;
import com.nick.chef.main.video.activity.VideoActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * *********************************************************************
 * Author: Nick
 * Created on 2016/10/27  20:28
 * desc: linearlayout适配器
 * <p>
 * *********************************************************************
 */

public class VideoMulitiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private int mIndex;



    /**
     * item类型
     */
    private static final int TYPE_TITLE = 0;
    private static final int TYPE_NORMAL = 1;
    /**
     * banner图片源
     */
    private List<String> mImageUrlList;
    /**
     * VideoList数据源
     */
    private List<VideoLinearBean.ObjBean.VideoListBean> mVideoDataList;
    private List<VideoLinearBeanB.ObjBean.VideoListBean> mVideoDataListB;
    private List<VideoJump> mBannerJumpList;

    public VideoMulitiAdapter(Context context, int index) {

        mContext = context;
        mIndex = index;
    }


    @Override
    public int getItemViewType(int position) {
//        if (position == 0)
//            return TYPE_TITLE;
//        return TYPE_NORMAL;

        if (mIndex == 0) { //第一个tab界面
            if (position == 0) {
                return TYPE_TITLE;
            } else {
                return TYPE_NORMAL;
            }
        } else if (mIndex == 1) {//第二个tab界面
            return TYPE_NORMAL;
        }
        return 521;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TITLE:
                View titleview = LayoutInflater.from(mContext).inflate(R.layout.videoitem_header, parent, false);
                return new TitleViewHolder(titleview);

            case TYPE_NORMAL:
                View contentview = LayoutInflater.from(mContext).inflate(R.layout.videoitem_content, parent, false);
                return new ContentViewHolder(contentview);


        }


        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case TYPE_TITLE:
                TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
                titleViewHolder.mVideoBanner.setImageLoader(new VideoGlideImageLoader());
                titleViewHolder.mVideoBanner.setImages(mImageUrlList);
                titleViewHolder.mVideoBanner.setOnBannerClickListener(new OnBannerClickListener() {
                    @Override
                    public void OnBannerClick(int bannerposition) {
                        Intent intent = new Intent(mContext, VideoActivity.class);
                        intent.putExtra("vurl",mBannerJumpList.get(bannerposition-1).getProperty().getUrlString());
                        mContext.startActivity(intent);
                    }
                });
                titleViewHolder.mVideoBanner.start();





                break;
            case TYPE_NORMAL:
                ContentViewHolder contentHolder = (ContentViewHolder) holder;

                if (mIndex==0) {
                    position = position - 1;
                    //

                    contentHolder.mVideoTvName.setText(mVideoDataList.get(position).getName());
                    contentHolder.mVideoTvDescribtion.setText(mVideoDataList.get(position).getDescribtion());
                    contentHolder.mVideoTvUpdate.setText(mVideoDataList.get(position).getTime());
                    contentHolder.mVideoTvPlaytime.setText(mVideoDataList.get(position).getPlay_times());
                    Glide.with(mContext).load(mVideoDataList.get(position).getImg_video())
                            .diskCacheStrategy(DiskCacheStrategy.RESULT)
                            .into(contentHolder.mVideoImgMain);
                    Glide.with(mContext).load(mVideoDataList.get(position).getImg()).into(contentHolder.mVideoImgTitleicon);
                    final int finalPosition = position;
                    contentHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mListener.onItemClick(mVideoDataList.get(finalPosition).getVurl(),v);
                        }
                    });


                }

//                if (mIndex == 1) {
//                    contentHolder.mVideoTvName.setText(mVideoDataListB.get(position).getName());
//                    contentHolder.mVideoTvDescribtion.setText(mVideoDataListB.get(position).getDescribtion());
//                    contentHolder.mVideoTvUpdate.setText(mVideoDataListB.get(position).getTime());
//                    contentHolder.mVideoTvPlaytime.setText(mVideoDataListB.get(position).getPlay_times());
//                    Glide.with(mContext).load(mVideoDataListB.get(position).getImg_video()).into(contentHolder.mVideoImgMain);
//                    Glide.with(mContext).load(mVideoDataListB.get(position).getImg()).into(contentHolder.mVideoImgTitleicon);
//
//                }

                break;
        }


    }

    @Override
    public int getItemCount() {
      //  return mVideoDataList==null?0:mVideoDataList.size();
        //return 0;
        if (mIndex == 0) {
            return mVideoDataList != null ? mVideoDataList.size()+1 : 0;
        } else {
            return mVideoDataList != null ? mVideoDataList.size() : 0;
        }
    }


    static class TitleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.video_banner)
        Banner mVideoBanner;

        public TitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

//        ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
    }

    static class ContentViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.video_img_titleicon)
        ImageView mVideoImgTitleicon;
        @BindView(R.id.video_tv_name)
        TextView mVideoTvName;
        @BindView(R.id.video_tv_update)
        TextView mVideoTvUpdate;
        @BindView(R.id.video_img_main)
        ImageView mVideoImgMain;
        @BindView(R.id.video_tv_describtion)
        TextView mVideoTvDescribtion;
        @BindView(R.id.video_tv_playtime)
        TextView mVideoTvPlaytime;

        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

//        ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
    }

    /**
     * 设置Banner图片源
     */
    public void setBannnerSource(List<VideoLinearBean.ObjBean.AdBean> imgList) {
        if (mImageUrlList == null)
            mImageUrlList = new ArrayList<>();
        if (mBannerJumpList==null)
            mBannerJumpList = new ArrayList<>();

            mImageUrlList.clear();
            mBannerJumpList.clear();

            //遍历将url存入数组，供banner使用
            for (VideoLinearBean.ObjBean.AdBean adBean : imgList) {
            mImageUrlList.add(adBean.getPhoto());
                VideoJump jumpBean = new Gson().fromJson(adBean.getJump(), VideoJump.class);
                mBannerJumpList.add(jumpBean);
        }

    }

    /**
     * 设置第一个界面videolist数据源
     */
    public void setVedioList(List<VideoLinearBean.ObjBean.VideoListBean> videoList) {

        if (mVideoDataList == null)
            mVideoDataList = new ArrayList<>();

            mVideoDataList.addAll(videoList);
            notifyDataSetChanged();


    }

    /**
     * 设置第二个界面videolist数据源
     */
    public void setVideoDataListB(List<VideoLinearBeanB.ObjBean.VideoListBean> videoListB) {
        if (mVideoDataListB == null)
            mVideoDataListB = new ArrayList<>();

        mVideoDataListB.addAll(videoListB);
        notifyDataSetChanged();
    }
    /**
     * 点击事件
     */
    public interface OnItemClickListener {
        //  void onItemClick(int position, View view);
        void onItemClick(String vurl, View view);

    }

    private VideoBAdapter.OnItemClickListener mListener;

    public void setOnItemClickListener(VideoBAdapter.OnItemClickListener listener) {
        this.mListener = listener;
    }


}
