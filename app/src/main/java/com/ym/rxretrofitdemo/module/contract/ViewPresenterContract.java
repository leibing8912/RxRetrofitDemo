package com.ym.rxretrofitdemo.module.contract;

import com.ym.rxretrofitdemo.module.presenter.BasePresenter;
import java.io.Serializable;

/**
 * @className: ViewPresenterContract
 * @classDescription:契约类
 * @author: leibing
 * @createTime: 2016/8/12
 */
public class ViewPresenterContract {
    public interface View extends BaseView{
        void updateUI(Serializable serializable); // 更新UI
    }

    public interface Presenter extends BasePresenter {
        void loadData(); // 加载数据
    }

    public interface LifeCycle extends BaseLifeCycle{
        void onRestart(); // 添加restart生命周期
    }
}
