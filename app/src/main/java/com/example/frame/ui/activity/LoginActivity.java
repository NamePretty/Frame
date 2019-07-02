package com.example.frame.ui.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.frame.R;
import com.example.frame.base.BaseActivity;
import com.example.frame.contract.LoginContract;
import com.example.frame.data.bean.UserInfo;
import com.example.frame.util.Log.AppLogMessageUtil;
import com.example.frame.util.toast.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View{

    @BindView(R.id.toolbar_login)
    Toolbar toolbarLogin;
    @BindView(R.id.et_ensure_username)
    EditText etEnsureUsername;
    @BindView(R.id.et_ensure_password)
    EditText etEnsurePassword;

    private String username, password;
    LoginContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(toolbarLogin);
        getSupportActionBar().setTitle(getString(R.string.login));
        toolbarLogin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.tv_register, R.id.btn_login})
    void click(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                //JumpUtil.overlay(context, RegisterActivity.class);
                break;
            case R.id.btn_login:
                check();
                break;
            default:
                break;
        }
    }

    private void check() {
        username = etEnsureUsername.getText().toString();
        password = etEnsurePassword.getText().toString();
        if(TextUtils.isEmpty(username)){
            ToastUtil.show(LoginActivity.this,"请输入用户名");
           return;
        }
        if(TextUtils.isEmpty(password)){
            ToastUtil.show(LoginActivity.this,"请输入密码");
            return;
        }
        AppLogMessageUtil.e("username",username);
        AppLogMessageUtil.e("password",password);
        presenter.login(username, password);
    }

    @Override
    public void loginOk(UserInfo userInfo) {
        ToastUtil.show(activity, getString(R.string.login_ok));
        AppLogMessageUtil.d("TAG",userInfo.getUserName());
        //JumpUtil.overlay(this, MainActivity.class);
        finish();
    }

    @Override
    public void loginErr(String info) {
        ToastUtil.show(activity, info);
    }

    @Override
    public void showNormal() {

    }

    @Override
    public void showError(String err) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void reload() {

    }
}
