package com.ym.rxretrofitdemo.httprequest.rxrequest;

import com.ym.rxretrofitdemo.httprequest.BaseModel;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @className:RxHelper
 * @classDescription: Rx 一些巧妙的处理
 * @author: leibing
 * @createTime: 2016/8/12
 */
public class RxHelper {

    /**
     * 对结果进行预处理
     * @author leibing
     * @createTime 2016/8/12
     * @lastModify 2016/8/12
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<BaseModel<T>, T> handleResult() {
        return new Observable.Transformer<BaseModel<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseModel<T>> tObservable) {
                return tObservable.flatMap(new Func1<BaseModel<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseModel<T> result) {
                        if (result.success()) {
                            return createData(result.result);
                        } else {
                            return Observable.error(new ServerException(result.reason));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 创建成功的数据
     * @author leibing
     * @createTime 2016/8/12
     * @lastModify 2016/8/12
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
