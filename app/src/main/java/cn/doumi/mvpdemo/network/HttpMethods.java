package cn.doumi.mvpdemo.network;

import java.util.List;

import cn.doumi.mvpdemo.MainActivity;
import cn.doumi.mvpdemo.bean.ZhuangbiImage;
import cn.doumi.mvpdemo.config.Contants;
import cn.doumi.mvpdemo.network.api.NetApiService;
import cn.doumi.mvpdemo.retrofitrxjava.OkHttpProvider;
import cn.doumi.mvpdemo.retrofitrxjava.TransFormUtils;
import cn.doumi.mvpdemo.rxlife.ActivityEvent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class HttpMethods {

    private HttpMethods() {

    }

    private static OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
    private static GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
    private static RxJavaCallAdapterFactory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    private static HttpMethods httpMethods;

    public static HttpMethods getinstance() {
        if (httpMethods == null) {
            synchronized (HttpMethods.class) {
                if (httpMethods == null) {
                    httpMethods = new HttpMethods();
                }
            }
        }
        return httpMethods;
    }

    private NetApiService getTest(MainActivity activity) {
//OkHttpProvider.getOkHttpClient()
        Retrofit retrofit = new Retrofit.Builder()
                .client(OkHttpProvider.getOkHttpClient())
                .baseUrl(Contants.BaseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();

        NetApiService netService = retrofit.create(NetApiService.class);

        return netService;
    }

    public Subscription  getZhuangBiData(MainActivity activity, String key, Subscriber subscriber){
        return getTest(activity).search(key)
                .compose(TransFormUtils.<List<ZhuangbiImage>>applySchedulers())
                .compose(activity.bindUntilEvent(ActivityEvent.PAUSE))
                .subscribe(subscriber);
    }
}
