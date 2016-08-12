package com.ym.rxretrofitdemo.module.presenter;

import com.ym.rxretrofitdemo.commons.data.rxdata.RxRetrofitCache;
import com.ym.rxretrofitdemo.httprequest.YmApiRequest;
import com.ym.rxretrofitdemo.httprequest.api.ApiWeather;
import com.ym.rxretrofitdemo.httprequest.rxrequest.RxHelper;
import com.ym.rxretrofitdemo.httprequest.rxrequest.RxSubscribe;
import com.ym.rxretrofitdemo.module.AppManager;
import com.ym.rxretrofitdemo.module.contract.ViewPresenterContract;
import com.ym.rxretrofitdemo.module.model.MainModel;
import rx.Observable;

/**
 * @className: MainPresenter
 * @classDescription:
 * @author: leibing
 * @createTime: 2016/8/12
 */
public class MainPresenter implements ViewPresenterContract.Presenter, ViewPresenterContract.LifeCycle{
    public static final String WEATHER_KEY = "a2da70728aa47f71edd6411ec13021f5";
    public static final String WEATHER_CITY = "广州";
    public final static String CACHE_KEY = "cache_key";
    public final static int CACHE_TIME = 10*60*60;
    public final static String REQUEST_TITLE = "正在加载";
    private ViewPresenterContract.View view;
    private ApiWeather mApi;

    public MainPresenter(ViewPresenterContract.View view){
        this.view = view;
        view.setPresenter(this);
        view.setLifeCycle(this);
        mApi = YmApiRequest.getInstance().create(ApiWeather.class);
    }

    @Override
    public void loadData() {
        try {
            Observable<MainModel> fromNetwrok = mApi.getWeatherInfo(WEATHER_CITY, WEATHER_KEY)
                    .compose(RxHelper.<MainModel> handleResult());
            RxRetrofitCache.load(AppManager.getInstance().currentActivity(),
                    CACHE_KEY,CACHE_TIME,fromNetwrok,false)
                    .subscribe(new RxSubscribe<MainModel>(AppManager.getInstance().currentActivity(),
                            REQUEST_TITLE) {
                        @Override
                        protected void _onNext(MainModel model) {
                            view.updateUI(model);
                        }

                        @Override
                        protected void _onError(String message) {

                        }

                    });
        }catch (Exception ex){
        }
    }

    @Override
    public void start() {
        // 加载数据
        loadData();
    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
