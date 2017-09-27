package com.test.baseproject.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: duguang
 * Date 2017/9/22 0022.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P mPresenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //解决软键盘遮挡
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //设置布局id
        setContentView(setLayoutId());
        //buttferknife绑定
        unbinder = ButterKnife.bind(this);
        mPresenter = createPresenter();
        // TODO: 2017/9/25 0025
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //buttferknife解绑
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter.finish();
        }
    }

    /**
     * 设置布局xml
     */
    protected abstract int setLayoutId();

    /**
     * 创建presenter
     */
    protected abstract P createPresenter();
}
