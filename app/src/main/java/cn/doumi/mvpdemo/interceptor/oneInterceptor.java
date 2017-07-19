package cn.doumi.mvpdemo.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 第一种拦截器addNetworkInterceptor，无论有无网络我们都先获取缓存的数据。
 * Created by Administrator on 2017/6/23 0023.
 */

/*
 要做缓存用的是okhttp的功能，主要利用的是拦截器。这里一定要看清楚okhtt添加拦截器有两种。
 看清楚啊，很多时候这样的小的设置可能然我们浪费一天的时间的。
 有1.addInterceptor ,和2.addNetworkInterceptor这两种。他们的区别简单的说下，不知道也没关系，
 addNetworkInterceptor添加的是网络拦截器，他会在在request和resposne是分别被调用一次，
 addinterceptor添加的是aplication拦截器，他只会在response被调用一次。
*/

public class oneInterceptor implements Interceptor{



    /**
     * 一、无论有无网路都添加缓存。
     * 目前的情况是我们这个要addNetworkInterceptor
     * 这样才有效。经过本人测试（chan）测试有效.
     * 60S后如果没有网络将获取不到数据，显示连接失败
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Log.d("BBBBB",chain.request().toString()+"1111111111222222456");
        Request request = chain.request();
        Response response = chain.proceed(request);
        String cachetime = request.header("Cache-Time");
        int maxAge = 3200*24;//这里设置时间是缓存保留时间
        Log.d("BBBBB",cachetime+"9999999999999");
        return response.newBuilder()
                .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                .removeHeader("Cache-Control")
                .header("Cache-Control", "public, max-age=" + maxAge)
                .build();
    }
}
