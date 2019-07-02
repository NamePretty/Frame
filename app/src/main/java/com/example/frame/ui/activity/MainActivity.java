package com.example.frame.ui.activity;

import android.Manifest;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.frame.R;
import com.example.frame.base.BaseActivity;
import com.example.frame.ui.fragment.IndexFragment;
import com.example.frame.ui.fragment.MineFragment;
import com.example.frame.util.BottomNavigationViewHelper;
import com.example.frame.util.toast.ToastUtil;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar_common)
    Toolbar toolbarCommon;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private List<Fragment> fragmentList;
    private int lastIndex;
    private long mExitTime;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectFragment(0);
                    return true;
                case R.id.navigation_mine:
                    selectFragment(1);
                    return true;
                default:
                    break;
            }
            return false;
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        initFragment();
        selectFragment(0);
        requestPermission();
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(toolbarCommon);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    /**
     * 设置默认选中fragment
     * @param index 碎片fragment
     */
    private void selectFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = fragmentList.get(index);
        Fragment lastFragment = fragmentList.get(lastIndex);
        lastIndex = index;
        ft.hide(lastFragment);
        if (!currentFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            ft.add(R.id.frame_layout, currentFragment);
        }
        ft.show(currentFragment);
        ft.commitAllowingStateLoss();
    }

    @Override
    protected void initView() {
        // 将item 设置为不移动
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    /**
     * 初始化碎片
     */
    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(IndexFragment.getInstance());
        fragmentList.add(MineFragment.getInstance());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtil.show(activity, getString(R.string.exit_system));
            mExitTime = System.currentTimeMillis();
            return false;
        } else {
            finish();
            return true;
        }
    }

    private void requestPermission() {
        AndPermission.with(this)
                .permission(Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                // 准备方法，和 okhttp 的拦截器一样，在请求权限之前先运行改方法，已经拥有权限不会触发该方法
                .rationale(new Rationale() {
                    @Override
                    public void showRationale(Context context, List<String> permissions, RequestExecutor executor) {
                        // 此处可以选择显示提示弹窗
                        executor.execute();
                    }
                })
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        ToastUtil.show(activity,"用户给权限啦");
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(activity, permissions)) {
                            // 打开权限设置页
                            AndPermission.permissionSetting(activity).execute();
                            return;
                        }
                        ToastUtil.show(activity,"用户拒绝权限");
                    }
                }).start();
    }


}
