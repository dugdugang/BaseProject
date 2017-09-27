package com.test.baseproject.base;

/**
 * Author: duguang
 * Date 2017/9/25 0025.
 */

public class BasePresenter<V extends BaseView, M extends BaseModel> {
    protected V mView;
    protected M mModel;

    public void finish() {
        if (mView != null) {
            mView = null;
        }
    }

}
