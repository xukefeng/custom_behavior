package com.app.behavior.adapter.rvAdapter;


import androidx.collection.SparseArrayCompat;

/**
 * 多类型，多样式item的管理器
 */
public class RViewItemManager<T> {

    /**
     * key:viewType  value:RViewItem
     */
    private SparseArrayCompat<RViewItem<T>> styles = new SparseArrayCompat<>();

    public void addStyles(RViewItem<T> item) {
        if (item != null) {
            styles.put(styles.size(), item);
        }
    }

    /**
     * 判断有没有多样式
     */
    public int getItemViewStylesCount() {
        return styles.size();
    }

    /**
     * 根据显示的viewType返回RViewItem对象（集合的value）
     *
     * @param viewType
     * @return
     */
    public RViewItem getRViewItem(int viewType) {
        return styles.get(viewType);
    }

    /**
     * 根据数据源和位置返回某个item类型的viewType（集合的key）
     */
    public int getItemViewType(T entity, int position) {
        //样式倒序循环，防止增删集合抛出异常
        for (int i = styles.size() - 1; i >= 0; i--) {
            RViewItem<T> item = styles.valueAt(i);
            if (item.isItemView(entity, position)) {//是否是当前的样式显示，由开发者实现
                return styles.keyAt(i);//返回viewType
            }
        }
        throw new IllegalArgumentException("该位置没有匹配的条目样式");
    }

    /**
     * 试图与数据源的绑定显示
     *
     * @param holder
     * @param entity
     * @param position
     */
    public void convert(RViewHolder holder, T entity, int position) {
        for (int i = 0; i < styles.size(); i++) {
            RViewItem<T> item = styles.valueAt(i);
            if (item.isItemView(entity, position)) {
                item.convert(holder, entity, position);
                return;
            }
        }
        throw new IllegalArgumentException("该位置没有匹配的条目样式");
    }
}
