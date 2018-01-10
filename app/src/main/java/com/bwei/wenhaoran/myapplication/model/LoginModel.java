package com.bwei.wenhaoran.myapplication.model;

import com.bwei.wenhaoran.myapplication.bean.LoginBean;
import com.bwei.wenhaoran.myapplication.net.GetDataInterface;
import com.bwei.wenhaoran.myapplication.net.LoggingInterceptor;
import com.bwei.wenhaoran.myapplication.net.RetrofitUnitl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class LoginModel {
    public void getLoginData(final String tel, String pwd, final ModelCallBack.LoginCallBack callBack){
        //使用okhttp请求,添加拦截器时把下面代码解释
        OkHttpClient ok = new OkHttpClient.Builder()
                .connectTimeout(20000, TimeUnit.SECONDS)
                .writeTimeout(20000, TimeUnit.SECONDS)
                .readTimeout(20000,TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .build();

        //使用Retrofit结合RxJava，okhttp封装类的单例模式
        Map<String,String> map = new HashMap<>();
        map.put("mobile",tel);
        map.put("password",pwd);

        RetrofitUnitl.getInstance("https://www.zhaoapi.cn",ok)
                .setCreate(GetDataInterface.class)
                .login(map)
                .subscribeOn(Schedulers.io())               //请求完成后在io线程中执行
                .observeOn(AndroidSchedulers.mainThread())  //最后在主线程中执行

                //进行事件的订阅，使用Consumer实现
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        //请求成功时返回数据
                        callBack.success(loginBean);
                        System.out.println("m登录信息：" + loginBean.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBack.failed(throwable);
                    }
                });
    }
}
