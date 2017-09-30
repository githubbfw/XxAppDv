package com.zhangrong.xxappdv.base.baseother;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhang on 2016/11/21.
 */
public class BaseFragment extends Fragment {
    public CompositeSubscription compositeSubscription = new CompositeSubscription();//管理所有的订阅
    public Subscription subscription;//订阅
    public Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        EventBus.getDefault().register(this);//EventBus注册
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    /**
     * 检测网络是否可用
     */
    public boolean isNetworkConnected() {

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);//EventBus取消注册
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {

    }

}
