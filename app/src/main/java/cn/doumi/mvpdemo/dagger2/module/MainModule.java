package cn.doumi.mvpdemo.dagger2.module;

import cn.doumi.mvpdemo.mvp.contract.MainContract;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/6/16 0016.
 */
@Module
public class MainModule {
    private MainContract.View mView;

    public MainModule(MainContract.View view) {
        mView = view;
    }

    @Provides
    MainContract.View provideMainView() {
        return mView;
    }
}
