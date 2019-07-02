package com.example.frame.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.frame.R;
import com.example.frame.data.bean.UserInfo;
import com.example.frame.util.Log.AppLogMessageUtil;
import com.example.frame.util.davik.AppDavikActivityUtil;
import com.example.frame.util.network.NetUtil;
import com.example.frame.util.network.NetWorkBroadcastReceiver;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *  基础 activity
 *
 * @packageName: com.example.frame.base
 * @fileName: BaseActivity
 * @date: 2018/7/19  14:48
 * @author: ymc
 * @QQ:745612618
 */

public abstract class BaseActivity extends AppCompatActivity implements NetWorkBroadcastReceiver.NetEvent{
    public static NetWorkBroadcastReceiver.NetEvent netEvent;
    public AppDavikActivityUtil appDavikActivityUtil = AppDavikActivityUtil.getScreenManager();

    protected MyApplication context;
    protected BaseActivity activity;

    private Unbinder bun;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        bun = ButterKnife.bind(this);
        appDavikActivityUtil.addActivity(this);
        context = MyApplication.getInstance();
        activity = this;
        netEvent = this;
        initStatusColor();
        initToolbar();
        initView();
        initData();
    }

    private void initStatusColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Window window = activity.getWindow();
            //设置透明状态栏,这样才能让 ContentView 向上  6.0小米手机设置 tootlbar 会被挤上去
            //window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            window.setStatusBarColor(getColor(R.color.theme));

            ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
                ViewCompat.setFitsSystemWindows(mChildView, false);
            }
        }
    }

    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void initData();
    /**
     * 初始化toolbar
     */
    protected void initToolbar(){}

    @Override
    public void onNetChange(int netMobile) {
        if (netMobile == NetUtil.NETWORK_NONE) {
            AppLogMessageUtil.e("NETWORK_NONE");
        } else{
            AppLogMessageUtil.e("NETWORK_NORMAL");
        }
    }


    @Override
    protected void onDestroy() {
        appDavikActivityUtil.removeActivity(this);
        bun.unbind();
        super.onDestroy();
    }
}
