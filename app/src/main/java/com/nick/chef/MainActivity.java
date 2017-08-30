package com.nick.chef;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.nick.chef.main.homepage.fragment.HomePageFragment;
import com.nick.chef.main.mine.fragment.MineFragment;
import com.nick.chef.main.recipe.fragment.RecipeFragment;
import com.nick.chef.main.video.fragment.VideoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.rg_tab)
    RadioGroup mRgTab;

    private Fragment mHomeFragment;
    private Fragment mRecipeFragment;
    private Fragment mVideoFragment;
    private Fragment mMineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRgTab.setOnCheckedChangeListener(this);
        //第一次默认选中第一项
        mRgTab.check(R.id.rb_homepage);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_homepage:
                setChecked(0);

                break;
            case R.id.rb_recipe:
                setChecked(1);

                break;
            case R.id.rb_video:
                setChecked(2);

                break;
            case R.id.rb_mine:
                setChecked(3);

                break;
        }


    }

    private void setChecked(int i) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //=======================================================================
        // 1.隐藏已将加载过的Fragment
        //=======================================================================
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mRecipeFragment != null) {
            transaction.hide(mRecipeFragment);
        }
        if (mVideoFragment != null) {
            transaction.hide(mVideoFragment);
        }
        if (mMineFragment != null) {
            transaction.hide(mMineFragment);
        }

        //=======================================================================
        //2. 开始加载当前选中的Framgment
        //=======================================================================
        switch (i) {
            case 0:
                if (mHomeFragment == null) {
                    //第一次加载
                    mHomeFragment = new HomePageFragment();
                    transaction.add(R.id.fl_container, mHomeFragment);
                } else {
                    transaction.show(mHomeFragment);
                }
                break;
            case 1:
                if (mRecipeFragment == null) {
                    mRecipeFragment = new RecipeFragment();
                    transaction.add(R.id.fl_container, mRecipeFragment);
                } else {
                    transaction.show(mRecipeFragment);
                }
                break;
            case 2:
                if (mVideoFragment == null) {
                    mVideoFragment = new VideoFragment();
                    transaction.add(R.id.fl_container, mVideoFragment);
                } else {
                    transaction.show(mVideoFragment);
                }
                break;
            case 3:
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                    transaction.add(R.id.fl_container, mMineFragment);
                } else {
                    transaction.show(mMineFragment);
                }
                break;
        }

        transaction.commit();
    }
}
