package com.ym.rxretrofitdemo.httprequest;

import com.ym.rxretrofitdemo.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @className:YmApiRequest
 * @classDescription:网络请求
 * @author: leibing
 * @createTime: 2016/8/12
 */
public class YmApiRequest {
    // 单例
    private static YmApiRequest instance = null;
    // 请求地址
    private final static String TRANS_HTTP_URL = BuildConfig.API_URL;
    // Retrofit
    private Retrofit retrofit;

    private YmApiRequest(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(TRANS_HTTP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
    }

    public static YmApiRequest getInstance(){
        if (instance == null){
            instance = new YmApiRequest();
        }
        return instance;
    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }
}
