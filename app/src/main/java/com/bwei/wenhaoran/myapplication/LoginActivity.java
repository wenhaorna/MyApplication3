package com.bwei.wenhaoran.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwei.wenhaoran.myapplication.bean.LoginBean;
import com.bwei.wenhaoran.myapplication.presenter.LoginPresenter;
import com.bwei.wenhaoran.myapplication.view.MyView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class LoginActivity extends AppCompatActivity implements MyView.LoginView {
    @BindView(R.id.tel)
    EditText tel;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.reg)
    Button reg;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //调用p层
        presenter = new LoginPresenter(this);

    }

    @Override
    public void success(LoginBean loginBean) {
        Toast.makeText(this, "登录结果：  " + loginBean.getMsg(), Toast.LENGTH_SHORT).show();

        if(loginBean.getMsg().equals("登录成功")){
            try {
                Thread.sleep(1000);
                Intent intent = new Intent(LoginActivity.this, PersonInfoActivity.class);
                startActivity(intent);
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void failed(int code) {
        Toast.makeText(this, "登录失败！请检查登录信息" + code, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btn, R.id.reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn:
                if (!TextUtils.isEmpty(tel.getText().toString()) && !TextUtils.isEmpty(pwd.getText().toString())) {
                    //如果都不为空,请求接口
                    presenter.getData(tel.getText().toString(), pwd.getText().toString());
                } else {
                    Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.reg:      //注册页
                Intent intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);
                break;
        }
    }
}
