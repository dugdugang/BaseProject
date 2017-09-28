package com.test.baseproject.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Author: duguang
 * Date 2017/9/27 0027.
 */

public class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {
    private List<T> mDatas;
    private Context mContext;
    private DelagateManager<T> mDelagateManager;
    private OnItemClickListener mOnItemClickListener;

    public MultiItemTypeAdapter(List<T> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        mDelagateManager = new DelagateManager<>();
    }

    @Override
    public int getItemViewType(int position) {
        if (mDelagateManager.getCount() <= 0) {
            return super.getItemViewType(position);

        }
        return mDelagateManager.getViewItemType(mDatas.get(position), position);
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecycleItemDelagate<T> delagate = mDelagateManager.getItemViewDelagate(viewType);
        int layoutId = delagate.getItemLayoutId();
        CommonViewHolder holder = CommonViewHolder.get(mContext, parent, layoutId);
        onHolderCreated(holder, holder.getItemView());
        setListener(parent, holder, viewType);
        return holder;
    }

    protected void setListener(ViewGroup parent, final CommonViewHolder holder, int viewType) {
        if (!isEnabled(viewType)) {
            return;
        }
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(holder.getItemView(), holder, holder.getAdapterPosition());
                }
            }
        });

        holder.getItemView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mOnItemClickListener != null) {
                    return mOnItemClickListener.onLongClick(holder.getItemView(), holder, holder.getAdapterPosition());
                }
                return false;
            }
        });
    }

    private boolean isEnabled(int viewType) {
        return true;
    }

    public void onHolderCreated(CommonViewHolder holder, View itemView) {

    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        convert(holder, mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        if (mDatas == null) {
            return 0;
        }
        return mDatas.size();
    }

    public void convert(CommonViewHolder holder, T t) {
        mDelagateManager.corvent(holder, t, holder.getAdapterPosition());
    }

    public interface OnItemClickListener {
        void onClick(View v, CommonViewHolder holder, int position);

        boolean onLongClick(View v, CommonViewHolder holder, int position);
    }
}
