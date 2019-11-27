package com.app.behavior.adapter;


import com.app.behavior.adapter.rvAdapter.RViewAdapter;
import com.app.behavior.bean.UserInfoBean;

import java.util.List;

public class MultiAdapter extends RViewAdapter<UserInfoBean> {
    public MultiAdapter(List<UserInfoBean> mDatas) {
        super(mDatas);
        addItemStyles(new TestAdapterItem());
        addItemStyles(new TestAdapterItem01());
    }
}
