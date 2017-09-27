package com.test.baseproject.test;

import dagger.Component;

/**
 * Author: duguang
 * Date 2017/9/26 0026.
 */
@Component(modules = AModule.class)
public interface AComponent {
    void inject(Test test);
}
