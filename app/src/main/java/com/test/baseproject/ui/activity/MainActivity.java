package com.test.baseproject.ui.activity;

import com.test.baseproject.R;
import com.test.baseproject.base.BaseActivity;
import com.test.baseproject.mvp.contract.MainContract;
import com.test.baseproject.mvp.presenter.MainPresenter;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    @Inject
    MainPresenter mainPresenter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void initViews() {

    }
}
