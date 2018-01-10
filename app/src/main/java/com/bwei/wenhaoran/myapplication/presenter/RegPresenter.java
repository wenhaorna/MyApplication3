package com.bwei.wenhaoran.myapplication.presenter;

import com.bwei.wenhaoran.myapplication.bean.RegBean;
import com.bwei.wenhaoran.myapplication.model.ModelCallBack;
import com.bwei.wenhaoran.myapplication.model.RegModel;
import com.bwei.wenhaoran.myapplication.view.MyView;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class RegPresenter {
    RegModel regModel = new RegModel();
    MyView.RegView regView;
    public RegPresenter(MyView.RegView regView) {
        this.regView = regView;
    }

    public void getData(String tel, String pwd) {
        regModel.getRegData(tel,pwd, new ModelCallBack.RegCallBack() {

            @Override
            public void success(RegBean regBean) {
                regView.sucess(regBean);
                System.out.println("注册p数据："+regBean.toString());
            }

            @Override
            public void failed(Throwable code) {
                System.out.println("注册p错误："+code);
            }
        });
    }
}
