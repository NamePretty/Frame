package com.example.frame.presenter;

import com.example.frame.base.contract.BasePresenter;
import com.example.frame.contract.LoginContract;
import com.example.frame.data.BaseResp;
import com.example.frame.data.bean.UserInfo;
import com.example.frame.model.ApiService;
import com.example.frame.model.ApiStore;
import com.example.frame.util.ConstantUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 登陆 presenter 层
 *
 * @packageName: com.example.frame.presenter
 * @fileName: LoginPresenter
 * @date: 2018/7/2 15:18
 * @author: zdj
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void login(String name, String password) {
        ApiStore.createApi(ApiService.class)
                .login(name,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResp<UserInfo>>() {
                    @Override
                    public void onError(Throwable e) {
                        if (e.getMessage() != null) {
                            view.loginErr(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResp<UserInfo> baseResp) {
                        if (baseResp.getCode() == ConstantUtil.REQUEST_SUCCESS) {
                            view.loginOk(baseResp.getData());
                        } else if (baseResp.getCode() == ConstantUtil.REQUEST_ERROR) {
                            view.loginErr(baseResp.getMsg());
                        }
                    }
                });

    }

}
