package cn.doumi.mvpdemo.mvp.presenter;

import cn.doumi.mvpdemo.mvp.contract.LoginContract;
import cn.doumi.mvpdemo.mvp.model.LoginModelImpl;
import cn.doumi.mvpdemo.intface.OnLoginListener;

/**
 * Created by Administrator on 2017/02/27
 */

public class LoginPresenterImpl implements LoginContract.LoginPresenter {

    LoginContract.LoginView mLoginView;
    LoginContract.LoginModel mModel;

    public LoginPresenterImpl(LoginContract.LoginView loginView) {
        this.mLoginView = loginView;
        mModel = new LoginModelImpl();
    }

    @Override
    public void Login() {
        mLoginView.showLoading();
        mModel.Login(mLoginView.getUserName(), mLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess() {
                //登陆成功的一些处理
            }

            @Override
            public void loginFailed() {
                //登陆失败的一些处理
            }
        });
    }

    @Override
    public void Clear() {
        mLoginView.clearUserName();
        mLoginView.clearPassword();
    }
}