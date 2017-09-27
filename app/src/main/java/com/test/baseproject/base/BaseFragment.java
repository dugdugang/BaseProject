package com.test.baseproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: duguang
 * Date 2017/9/25 0025.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    private Unbinder unbinder;
    private View view;
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(setLayoutId(), container, false);
        //buttferknife绑定
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void onViewCreated(View view, Bundle savedInstanceState, boolean b) {
        if (b) {
            super.onViewCreated(view, savedInstanceState);
            // TODO: 2017/9/25 0025
        } else {
            super.onViewCreated(view, savedInstanceState);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //buttferknife解绑
        unbinder.unbind();
    }

    /**
     * 设置布局xml
     */
    abstract int setLayoutId();
}
