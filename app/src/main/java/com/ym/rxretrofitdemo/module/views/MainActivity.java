package com.ym.rxretrofitdemo.module.views;

import android.os.Bundle;
import com.ym.rxretrofitdemo.R;
import com.ym.rxretrofitdemo.module.contract.ViewPresenterContract;
import com.ym.rxretrofitdemo.module.model.MainModel;
import com.ym.rxretrofitdemo.module.presenter.MainPresenter;
import java.io.Serializable;

public class MainActivity extends BaseActivity implements ViewPresenterContract.View{
    // 逻辑
    private ViewPresenterContract.Presenter presenter;
    // 生命周期
    private ViewPresenterContract.LifeCycle lifeCycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化逻辑实例
        new MainPresenter(this);
        presenter.start();
        // View映射onCreate生命周期到Presenter
        lifeCycle.onCreate();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // View映射onRestart生命周期到Presenter
        lifeCycle.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // View映射onStart生命周期到Presenter
        lifeCycle.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // View映射onResume生命周期到Presenter
        lifeCycle.onResume();
    }

    @Override
    protected void onPause() {
        // View映射onPause生命周期到Presenter
        lifeCycle.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        // View映射onStop生命周期到Presenter
        lifeCycle.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // View映射onDestroy生命周期到Presenter
        lifeCycle.onDestroy();
        super.onDestroy();
    }

    @Override
    public void updateUI(Serializable serializable) {
        MainModel mainModel = (MainModel) serializable;
        // 更新UI操作
        // DO......
    }

    @Override
    public void setPresenter(ViewPresenterContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setLifeCycle(ViewPresenterContract.LifeCycle lifeCycle) {
        this.lifeCycle = lifeCycle;
    }
}
