package com.ym.rxretrofitdemo.module.contract;

/**
 * @className: BaseLifeCycle
 * @classDescription: 生命周期基类
 * @author: leibing
 * @createTime: 2016/8/12
 */
public interface BaseLifeCycle {
    void onCreate();
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
}
