package com.test.baseproject.recycleview;

import android.view.View;

/**
 * Author: duguang
 * Date 2017/9/27 0027.
 */

public interface RecycleItemDelagate<T> {
    int getItemType(T t);

    BaseViewHolder createViewHolder();

    void bindData(T t, View itemView);

}
