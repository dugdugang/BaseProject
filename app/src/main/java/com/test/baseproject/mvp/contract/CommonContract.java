package com.test.baseproject.mvp.contract;

import com.test.baseproject.base.BaseModel;
import com.test.baseproject.base.BasePresenter;
import com.test.baseproject.base.BaseView;

/**
 * Author: duguang
 * Date 2017/9/26 0026.
 */

public interface CommonContract {
    interface View extends BaseView {

    }

    interface Model extends BaseModel {

    }

    class Presenter extends BasePresenter<View, Model> {

    }
}
