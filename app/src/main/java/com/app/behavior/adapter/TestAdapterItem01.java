package com.app.behavior.adapter;

import android.widget.TextView;

import com.app.behavior.R;
import com.app.behavior.adapter.rvAdapter.RViewHolder;
import com.app.behavior.adapter.rvAdapter.RViewItem;
import com.app.behavior.bean.UserInfoBean;

public class TestAdapterItem01 implements RViewItem<UserInfoBean> {
    @Override
    public int getItemLayout() {
        return R.layout.item_recycler;
    }

    @Override
    public boolean openClick() {
        return false;
    }

    @Override
    public boolean isItemView(UserInfoBean entity, int position) {
        return entity.getViewType() == 2;
    }

    @Override
    public void convert(RViewHolder holder, UserInfoBean entity, int position) {
        TextView textView = holder.getView(R.id.item_tv_title);
        textView.setText(entity.getName());
    }
}
