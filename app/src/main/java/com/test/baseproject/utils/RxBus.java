package com.test.baseproject.utils;

import android.util.Log;

import java.util.HashMap;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * rxjava实现的事件传递
 */
public class RxBus {
    private HashMap<String, CompositeDisposable> mSubscriptionMap;
    private static volatile RxBus mRxbus;
    private final Subject<Object> mSubject;

    //单例
    public static RxBus getInstance() {
        if (mRxbus == null) {
            synchronized (RxBus.class) {
                if (mRxbus == null) {
                    mRxbus = new RxBus();
                }
            }
        }
        return mRxbus;
    }

    public RxBus() {
        mSubject = PublishSubject.create().toSerialized();
    }

    public void send(Object o) {
        mSubject.onNext(o);
    }

    /**
     * 返回指定类型带背压的Flowable实例
     */
    public <T> Flowable<T> getObservable(Class<T> type) {
        return mSubject.toFlowable(BackpressureStrategy.BUFFER).ofType(type);
    }

    /**
     * 订阅
     */
    public <T> Disposable doSubScribe(Class<T> type, Consumer<T> next, Consumer<Throwable> error) {
        return getObservable(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next, error);

    }

    /**
     * 订阅
     */
    public <T> Disposable doSubScribe(Class<T> type, Consumer<T> next) {
        return getObservable(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("M-TAG", "" + throwable.toString());
                    }
                });

    }

    /**
     * 是否已有观察者订阅
     */
    public boolean hasObservers() {
        return mSubject.hasObservers();
    }

    /**
     * 保存订阅disposable
     */
    public void register(Object o, Disposable disposable) {
        if (mSubscriptionMap == null) {
            mSubscriptionMap = new HashMap<>();
        }
        String key = o.getClass().getName();
        if (mSubscriptionMap.get(key) != null) {
            mSubscriptionMap.get(key).add(disposable);
        } else {
            //一次性容器，可持有多个并提供添加、移除
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(disposable);
            mSubscriptionMap.put(key, compositeDisposable);
        }
    }

    /**
     * 取消订阅
     */
    public void unregister(Object o) {
        if (mSubscriptionMap == null) {
            return;
        }
        String key = o.getClass().getName();
        if (!mSubscriptionMap.containsKey(key)) {
            return;
        }
        if (mSubscriptionMap.get(key) != null) {
            mSubscriptionMap.get(key).dispose();
        }
        mSubscriptionMap.remove(key);
    }

}