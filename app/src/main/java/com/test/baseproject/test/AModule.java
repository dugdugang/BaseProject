package com.test.baseproject.test;

import dagger.Provides;

/**
 * Author: duguang
 * Date 2017/9/26 0026.
 */
@dagger.Module
public class AModule {
    private A a;

    public AModule() {
        this.a = new A();
    }

    @Provides
    A provideA() {
        return a;
    }

}
