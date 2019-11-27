package com.app.behavior.adapter;

import android.widget.TextView;

import com.app.behavior.R;
import com.app.behavior.adapter.rvAdapter.RViewHolder;
import com.app.behavior.adapter.rvAdapter.RViewItem;


/**
 * Created by HelloCsl(cslgogogo@gmail.com) on 2016/2/29 0029.
 */
public class RecyclerViewAdapter implements RViewItem<String> {
    @Override
    public int getItemLayout() {
        return R.layout.item_recycler;
    }

    @Override
    public boolean openClick() {
        return false;
    }

    @Override
    public boolean isItemView(String entity, int position) {
        return true;
    }

    @Override
    public void convert(RViewHolder holder, String entity, int position) {
        TextView textView = holder.getView(R.id.item_tv_title);
        textView.setText(entity);
    }
}
