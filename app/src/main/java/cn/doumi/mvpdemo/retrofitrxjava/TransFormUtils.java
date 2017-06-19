package cn.doumi.mvpdemo.retrofitrxjava;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class TransFormUtils {
    public static <T>Observable.Transformer<T,T> applySchedulers(){

        return new Observable.Transformer<T,T>(){

            @Override
            public Observable<T> call(Observable<T> tobservable) {
                return ((Observable) tobservable).subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


}
