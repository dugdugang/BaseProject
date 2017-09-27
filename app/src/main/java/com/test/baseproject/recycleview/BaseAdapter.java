package com.test.baseproject.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Author: duguang
 * Date 2017/9/27 0027.
 */

public class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    protected List<T> mDatas;
    protected Context mContext;

    private RecycleItemDelagate delagate;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (mDatas==null){
            return 0;
        }
        return 0;
    }
}
