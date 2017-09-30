package com.zhangrong.xxappdv.net;

import com.zhangrong.xxappdv.net.netother.HttpConstant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by zhang on 2016/5/7.
 */
public class NetWork {

    public NetWork() {

    }

    /**
     * HttpLoggingInterceptor 是一个okhttp拦截器，输出网络请求和结果的 Log
     * 它有三种模式 BASIC / HEADERS / BODY  基础，头，身体
     * MarvelSigningInterceptor 也是一个okhttp拦截器 ，用于密钥使用的
     * ---Okhttpclient 的创建时通过Builder的build（）方法创建的，其中的参数，都是设置配置
     */

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()

            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))//添加一个拦截器
//                    .addNetworkInterceptor(mTokenInterceptor) //让所有网络请求都附上你的拦截器
            //.connectTimeout   设置连接时间
            // .retryOnConnectionFailure   设置连接错误进行重新连接
            //.authenticator()  设置身份验证
            //.cache()  设置网络请求缓存
            //.dispatcher() 分发
            // .proxy() 代理
            // .dns()  dns
            //.authenticator()  证书
            //.proxyAuthenticator()  代理证书
            .addInterceptor(new KeyValueXXTEAIntercepter())//各别参数加密拦截
            .connectTimeout(5000, TimeUnit.MINUTES)
            .writeTimeout(5000, TimeUnit.MINUTES)
            .readTimeout(5000, TimeUnit.MINUTES)
            .build();
    /**
     * Retrofit2 使用配置 通过Builder创建
     * 它的最简单配置：
     * Retrofit retrofit = new Retrofit.Builder()
     * .baseUrl("https://api.github.com/")
     * .build();
     */
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(HttpConstant.BASE_ZURL)//  * .baseUrl  是设置连接的地址
            .client(okHttpClient)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//* .addCallAdapterFactory 添加Rxjava适配器
            .addConverterFactory(GsonConverterFactory.create())//* .addConverterFactory  添加自定义转换器
            .build();

    public static <T> T createApi(Class<T> tClass) {

        return retrofit.create(tClass);

    }


}
