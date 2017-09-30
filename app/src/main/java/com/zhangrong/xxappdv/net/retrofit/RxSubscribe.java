package com.zhangrong.xxappdv.net.retrofit;

import android.content.Context;

import rx.Subscriber;

/**
 * Created by zhang 16-10-31
 * Description: 自定义Subscribe
 */
public abstract class RxSubscribe<T> extends Subscriber<T> {
    private Context mContext;
    private String msg;

    protected boolean showDialog() {
        return true;
    }

    /**
     * @param context context
     * @param msg     dialog message
     */
    public RxSubscribe(Context context, String msg) {
        this.mContext = context;
        this.msg = msg;
    }

    protected RxSubscribe() {

    }

    /**
     * @param context context
     */
    public RxSubscribe(Context context) {
        this(context, "请稍后...");
    }

    @Override
    public void onCompleted() {
        if (showDialog()) {
            //dialog消失
        }


    }

    @Override
    public void onStart() {
        super.onStart();
        if (showDialog()) {
            //创建dialog并显示
            //点击取消的时候取消订阅

        }
    }

    @Override
    public void onNext(T t) {
        _onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (false) { //这里自行替换判断网络的代码  进行全局的错误处理
            _onError("网络不可用");
        } else if (e instanceof ServerException) {
            _onError(e.getMessage());
        } else {
            _onError(e.toString());
        }
        if (showDialog()) {
            //dialog消失
        }

    }

    protected abstract void _onSuccess(T t);

    protected abstract void _onError(String message);
    //定义一个dialog的不显示方法，进行让子类重写，不显示dialog



}
