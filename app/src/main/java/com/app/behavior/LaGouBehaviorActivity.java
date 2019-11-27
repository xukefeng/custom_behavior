package com.app.behavior;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewpager.widget.ViewPager;

import com.app.behavior.adapter.TestFragmentAdapter;
import com.app.behavior.behavior.lagou.LagouNewsContentBehavior;
import com.app.behavior.behavior.lagou.LagouNewsHeaderPagerBehavior;
import com.app.behavior.utils.QMUI.QMUIStatusBarHelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class LaGouBehaviorActivity extends BaseActivity implements TabLayout.OnTabSelectedListener, LagouNewsHeaderPagerBehavior.OnPagerStateListener, LagouNewsContentBehavior.OnContentListener {
    private ViewPager mNewsPager;
    private TabLayout mTableLayout;
    private List<TestFragment> mFragments;
    private String[] titles = new String[4];
    private LagouNewsHeaderPagerBehavior mPagerBehavior;
    private LagouNewsContentBehavior mContentBehavior;
    private FrameLayout fl_title;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_la_gou_behavior;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        QMUIStatusBarHelper.setTransparent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        fl_title = findViewById(R.id.fl_title);
        mPagerBehavior = (LagouNewsHeaderPagerBehavior) ((CoordinatorLayout.LayoutParams) findViewById(R.id.id_uc_news_header_pager).getLayoutParams()).getBehavior();
        mPagerBehavior.setPagerStateListener(this);
        mContentBehavior = (LagouNewsContentBehavior) ((CoordinatorLayout.LayoutParams) findViewById(R.id.ll_content).getLayoutParams()).getBehavior();
        mContentBehavior.setOnContentListener(this);
        mNewsPager = findViewById(R.id.id_uc_news_content);
        mTableLayout = findViewById(R.id.id_uc_news_tab);
        mFragments = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mFragments.add(TestFragment.newInstance(String.valueOf(i), false));
            titles[i] = "Tab " + i;
        }
        mTableLayout.setTabMode(TabLayout.MODE_FIXED);
        mNewsPager.setAdapter(new TestFragmentAdapter(mFragments, titles, getSupportFragmentManager()));
        mTableLayout.setupWithViewPager(mNewsPager);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mNewsPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        mNewsPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onPagerClosed() {
        for (int i = 0; i < mFragments.size(); i++) {
            mFragments.get(i).setRefreshEnable(true);
        }
        Snackbar.make(mNewsPager, "pager closed", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onPagerOpened() {
        for (int i = 0; i < mFragments.size(); i++) {
            mFragments.get(i).setRefreshEnable(false);
        }
        Snackbar.make(mNewsPager, "pager opened", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onProgress(int scrollY) {
        if (scrollY <= 0) {
            fl_title.setBackgroundColor(Color.argb(0, 255, 153, 52));
        } else if (scrollY > 0 && scrollY <= 200) {
            float scale = (float) scrollY / 200;
            float alpha = (255 * scale);
            fl_title.setBackgroundColor(Color.argb((int) alpha, 255, 153, 52));

        } else {
            fl_title.setBackgroundColor(Color.argb(255, 255, 153, 52));
        }
    }


    @Override
    public void onBackPressed() {
        if (mPagerBehavior != null && mPagerBehavior.isClosed()) {
            mPagerBehavior.openPager();
        } else {
            super.onBackPressed();
        }
    }
}
