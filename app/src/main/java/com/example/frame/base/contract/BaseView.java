package com.example.frame.base.contract;

/**
 * view 基础接口
 * @packageName: com.example.frame.base.contract
 * @fileName: BaseView
 * @date: 2019/7/12  9:33
 * @author: zdj
 */

public interface BaseView {

    /**
     * showNormal
     */
    void showNormal();

    /**
     * Show error
     */
    void showError(String err);

    /**
     * Show loading
     */
    void showLoading();

    /**
     * Show empty
     */
    void showEmpty();

    /**
     * Reload
     */
    void reload();
}
