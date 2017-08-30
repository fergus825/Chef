package com.nick.chef.main.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nick.chef.R;
import com.nick.chef.bean.HomePageBean;
import com.nick.chef.main.recipe.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FG on 2016/10/29.
 */

public class TopAdaper  extends RecyclerView.Adapter{
    private Context mContext;
    private List<HomePageBean.DatasBean.ArticleListBean> mDatas;
    private OnItemClickListener mListener;
    private RecyclerView mRecyclerView;
    private boolean isLoading=false;
    private static final int VIEW_ITEM = 0;
    private static final int VIEW_PROG = 1;
    public TopAdaper(Context context, RecyclerView recyclerView){
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

    //根据不同的数据返回不同的viewType
    @Override
    public int getItemViewType(int position) {
        //当数据源中取不出数据时，也就是item滑到底了，也就是显示加载进度的进度条该出来了
        return mDatas.get(position) != null ? VIEW_ITEM : VIEW_PROG;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if(viewType==VIEW_ITEM){
        holder= new Holder(LayoutInflater.from(mContext).inflate(R.layout.item_homepage_top,parent,false));
        }else {
            holder=new MyProgressViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_footer,parent,false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof Holder){
            Holder myHoler= (Holder) holder;
            myHoler.top.setText(mDatas.get(position).getTop());
            myHoler.tag.setText(mDatas.get(position).getArticle_abstract());
            myHoler.title.setText(mDatas.get(position).getArticle_title());
            GlideUtils.glide(mContext,mDatas.get(position).getArticle_image(),myHoler.picture);
            myHoler.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        //拿到被点击的item所对应的article_id，等待着将该结果回传给调用者
                        mListener.onItemClickListener(mDatas.get(position).getArticle_id());
                    }
                }
            });
        }else {
            if (((MyProgressViewHolder) holder).pb != null)
                ((MyProgressViewHolder) holder).pb.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private TextView top,tag,title,origin;
        private ImageView picture;
        public Holder(View itemView) {
            super(itemView);
            top= (TextView) itemView.findViewById(R.id.tv_top);
            picture= (ImageView) itemView.findViewById(R.id.iv_img_top);
            tag= (TextView) itemView.findViewById(R.id.tv_tag);
            title= (TextView) itemView.findViewById(R.id.tv_title);
            origin= (TextView) itemView.findViewById(R.id.tv_origin);
        }
    }
    //两种item类型，一个是正常的数据展示条目，一个是当数据没有的时候，progressbar就会出现
    public class MyProgressViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar pb;

        public MyProgressViewHolder(View itemView) {
            super(itemView);
            pb = (ProgressBar) itemView.findViewById(R.id.progressBar);
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
     //   Log.i("tttttttt",mDatas.size()+"");
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
    //加了一个空数据让progressbar出现，但是在一定时间后需要把它移除，否则，它会一直在那转
    public void removeProgressBar(){
        mDatas.remove(mDatas.size()-1);
        notifyDataSetChanged();
    }
}
