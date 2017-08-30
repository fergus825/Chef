package com.nick.chef.main.mine.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nick.chef.R;
import com.nick.chef.main.mine.manager.DataCleanManager;
import com.nick.chef.main.recipe.fragment.CollectActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * *********************************************************************
 * Author: Nick
 * Created on 2016/10/27  11:01
 * desc:
 * <p>
 * *********************************************************************
 */

public class MineFragment extends Fragment {
    @BindView(R.id.more_clear)
    TextView mMoreClear;
    @BindView(R.id.clearing)
    RelativeLayout mClearing;
    @BindView(R.id.txt_collect_mine)
    TextView mTxtCollectMine;
    @BindView(R.id.tv_shareto)
    TextView tvShareto;


    private File mFile;

    public MineFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getActivity()).inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //  DataCleanManager dataCleanManager =  new DataCleanManager();

        showCacheSize();

    }


    @OnClick({R.id.clearing, R.id.txt_collect_mine,R.id.tv_shareto})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clearing:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("确认清除？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataCleanManager.cleanInternalCache(getContext());
                        DataCleanManager.cleanExternalCache(getContext());
                        showCacheSize();

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
                break;
            case R.id.txt_collect_mine:
                startActivity(new Intent(getActivity(), CollectActivity.class));
                break;
            case R.id.tv_shareto:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "给各位吃货们推荐一个美食App----》i小厨，快去下载吧！");
                shareIntent.setType("text/plain");
                //设置分享列表的标题，并且每次都显示分享列表
                startActivity(Intent.createChooser(shareIntent, "分享到"));
                break;
        }
    }


    /**
     * 获取缓存
     */
    private void showCacheSize() {
        try {
            mFile = new File(getActivity().getCacheDir().getAbsolutePath());
            String dataSize = DataCleanManager.getCacheSize(mFile);
            mMoreClear.setText(dataSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
