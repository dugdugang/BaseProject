package com.test.baseproject.recycleview;

import android.support.v4.util.SparseArrayCompat;

/**
 * Author: duguang
 * Date 2017/9/27 0027.
 */

public class DelagateManager<T> {
    private SparseArrayCompat<RecycleItemDelagate<T>> delagates = new SparseArrayCompat<>();

    public DelagateManager<T> addDelagate(RecycleItemDelagate<T> delagate) {
        int viewType = delagates.size();
        if (delagate != null) {
            delagates.put(viewType, delagate);
        }
        return this;
    }

    public DelagateManager<T> addDelagate(int viewType, RecycleItemDelagate<T> delagate) {
        if (delagates.get(viewType) != null) {
            throw new IllegalArgumentException("An ItemViewDelegate is already registered for the viewType = "
                    + viewType
                    + ". Already registered ItemViewDelegate is "
                    + delagates.get(viewType));
        }
        delagates.put(viewType, delagate);
        return this;
    }


}
