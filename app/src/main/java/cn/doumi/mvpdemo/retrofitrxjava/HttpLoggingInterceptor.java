package cn.doumi.mvpdemo.retrofitrxjava;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/5 0005.
 */

public class HttpLoggingInterceptor extends BaseInterceptor {
    public HttpLoggingInterceptor(Map<String, String> headers) {
        super(headers);
    }
}
