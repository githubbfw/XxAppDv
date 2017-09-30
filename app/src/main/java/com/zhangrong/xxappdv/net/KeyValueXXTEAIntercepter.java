package com.zhangrong.xxappdv.net;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhangrong on 2017/6/13.
 * <p>
 * 各别参数加密拦截
 */

class KeyValueXXTEAIntercepter implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        // 添加新的参数
        HttpUrl.Builder newUrlBuilder = request.url()
                .newBuilder()
                .scheme(request.url().scheme())
                .host(request.url().host())
                .addQueryParameter("method", request.url().host())//调用的方法名称
                .addQueryParameter("sys", "")//调用方的系统代码
                .addQueryParameter("verification_code", "")//验证码
                .addQueryParameter("key", "")//约定的接口密钥
                ;


        // 新的请求
        Request newRequest = request.newBuilder()
                .method(request.method(), request.body())
                .url(newUrlBuilder.build())
                .build();

        return chain.proceed(newRequest);
    }
}
