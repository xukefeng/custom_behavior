package com.app.behavior.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by HelloCsl(cslgogogo@gmail.com) on 2016/3/1 0001.
 */
public class TestFragmentAdapter extends FragmentStatePagerAdapter {
    List<? extends Fragment> mFragments;
    String[] titles;

//    public TestFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
//        super(fm, behavior);
//    }


    public TestFragmentAdapter(List<? extends Fragment> fragments, String[] titles, FragmentManager fm) {
        //super(fm,FragmentStatePagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT);
        super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mFragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
