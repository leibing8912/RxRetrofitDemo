package com.ym.rxretrofitdemo.module.contract;

/**
 * @className: BaseView
 * @classDescription: view基类
 * @author: leibing
 * @createTime: 2016/8/11
 */
public interface BaseView<T>{
    void setPresenter(ViewPresenterContract.Presenter presenter);
    void setLifeCycle(ViewPresenterContract.LifeCycle lifeCycle);
}
