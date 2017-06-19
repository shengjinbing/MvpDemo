package cn.doumi.mvpdemo.dagger2.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/4/24 0024.
 */
@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    public Application providesApplication(){
        return mApplication;
    }
}
