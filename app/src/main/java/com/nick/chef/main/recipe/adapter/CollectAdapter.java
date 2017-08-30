package com.nick.chef.main.recipe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nick.chef.R;
import com.nick.chef.main.recipe.GlideUtils;
import com.nick.chef.main.recipe.SQL.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ***********************************************************
 * Created by Jerry on 2016/10/31.
 * name:
 * desc:
 * step:
 * *************************************************************
 */
public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.CollectHolder> {
    private Context mContext;
    private List<Recipe> mDatas;

    public CollectAdapter(Context context, List<Recipe> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
    }

    @Override
    public CollectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.collect_item, parent, false);
        return new CollectHolder(view);
    }

    @Override
    public void onBindViewHolder(final CollectHolder holder, final int position) {
        GlideUtils.glide(mContext,mDatas.get(position).getAlbums(),holder.mImgCollect);
        holder.mTxtCollect.setText(mDatas.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onCollectItemClick(position,holder.itemView);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    class CollectHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_collect)
        ImageView mImgCollect;
        @BindView(R.id.txt_collect)
        TextView mTxtCollect;

        public CollectHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //利用接口回调的方式添加点击事件
    public interface OnCollectItemClickListener{
         void onCollectItemClick(int position, View view);
    }

    private OnCollectItemClickListener mListener;

    public void setOnCollectItemClickListener(OnCollectItemClickListener listener) {
        this.mListener = listener;
    }

}
