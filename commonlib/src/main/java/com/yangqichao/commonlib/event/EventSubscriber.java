package com.yangqichao.commonlib.event;

import rx.Subscriber;

/**
 * Created by yangqc on 2016/8/12.
 */
public abstract class EventSubscriber<T> extends Subscriber<T> {

    public abstract void onNextDo(T t);

    @Override
    public void onCompleted() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

    @Override
    public void onError(Throwable e) {
        System.out.println("RxBus Event Error :"+e.getMessage());
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

    @Override
    public void onNext(T t) {
        onNextDo(t);
    }
}
