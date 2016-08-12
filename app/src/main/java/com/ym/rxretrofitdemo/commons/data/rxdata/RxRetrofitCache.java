package com.ym.rxretrofitdemo.commons.data.rxdata;

import android.content.Context;
import java.io.Serializable;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @className: RxRetrofitCache
 * @classDescription: RxJava + Retrofit 的缓存机制
 * @author: leibing
 * @createTime: 2016/8/12
 */
public class RxRetrofitCache {
    /**
     *
     * @param context
     * @param cacheKey 缓存key
     * @param expireTime 过期时间 0 表示有缓存就读，没有就从网络获取
     * @param fromNetwork 从网络获取的Observable
     * @param forceRefresh 是否强制刷新
     * @param <T>
     * @return
     */
    /**
     *
     * @author leibing
     * @createTime 2016/8/12
     * @lastModify 2016/8/12
     * @param context 上下文
     * @param cacheKey 缓存key
     * @param expireTime 缓存过期时间 (0:表示有缓存就读，没有就从网络获取)
     * @param fromNetwork 从网络获取的Observable
     * @param forceRefresh 是否强制刷新
     * @return
     */
    public static <T> Observable<T> load(final Context context, final String cacheKey,
                                         final long expireTime, Observable<T> fromNetwork,
                                         boolean forceRefresh) {
        Observable<T> fromCache = Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                T cache = (T) CacheManager.readObject(context, cacheKey,expireTime);
                if (cache != null) {
                    subscriber.onNext(cache);
                } else {
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        // 这里的fromNetwork 不需要指定Schedule,在handleRequest中已经变换了
        fromNetwork = fromNetwork.map(new Func1<T, T>() {
            @Override
            public T call(T result) {
                CacheManager.saveObject(context, (Serializable) result, cacheKey);
                return result;
            }
        });
        if (forceRefresh) {
            return fromNetwork;
        } else {
            return Observable.concat(fromCache, fromNetwork).first();
        }
    }
}
