package com.freeproject.mynews.mynewsreader.utility;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Lukas Dylan Adisurya on 12/28/2017.
 * If you had any question about this project, you can contact me via E-mail lukas.dylan.adisurya@gmail.com
 */

public final class RxBus {
    private final PublishSubject<Object> bus = PublishSubject.create();

    public void send(final Object event) {
        bus.onNext(event);
    }

    public Observable<Object> toObservable() {
        return bus;
    }

    public boolean hasObservers() {
        return bus.hasObservers();
    }
}
