package cn.doumi.mvpdemo.retrofitrxjava;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public abstract class HttpResultSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e != null) {
            e.printStackTrace();
            if (e.getMessage() == null) {
                _onError(new Throwable(e.toString()));
            } else {
                _onError(new Throwable(e.getMessage()));
            }
        } else {
            _onError(new Exception("null message"));
        }
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);

    }

    public abstract void onSuccess(T t);

    public abstract void _onError(Throwable e);
}
