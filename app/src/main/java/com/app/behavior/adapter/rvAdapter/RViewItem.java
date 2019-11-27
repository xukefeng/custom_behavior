package com.app.behavior.adapter.rvAdapter;

/**
 * 定义item 的元素特征
 *
 * @param <T> 数据类型
 */
public interface RViewItem<T> {
    /**
     * 获取item的布局id
     *
     * @return
     */
    int getItemLayout();

    /**
     * 判断是否开启点击
     *
     * @return
     */
    boolean openClick();

    /**
     * 是否是当前item布局
     *
     * @return
     */
    boolean isItemView(T entity, int position);

    /**
     * item控件和数据的绑定
     *
     * @param entity   数据对象
     * @param position 当前item的索引
     */
    void convert(RViewHolder holder, T entity, int position);
}
