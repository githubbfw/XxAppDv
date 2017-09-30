package com.zhangrong.xxappdv.net;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by zhang on 2016/5/31.
 * <p>
 * 自定义请求RequestBody
 */
public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    /**
     * 构造器
     */

    public JsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }


    @Override
    public RequestBody convert(T value) throws IOException {
        APIBodyData data = new APIBodyData();
        data.setData(value.toString());
        String postBody = gson.toJson(data); //对象转化成json
        Log.i("request", "转化后的数据：" + value.toString());
        return RequestBody.create(MEDIA_TYPE, postBody);

    }


}
