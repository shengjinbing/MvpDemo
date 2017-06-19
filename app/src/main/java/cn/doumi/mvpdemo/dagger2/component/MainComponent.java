package cn.doumi.mvpdemo.dagger2.component;

import cn.doumi.mvpdemo.MainActivity;
import cn.doumi.mvpdemo.dagger2.module.MainModule;
import dagger.Component;

/**
 * Created by 李想 on 2017/6/16 0016.
 */
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);

}
