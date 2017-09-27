package com.test.baseproject.base;

import android.os.Bundle;
import android.view.View;

/**
 * Author: duguang
 * Date 2017/9/25 0025.
 */

public abstract class BaseLazyFragment extends BaseFragment {
    protected boolean isVisible = false;
    protected boolean isFirst = true;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState,false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
        } else {
            isVisible = false;
        }
        if (isFirst && isVisible) {
            isFirst = false;
            // TODO: 2017/9/25 0025

        }
    }

}
