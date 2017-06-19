package cn.doumi.mvpdemo.mvp.contract;

import cn.doumi.mvpdemo.bean.User;
import cn.doumi.mvpdemo.intface.OnLoginListener;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class LoginContract {

    public interface LoginView {
        String getUserName();

        String getPassword();

        void clearUserName();

        void clearPassword();

        void showLoading();

        void hideLoading();

        void toMainActivity(User user);

        void showFailedError();
    }

    public interface LoginPresenter {
        void Login();
        void Clear();
    }

    public interface LoginModel {
        void Login(String account, String password, OnLoginListener onLoginListener);
    }


}