package com.bwei.wenhaoran.myapplication.model;

import com.bwei.wenhaoran.myapplication.bean.LoginBean;
import com.bwei.wenhaoran.myapplication.bean.PersonInfoBean;
import com.bwei.wenhaoran.myapplication.bean.RegBean;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class ModelCallBack {
    public interface LoginCallBack{
        //登录时，数据获取成功的方法，返回一个值表示登陆成功
        public void success(LoginBean loginBean);
        //登录时，数据获取失败的方法，返回一个int值响应码表示登陆失败
        public void failed(Throwable code);
    }

    public interface RegCallBack {
        //注册时，数据获取成功的方法，返回一个值表示登陆成功
        public void success(RegBean regBean);
        //注册时，数据获取失败的方法，返回一个int值响应码表示登陆失败
        public void failed(Throwable code);
    }

    //个人中心
    public interface PersonCallBack{
        void success(PersonInfoBean personBean);
        void failed(Throwable code);
    }
}
