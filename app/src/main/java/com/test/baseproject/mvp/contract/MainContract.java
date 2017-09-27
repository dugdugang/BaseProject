package com.test.baseproject.mvp.contract;

import com.test.baseproject.base.BaseModel;
import com.test.baseproject.base.BasePresenter;
import com.test.baseproject.base.BaseView;

/**
 * Author: duguang
 * Date 2017/9/25 0025.
 */

public interface MainContract {

    interface View extends BaseView {
        void init();
        void initViews();

    }

    interface Model extends BaseModel {

    }

    abstract class Present extends BasePresenter<View, Model> {

    }
}
