package com.nick.chef.main.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nick.chef.R;
import com.nick.chef.bean.HomePageBean;
import com.nick.chef.main.recipe.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FG on 2016/10/29.
 */

public class MapAdapter extends RecyclerView.Adapter<MapAdapter.Holder>{
    private Context mContext;
    private List<HomePageBean.DatasBean.ArticleListBean> mDatas;
    private OnItemClickListener mListener;
    private RecyclerView mRecyclerView;
    private boolean isLoading;
    public MapAdapter(Context context, RecyclerView recyclerView){
        mContext=context;
        mRecyclerView = recyclerView;
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //如果没在加载数据，而且以经滑动到底部，就调用加载更多的数据回调接口
                if (!isLoading&&!recyclerView.canScrollVertically(1)){
                    if (mMoreDataListener!=null){
                        mMoreDataListener.loadMoreData();
                    }
                    isLoading=true;
                }

            }
        });

    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_homepage_map,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
       Holder myHolder=holder;
        myHolder.place.setText(mDatas.get(position).getArticle_title());
        myHolder.abstra.setText(mDatas.get(position).getArticle_abstract());
        GlideUtils.glide(mContext,mDatas.get(position).getArticle_image(),myHolder.picture);
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    //拿到被点击的item所对应的article_id，等待着将该结果回传给调用者
                    mListener.onItemClickListener(mDatas.get(position).getArticle_id());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        private TextView place,abstra;
        private ImageView picture;
        public Holder(View itemView) {
            super(itemView);
            place= (TextView) itemView.findViewById(R.id.tv_place);
            abstra= (TextView) itemView.findViewById(R.id.tv_abstract);
            picture= (ImageView) itemView.findViewById(R.id.iv_img_map);
        }
    }
    public void setDataSource(List<HomePageBean.DatasBean.ArticleListBean> data,boolean isRefreash){
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        if (isRefreash){
            mDatas.clear();
        }
        mDatas.addAll(data);
        notifyDataSetChanged();
    }
    public interface OnItemClickListener{
        void onItemClickListener(String articleId);
    }
    //这个方法供外部调用，传入是实现接口的对象
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    private LoadMoreDataListener mMoreDataListener;//上拉加载更多监听事件的全局变量
    public interface LoadMoreDataListener {
        void loadMoreData();
    }
    //加载更多监听方法,供外部调用的
    public void setOnMoreDataLoadListener(LoadMoreDataListener onMoreDataLoadListener) {
        mMoreDataListener = onMoreDataLoadListener;
    }
    public void setLoaded() {
        isLoading = false;
    }

}
