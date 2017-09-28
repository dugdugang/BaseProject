package com.test.baseproject.recycleview;

/**
 * Author: duguang
 * Date 2017/9/27 0027.
 */

public interface RecycleItemDelagate<T> {
    int getItemLayoutId();

    boolean isForViewType(T t, int position);

    void convert(T t, CommonViewHolder holder, int position);

}
