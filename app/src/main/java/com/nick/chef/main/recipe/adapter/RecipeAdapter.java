package com.nick.chef.main.recipe.adapter;

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
import com.nick.chef.bean.RecipeBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ***********************************************************
 * Created by Administrator on 2016/10/27.
 * name:
 * desc:
 * step:
 * *************************************************************
 */
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyHoldler> {
    private Context mContext;
    private List<RecipeBean.ResultBean.DataBean> mDatas = new ArrayList<>();

    public RecipeAdapter(Context context) {
        this.mContext = context;

    }

    public void setDatas(List<RecipeBean.ResultBean.DataBean> dataBeen) {
        if (mDatas ==null){
            return;
        }
        mDatas.addAll(dataBeen);
        notifyDataSetChanged();
    }

    @Override
    public MyHoldler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_item, parent, false);
        return new MyHoldler(view);
    }

    @Override
    public void onBindViewHolder(final MyHoldler holder, final int position) {
        List<String> albums = mDatas.get(position).getAlbums();
        Glide.with(mContext)
                .load(albums.get(0))
                .placeholder(R.mipmap.wait)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(300, 200)  //重新调整大小
                .into(holder.mImgItem);
        holder.mTxtItemTitle.setText(mDatas.get(position).getTitle());
        holder.mTxtItemDesc.setText(mDatas.get(position).getImtro());

        //item的监听器
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.OnItemClick(position, holder.itemView);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    class MyHoldler extends RecyclerView.ViewHolder {
        @BindView(R.id.img_item)
        ImageView mImgItem;
        @BindView(R.id.txt_item_title)
        TextView mTxtItemTitle;
        @BindView(R.id.txt_item_desc)
        TextView mTxtItemDesc;

        public MyHoldler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //利用接口回调的方式添加点击事件
    public interface OnItemClickListener {
        public void OnItemClick(int position, View view);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

}
