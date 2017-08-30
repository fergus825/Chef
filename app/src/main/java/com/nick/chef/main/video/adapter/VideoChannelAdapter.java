package com.nick.chef.main.video.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nick.chef.R;
import com.nick.chef.bean.VideoChannelBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * *********************************************************************
 * Author: Nick
 * Created on 2016/10/31  11:26
 * desc:
 * <p>
 * *********************************************************************
 */

public class VideoChannelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_TITLE = 0;
    private static final int TYPE_NORMAL = 1;

    private String url;
    private String title;
    private String scription;

    private List<VideoChannelBean.ObjBean.VideoListBean> mChannelDataList;

    private Context mContext;

    public VideoChannelAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TITLE;
        }
            return TYPE_NORMAL;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TITLE:
                View titleView = LayoutInflater.from(mContext).inflate(R.layout.videoitem_channel_header, parent, false);
                return new CTitleViewHolder(titleView);
            case TYPE_NORMAL:
                View contentView = LayoutInflater.from(mContext).inflate(R.layout.video_channel_content, parent, false);
                return new CContentViewHolder(contentView);
        }


        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case TYPE_TITLE:
                CTitleViewHolder titleHolder = (CTitleViewHolder) holder;
                titleHolder.mVideoChannelTexttitle.setText(title);
                titleHolder.mVideoChannelDescription.setText(scription);
                Glide.with(mContext).load(url).into(((CTitleViewHolder) holder).mVideoChannelImgicon);
                break;

            case TYPE_NORMAL:
                position = position - 1;
                CContentViewHolder cContentViewHolder = (CContentViewHolder) holder;
                cContentViewHolder.mVideoTvDescribtion.setText(mChannelDataList.get(position).getDescribtion());
                cContentViewHolder.mVideoTvPlaytime.setText(mChannelDataList.get(position).getPlay_times());
                Glide.with(mContext).load(mChannelDataList.get(position).getImg_video())

                        .into(((CContentViewHolder) holder).mVideoImgMain);

                final int finalPosition = position;
                cContentViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onItemClick(mChannelDataList.get(finalPosition).getVurl(),v);
                    }
                });

                break;
        }

    }

    @Override
    public int getItemCount() {
        return mChannelDataList != null ? mChannelDataList.size() + 1 : 0;
    }




     class CTitleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.video_channel_imgicon)
        ImageView mVideoChannelImgicon;
        @BindView(R.id.video_channel_texttitle)
        TextView mVideoChannelTexttitle;
        @BindView(R.id.video_channel_description)
        TextView mVideoChannelDescription;

        public CTitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class CContentViewHolder extends RecyclerView.ViewHolder  {
        @BindView(R.id.video_img_main)
        ImageView mVideoImgMain;
        @BindView(R.id.video_tv_describtion)
        TextView mVideoTvDescribtion;
        @BindView(R.id.video_tv_playtime)
        TextView mVideoTvPlaytime;

        public CContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setChannelList(List<VideoChannelBean.ObjBean.VideoListBean> videoList) {

        if (mChannelDataList == null)
            mChannelDataList = new ArrayList<>();
        if (videoList!=null){
        mChannelDataList.addAll(videoList);
        notifyDataSetChanged();}


    }

    public void setTitle(String url, String title, String scription) {
        this.url = url;
        this.title = title;
        this.scription = scription;
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
