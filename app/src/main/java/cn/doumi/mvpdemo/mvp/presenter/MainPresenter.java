package cn.doumi.mvpdemo.mvp.presenter;

import javax.inject.Inject;

import cn.doumi.mvpdemo.MainActivity;
import cn.doumi.mvpdemo.mvp.contract.MainContract;
import cn.doumi.mvpdemo.mvp.model.Rx1ModelImpl;
import cn.doumi.mvpdemo.network.HttpMethods;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/04/24
 */
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private MainContract.Model mModel;

    @Inject
    MainPresenter(MainContract.View view) {
        mView = view;
        mModel = new Rx1ModelImpl();
    }

    public void  LoadData(MainActivity activity, String key, Subscriber subscriber){
        HttpMethods.getinstance().getZhuangBiData(activity,key,subscriber);
    }

}