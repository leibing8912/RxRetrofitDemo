package com.ym.rxretrofitdemo.httprequest;

import java.io.Serializable;

/**
 * @className: BaseModel
 * @classDescription: 网络请求响应基类
 * @author: leibing
 * @createTime: 2016/8/12
 */
public class BaseModel<T> implements Serializable {
    private static final long serialVersionUID = 4835053302565627292L;

    public int error_code;
    public String reason;
    public T result;

    public boolean success(){
        return reason.equals("successed!");
    }
}