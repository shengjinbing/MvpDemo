package cn.doumi.mvpdemo.rxlife;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import rx.Observable;

import static rx.Observable.timer;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class TakeUntilUtils {

    @NonNull
    public static Observable.Transformer<Long, Long> bindUntilDelay(final int delaySecond) {
        return new Observable.Transformer<Long, Long>() {
            @Override
            public Observable<Long> call(Observable<Long> longObservable) {
                return longObservable.takeUntil(timer(delaySecond, TimeUnit.SECONDS));
            }
        };
    }
}
