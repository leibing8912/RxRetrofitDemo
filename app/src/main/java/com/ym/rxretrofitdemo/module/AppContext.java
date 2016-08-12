package com.ym.rxretrofitdemo.module;

import android.app.Application;
import com.ym.rxretrofitdemo.BuildConfig;
import com.ym.rxretrofitdemo.commons.utils.LogUtil;

/**
 * @className: AppContext
 * @classDescription: 应用类单例，生命周期=程序生命周期，部分数据共享，缓存可放这里。
 * @author: leibing
 * @createTime: 2016/8/12
 */
public class AppContext extends Application {
    // 单例
    private static AppContext instance;

    /**
     * 单例
     * @author leibing
     * @createTime 2016/8/12
     * @lastModify 2016/8/12
     * @param
     * @return
     */
    public static AppContext getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        LogUtil.init(!BuildConfig.ISRELEASE);
    }
}
