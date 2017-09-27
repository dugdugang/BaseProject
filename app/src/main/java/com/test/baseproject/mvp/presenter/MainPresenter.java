package com.test.baseproject.mvp.presenter;

import com.test.baseproject.mvp.contract.MainContract;
import com.test.baseproject.mvp.model.MainModel;

import javax.inject.Inject;

/**
 * Author: duguang
 * Date 2017/9/25 0025.
 */

public class MainPresenter extends MainContract.Present {
    @Inject
    public MainPresenter(MainContract.View view) {
        mView = view;
        mModel = new MainModel();
    }
}
