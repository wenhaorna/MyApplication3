package com.bwei.wenhaoran.myapplication.presenter;

import com.bwei.wenhaoran.myapplication.bean.LoginBean;
import com.bwei.wenhaoran.myapplication.model.LoginModel;
import com.bwei.wenhaoran.myapplication.model.ModelCallBack;
import com.bwei.wenhaoran.myapplication.view.MyView;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class LoginPresenter {
    LoginModel loginModel = new LoginModel();
    MyView.LoginView loginView;
    public LoginPresenter(MyView.LoginView loginView) {
        this.loginView = loginView;
    }

    public void getData(String tel, String pwd) {
        loginModel.getLoginData(tel,pwd, new ModelCallBack.LoginCallBack() {
            @Override
            public void success(LoginBean dengluBean) {
                loginView.success(dengluBean);
                System.out.println("登录p数据："+dengluBean.toString());
            }

            @Override
            public void failed(Throwable code) {
                System.out.println("登录p错误："+code);
            }
        });
    }
}
