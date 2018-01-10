package com.bwei.wenhaoran.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.wenhaoran.myapplication.bean.RegBean;
import com.bwei.wenhaoran.myapplication.presenter.RegPresenter;
import com.bwei.wenhaoran.myapplication.view.MyView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class RegActivity extends AppCompatActivity implements MyView.RegView {
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.regTel)
    EditText regTel;
    @BindView(R.id.regPwd)
    EditText regPwd;
    @BindView(R.id.regNow)
    Button regNow;
    private RegPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);

        //调用p层
        presenter = new RegPresenter(this);
    }

    @Override
    public void failed(int code) {
        Toast.makeText(this, "注册失败！请检查网络"+ code, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sucess(RegBean regBean) {
        Toast.makeText(this, "注册结果：      "+regBean.getMsg(), Toast.LENGTH_SHORT).show();

        if(regBean.getMsg().equals("注册成功")){
            try {
                Thread.sleep(2000);
                Toast.makeText(this, "即将跳转到登录页面", Toast.LENGTH_SHORT).show();
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick({R.id.back, R.id.regNow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                //点击返回按钮，返回到登录页面
                finish();
                break;

            case R.id.regNow: //注册按钮

                if (!TextUtils.isEmpty(regTel.getText().toString()) && !TextUtils.isEmpty(regPwd.getText().toString())) {

                    if (regTel.getText().toString().length() < 6){
                        Toast.makeText(this, "密码长度不能小于 6 位", Toast.LENGTH_SHORT).show();
                    }else {
                        // mvp请求注册的接口
                        presenter.getData(regTel.getText().toString(),regPwd.getText().toString());
                    }

                } else {
                    Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
