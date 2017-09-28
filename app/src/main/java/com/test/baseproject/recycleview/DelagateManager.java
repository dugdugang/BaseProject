package com.test.baseproject.recycleview;

import android.support.v4.util.SparseArrayCompat;

/**
 * Author: duguang
 * Date 2017/9/27 0027.
 */

public class DelagateManager<T> {
    private SparseArrayCompat<RecycleItemDelagate<T>> delagates = new SparseArrayCompat<>();

    public int getCount() {
        return delagates.size();
    }

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

    public DelagateManager<T> removeDelagate(RecycleItemDelagate<T> delagate) {
        if (delagate == null) {
            throw new NullPointerException("delagate is null");
        }
        int index = delagates.indexOfValue(delagate);
        if (index > 0) {
            delagates.remove(index);
        }
        return this;
    }

    public DelagateManager<T> removeDelagate(int viewType) {
        int index = delagates.indexOfKey(viewType);
        if (index > 0) {
            delagates.remove(index);
        }
        return this;
    }

    public int getViewItemType(T t, int position) {
        int size = delagates.size();
        for (int i = size - 1; i >= 0; i--) {
            RecycleItemDelagate<T> delagate = delagates.get(i);
            if (delagate.isForViewType(t, position)) {
                return delagates.keyAt(i);
            }
        }
        throw new IllegalArgumentException("No delagate added that matches position=" + position + "in data rource");
    }

    public void corvent(CommonViewHolder holder, T t, int position) {
        int size = delagates.size();
        for (int i = size - 1; i >= 0; i++) {
            RecycleItemDelagate<T> delagate = delagates.get(i);
            if (delagate.isForViewType(t, position)) {
                delagate.convert(t, holder, position);
                return;
            }
        }
        throw new IllegalArgumentException("No delagate added that matches position=" + position + "in data rource");

    }

    public RecycleItemDelagate<T> getItemViewDelagate(int viewType) {
        return delagates.get(viewType);
    }

    public int getViewTypeLayoutId(int viewType) {
        return delagates.get(viewType).getItemLayoutId();
    }

    public int getItemViewType(RecycleItemDelagate<T> delagate) {
        return delagates.indexOfValue(delagate);

    }

}
