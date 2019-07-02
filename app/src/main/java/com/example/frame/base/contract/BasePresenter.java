package com.example.frame.base.contract;

/**
 * @packageName: com.example.frame.base.contract
 * @fileName: BasePresenter
 * @date: 2019/7/12  9:27
 * @author: zdj
 */

public class BasePresenter<T extends BaseView> implements BasePre<T> {

    protected T mView;
    protected int currentPage;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
