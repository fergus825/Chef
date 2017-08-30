package com.nick.chef.main.video.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nick.chef.R;
import com.nick.chef.bean.VideoLinearBeanB;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * *********************************************************************
 * Author: Nick
 * Created on 2016/10/29  9:38
 * desc:
 * <p>
 * *********************************************************************
 */

public class VideoBAdapter extends RecyclerView.Adapter<VideoBAdapter.MyHolder> {
    private Context mContext;
    private List<VideoLinearBeanB.ObjBean.VideoListBean> mVideoDataListB;

    public VideoBAdapter(Context context) {
        mContext = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.videoitem_content, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
       // VideoMulitiAdapter.ContentViewHolder holder = (VideoMulitiAdapter.ContentViewHolder) holder;
        holder.mVideoTvName.setText(mVideoDataListB.get(position).getName());
        holder.mVideoTvDescribtion.setText(mVideoDataListB.get(position).getDescribtion());
        holder.mVideoTvUpdate.setText(mVideoDataListB.get(position).getTime());
        holder.mVideoTvPlaytime.setText(mVideoDataListB.get(position).getPlay_times());
        Glide.with(mContext).load(mVideoDataListB.get(position).getImg_video())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mVideoImgMain);
        Glide.with(mContext).load(mVideoDataListB.get(position).getImg()).into(holder.mVideoImgTitleicon);

        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(mVideoDataListB.get(position).getVurl(), v);
            }
        });






    }

    @Override
    public int getItemCount() {
        return mVideoDataListB != null ? mVideoDataListB.size() : 0;
    }

    static class MyHolder extends RecyclerView.ViewHolder {
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

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
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

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

}