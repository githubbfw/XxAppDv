package com.zhangrong.xxappdv.base.baseother;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zhangrong.xxappdv.R;
import com.zhangrong.xxappdv.base.baseview.SystemBarTintManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/10/24.
 * <p>
 * 公用的activity的基类
 */
public class BaseActivity extends FragmentActivity implements View.OnClickListener {
    public CompositeSubscription compositeSubscription = new CompositeSubscription();//管理所有的订阅
    public Subscription subscription;//订阅
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusBar();//初始化沉浸式”状态栏“
        this.context = this;
        EventBus.getDefault().register(this);//EventBus注册
    }


    /**
     * 检测网络是否可用
     */
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    //字体不变
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());

        return res;

    }

    /**
     * 设置头部状态栏
     */

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void initStatusBar() {
        //sdk为4.4的处理方式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.statusbar_color);//通知栏所需颜色
    }

    //设置sdk为4.4的沉浸式”状态栏“
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    /**
     * 建立Composite订阅管理，取消所有订阅  rxjava中使用
     */
    public void detachCompositeSubscription() {
        if (compositeSubscription.hasSubscriptions()) {
            //取消注册，以避免内存泄露
            compositeSubscription.unsubscribe();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachCompositeSubscription();//取消所有的订阅
        EventBus.getDefault().unregister(this);//EventBus取消注册
    }

    @Override
    public void onClick(View v) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event){

    }
}
