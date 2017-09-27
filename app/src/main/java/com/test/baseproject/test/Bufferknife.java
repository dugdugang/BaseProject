package com.test.baseproject.test;

import android.view.View;
import android.widget.TextView;

import com.test.baseproject.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: duguang
 * Date 2017/9/26 0026.
 */

public class Bufferknife {
    @BindView(R.id.test_tv)
    private TextView tv;

    @OnClick({R.id.test_tv})
    private void onClick(View view) {
    }


}
