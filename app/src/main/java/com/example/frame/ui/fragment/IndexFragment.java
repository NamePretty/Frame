package com.example.frame.ui.fragment;

import com.example.frame.R;
import com.example.frame.base.BaseFragment;

public class IndexFragment  extends BaseFragment {

    public static IndexFragment getInstance() {
        return new IndexFragment();
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initData() {

    }
}
