package com.app.behavior.adapter.rvAdapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RViewAdapter<T> extends RecyclerView.Adapter<RViewHolder> {
    private RViewItemManager<T> itemStyle;//item类型管理
    private ItemListener<T> itemListener;//item点击监听
    private List<T> mDatas;//数据源

    public void addDatas(List<T> datas) {
        if (datas == null)
            return;
        this.mDatas.addAll(datas);
        this.notifyDataSetChanged();
    }

    public void updateDatas(List<T> datas) {
        if (datas == null)
            return;
        this.mDatas = datas;
        this.notifyDataSetChanged();
    }

    //单样式
    public RViewAdapter(List<T> mDatas) {
        if (mDatas == null)
            this.mDatas = new ArrayList<>();
        this.mDatas = mDatas;
        itemStyle = new RViewItemManager<>();
    }

    //多样式
    public RViewAdapter(List<T> mDatas, RViewItem<T> item) {
        if (mDatas == null)
            this.mDatas = new ArrayList<>();
        this.mDatas = mDatas;
        itemStyle = new RViewItemManager<>();
        //将item的类型加入到管理器中
        addItemStyles(item);
    }

    @NonNull
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RViewItem item = itemStyle.getRViewItem(viewType);
        int layoutId = item.getItemLayout();
        RViewHolder holder = RViewHolder.careateViewHolder(parent.getContext(), parent, layoutId);
        if (item.openClick()) {//开启点击
            setListener(holder);
        }
        return holder;
    }

    /**
     * 根据position 获取当前Item的布局类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (hasMultiStyle()) {
            return itemStyle.getItemViewType(mDatas.get(position), position);
        }
        return super.getItemViewType(position);
    }

    /**
     * 是否是多样式Item
     *
     * @return
     */
    private boolean hasMultiStyle() {
        return itemStyle.getItemViewStylesCount() > 0;
    }

    @Override
    public void onBindViewHolder(@NonNull RViewHolder holder, int position) {
        convert(holder, mDatas.get(position));
    }


    @Override
    public int getItemCount() {
        return this.mDatas == null ? 0 : this.mDatas.size();
    }

    /**
     * 增加一种新的item样式
     *
     * @param item
     */
    public void addItemStyles(RViewItem<T> item) {
        itemStyle.addStyles(item);
    }

    /**
     * 点击、长按监听
     *
     * @param itemListener
     */
    public void setItemListener(ItemListener<T> itemListener) {
        this.itemListener = itemListener;
    }

    private void setListener(final RViewHolder holder) {
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemListener != null) {
                    int position = holder.getAdapterPosition();
                    itemListener.onItemClick(v, mDatas.get(position), position);
                }
            }
        });

        holder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemListener != null) {
                    int position = holder.getAdapterPosition();
                    return itemListener.onItemLongClick(v, mDatas.get(position), position);
                }
                return false;
            }
        });
    }

    private void convert(RViewHolder holder, T entity) {
        itemStyle.convert(holder, entity, holder.getAdapterPosition());
    }
}
