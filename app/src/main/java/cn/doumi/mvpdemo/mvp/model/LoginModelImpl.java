package cn.doumi.mvpdemo.mvp.model;

import cn.doumi.mvpdemo.mvp.contract.LoginContract;
import cn.doumi.mvpdemo.intface.OnLoginListener;


/**
* Created by Administrator on 2017/02/27
*/

public class LoginModelImpl implements LoginContract.LoginModel{

    public LoginModelImpl() {
    }


    @Override
    public void Login(String account, String password, OnLoginListener onLoginListener) {
    }
}