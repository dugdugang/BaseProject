package com.test.baseproject.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Author: duguang
 * Date 2017/9/26 0026.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected ArrayList<T> mDatas;
    protected LayoutInflater mInflater;

    public CommonAdapter(Context context, int layoutId, ArrayList<T> datas) {
        this.mContext = context;
        this.mLayoutId = layoutId;
        this.mDatas = datas;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder viewHolder = CommonViewHolder.get(mContext, parent, mLayoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        holder.undatePosition(position);
        convert(holder, mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        if (mDatas == null) {
            return 0;
        }
        return mDatas.size();
    }

    public abstract void convert(CommonViewHolder holder, T t);
}
