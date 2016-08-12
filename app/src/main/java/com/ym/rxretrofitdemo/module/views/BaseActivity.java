package com.ym.rxretrofitdemo.module.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.ym.rxretrofitdemo.module.AppManager;

/**
 * @className: BaseActivity
 * @classDescription: Activity基础类
 * @author: leibing
 * @createTime: 2016/8/12
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        AppManager.getInstance().finishActivity(this);
    }

    @Override
    public void onBackPressed() {
        AppManager.getInstance().finishActivity(this);
    }
}
