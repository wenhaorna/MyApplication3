package com.bwei.wenhaoran.myapplication.net;

import com.bwei.wenhaoran.myapplication.bean.LoginBean;
import com.bwei.wenhaoran.myapplication.bean.PersonInfoBean;
import com.bwei.wenhaoran.myapplication.bean.RegBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public interface GetDataInterface {
    //注册的接口
    //https://www.zhaoapi.cn/user/reg?mobile=18631090582&password=888888
    @FormUrlEncoded
    @POST("/user/reg")
    Observable<RegBean> reg(@FieldMap Map<String,String> map);

    //登录的接口
    //https://www.zhaoapi.cn/user/login?mobile=18631090582&password=888888
    @FormUrlEncoded
    @POST("/user/login")
    Observable<LoginBean> login(@FieldMap Map<String,String> map);

    //个人中心接口：
    //https://www.zhaoapi.cn/user/getUserInfo?uid=100
    @GET("/user/getUserInfo")
    Observable<PersonInfoBean> person(@Query("uid") int uid);
}
