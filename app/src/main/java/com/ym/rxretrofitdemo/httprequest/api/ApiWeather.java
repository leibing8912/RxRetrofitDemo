package com.ym.rxretrofitdemo.httprequest.api;

import com.ym.rxretrofitdemo.httprequest.BaseModel;
import com.ym.rxretrofitdemo.module.model.MainModel;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @className: ApiWeather
 * @classDescription: 天气Api
 * @author: leibing
 * @createTime: 2016/8/12
 */
public interface ApiWeather {

    // 获取天气信息
    @FormUrlEncoded
    @POST("onebox/weather/query")
    Observable<BaseModel<MainModel>> getWeatherInfo(
            @Field("cityname") String cityName,
            @Field("key") String key
    );
}
