package com.example.frame.ui.fragment;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.frame.R;
import com.example.frame.base.BaseFragment;
import com.example.frame.ui.activity.AboutActivity;
import com.example.frame.ui.activity.LoginActivity;
import com.example.frame.util.JumpUtil;
import com.example.frame.util.toast.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MineFragment extends BaseFragment {

    @BindView(R.id.image_head)
    CircleImageView imageHead;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.rl_about)
    RelativeLayout rlAbout;
    @BindView(R.id.tv_logout)
    TextView tvLogout;
    public static MineFragment getInstance() {
        return new MineFragment();
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_mine;
    }


    @Override
    protected void initUI() {
        super.initUI();
        Glide.with(context).load(R.drawable.icon_head).into(imageHead);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.rl_about, R.id.image_head, R.id.tv_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_about:
                JumpUtil.overlay(context, AboutActivity.class);
                break;
            case R.id.image_head:
                JumpUtil.overlay(context, LoginActivity.class);
                break;
            case R.id.tv_logout:
                ToastUtil.show(activity, getString(R.string.logout_ok));
                break;
            default:
                break;
        }
    }
}
