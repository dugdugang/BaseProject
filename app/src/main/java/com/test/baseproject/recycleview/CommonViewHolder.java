package com.test.baseproject.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Author: duguang
 * Date 2017/9/26 0026.
 */

public class CommonViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private Context mContext;
    private View mConvertView;
    private int mPosition;

    public CommonViewHolder(Context context, View itemView, ViewGroup parent) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    public static CommonViewHolder get(Context context, ViewGroup parent, int layoutId) {
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new CommonViewHolder(context, view, parent);
    }

    /**
     * 通过id获取控件
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        return (T) view;

    }

    public TextView setText(int viewId, String s) {
        TextView view = getView(viewId);
        view.setText(s);
        return view;
    }

    public CommonViewHolder setOnClickListener(int viewID, View.OnClickListener listener) {
        View view = getView(viewID);
        view.setOnClickListener(listener);
        return this;
    }

    public void undatePosition(int position) {
        mPosition = position;
    }

    public int getmPosition() {
        return mPosition;
    }

    public View getItemView() {
        return mConvertView;
    }
}
