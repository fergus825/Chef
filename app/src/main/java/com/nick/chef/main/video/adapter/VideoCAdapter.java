package com.nick.chef.main.video.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nick.chef.R;
import com.nick.chef.bean.VideoGridBean;
import com.nick.chef.main.recipe.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * *********************************************************************
 * Author: Nick
 * Created on 2016/10/29  11:04
 * desc:
 * <p>
 * *********************************************************************
 */

public class VideoCAdapter extends RecyclerView.Adapter<VideoCAdapter.MyViewHolder> {


    private Context mContext;
    private List<VideoGridBean.ObjBean.RecommendListBean> mVideoDataListC;

    public VideoCAdapter(Context context) {
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.videoitem_grid, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.videoTvTitle.setText(mVideoDataListC.get(position).getName());
        holder.videoTvNum.setText(mVideoDataListC.get(position).getPlay_amount());
//        Glide.with(mContext).load(mVideoDataListC.get(position).getImg_video()).into(holder.videoImgGridicon);
        GlideUtils.glide(mContext,mVideoDataListC.get(position).getImg_video(),holder.videoImgGridicon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(mVideoDataListC.get(position).getId(), v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mVideoDataListC != null ? mVideoDataListC.size() : 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.video_img_gridicon)
        CircleImageView videoImgGridicon;
        @BindView(R.id.video_tv_title)
        TextView videoTvTitle;
        @BindView(R.id.video_tv_num)
        TextView videoTvNum;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    /**
     * 设置第三个界面videolist数据源
     */
    public void setVideoDataListC(List<VideoGridBean.ObjBean.RecommendListBean> videoListB) {
        if (mVideoDataListC == null)
            mVideoDataListC = new ArrayList<>();

        mVideoDataListC.addAll(videoListB);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(String id, View view);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


}
