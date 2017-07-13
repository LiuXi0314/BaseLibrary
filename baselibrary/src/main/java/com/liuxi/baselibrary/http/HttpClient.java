package com.liuxi.baselibrary.http;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 基于OkHttp的封装类,
 * 用于管理Http请求
 */

public class HttpClient {

    private void get(String url, final ApiCallBack apiCallBack) {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url("").build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (apiCallBack != null) {
                    apiCallBack.onFail(e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (apiCallBack != null) {
                    apiCallBack.onSuccess(response);
                }
            }
        });
    }

    private void post(String url, final ApiCallBack apiCallBack) {
        OkHttpClient httpClient = new OkHttpClient();
        FormBody body = new FormBody.Builder().add("", "").build();
        Request request = new Request.Builder().post(body).build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (apiCallBack != null) {
                    apiCallBack.onFail(e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (apiCallBack != null) {
                    apiCallBack.onSuccess(response);
                }
            }
        });
    }
}
