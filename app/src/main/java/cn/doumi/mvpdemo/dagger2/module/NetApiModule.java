package cn.doumi.mvpdemo.dagger2.module;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import cn.doumi.mvpdemo.network.api.NetApiService;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/4/24 0024.
 */
@Module
public class NetApiModule {
    @Provides
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                connectTimeout(60, TimeUnit.MILLISECONDS).
                readTimeout(60, TimeUnit.MILLISECONDS).
                build();
        return okHttpClient;
    }

    @Provides
    public Retrofit provideRetrofit(Application application, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://zhuangbi.info/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    protected NetApiService provideGitHubService(Retrofit retrofit) {

        return retrofit.create(NetApiService.class);
    }
}
