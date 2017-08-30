package com.nick.chef.main.recipe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.nick.chef.R;
import com.nick.chef.main.recipe.SQL.Recipe;
import com.nick.chef.main.recipe.SQL.RecipeDao;
import com.nick.chef.main.recipe.adapter.CollectAdapter;

import java.util.Collections;
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
public class CollectActivity extends AppCompatActivity {
    @BindView(R.id.Rv_collect)
    RecyclerView mRvCollect;
    private RecipeDao mRecipeDao;
    private List<Recipe> mDatas;
    private CollectAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);

        mRecipeDao = new RecipeDao(this);
        mDatas = queryAllData();
        mRvCollect.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CollectAdapter(this, mDatas);
        mRvCollect.setAdapter(mAdapter);

        toDetailActivity();
        delete();  //删除
    }

    /**
     * 添加收藏条目的点击事件跳转到详情页（使用pos拼装URL）
     */
    private void toDetailActivity() {
        mAdapter.setOnCollectItemClickListener(new CollectAdapter.OnCollectItemClickListener() {
            @Override
            public void onCollectItemClick(int position, View view) {
//              ToastUtil.showToast(CollectActivity.this,position+"");
                String pos = mDatas.get(position).getPosition();
                Intent intent = new Intent(CollectActivity.this, RecipeDetailActivity.class);
                intent.putExtra("position", pos);
                startActivity(intent);
            }
        });
    }

    /**
     * 模拟删除的操作
     */
    private void delete() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(mRvCollect);
    }

    /**
     * 1、dragDirs - 表示拖拽的方向，有六个类型的值：LEFT、RIGHT、START、END、UP、DOWN
     * 2、swipeDirs - 表示滑动的方向，有六个类型的值：LEFT、RIGHT、START、END、UP、DOWN
     * 注: 如果为0，则表示不触发该操作（滑动or拖拽）
     */
    ItemTouchHelper.Callback mCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {
        /**
         * @param recyclerView
         * @param viewHolder 拖动的ViewHolder
         * @param target 目标位置的ViewHolder
         * @return
         */
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

            int fromPosition = viewHolder.getAdapterPosition(); //得到拖动ViewHolder的position
            int toPosition = target.getAdapterPosition(); //得到目标ViewHolder的position
            if (fromPosition < toPosition) {
                //分别把中间所有的item的位置重新交换
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(mDatas, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(mDatas, i, i - 1);
                }
            }
            mAdapter.notifyItemMoved(fromPosition, toPosition);
            return true;  //返回true表示执行拖动
        }

        /**
         * @param viewHolder 滑动的ViewHolder
         * @param direction 滑动的方向
         */
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            Recipe recipe = mDatas.get(position);
            //从数据库中彻底删除数据  注意：必须在移除数据前对对应数据库中的数据进行删除
            // 否则操作报java.lang.IndexOutOfBoundsException
            mRecipeDao.delete(recipe);
            mDatas.remove(position);
            mAdapter.notifyItemRemoved(position);
        }

    };


    /**
     * 查询所有的数据
     */
    public List<Recipe> queryAllData() {
        //查询数据
        return mRecipeDao.queryForAll();
    }
}
