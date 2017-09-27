package com.test.baseproject.test;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: duguang
 * Date 2017/9/26 0026.
 */

public class Test {
    @Inject
    A a;

    public int dos() {
        DaggerAComponent.builder().aModule(new AModule()).build().inject(this);
        Observable observable=  Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(@NonNull ObservableEmitter e) throws Exception {
            }
        });
        observable.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {

                    }
                });
        return a.dos();
    }
}
