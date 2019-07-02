package com.example.frame.contract;


import com.example.frame.base.contract.BasePre;
import com.example.frame.base.contract.BaseView;
import com.example.frame.data.bean.UserInfo;

/**
 * login 登陆
 *
 * @packageName: com.example.frame.contract
 * @fileName: LoginContrat
 * @date: 2019/7/2  15:11
 * @author: zdj
 */

public class LoginContract {

    public interface View extends BaseView {

        void loginOk(UserInfo userInfo);

        void loginErr(String info);

    }

    public interface Presenter extends BasePre<View> {

        void login(String name, String password);

    }
}
