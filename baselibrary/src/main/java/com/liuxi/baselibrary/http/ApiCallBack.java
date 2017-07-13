package com.liuxi.baselibrary.http;

import okhttp3.Response;

/**
 * 网络请求回调
 */

public interface ApiCallBack {
    public void onSuccess(Response response);
    public void onFail(Exception e);
}
