package com.example.frame.base.contract;

/**
 * @packageName: com.example.frame.base.contract
 * @fileName: BasePer
 * @date: 2019/7/12  9:25
 * @author: zdj
 */

public interface BasePre<T extends BaseView> {

    /**
     * 注入View
     *
     * @param view view
     */
    void attachView(T view);

    /**
     * 回收View
     */
    void detachView();

}
