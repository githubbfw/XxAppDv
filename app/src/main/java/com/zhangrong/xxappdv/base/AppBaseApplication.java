package com.zhangrong.xxappdv.base;

import android.app.Application;
import android.content.Context;
import android.view.WindowManager;


/**
 * Created by zhang on 2016/11/18.
 */
public class AppBaseApplication extends Application {

    private static AppBaseApplication sInstance;//实例

    private static boolean isLogin = false;//用户是否登陆


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        //自定义获取异常信息(将异常写入sd卡)
        AppCrashHandler appCrashHandler = AppCrashHandler.getInstance();
        appCrashHandler.init(this);

    }


    public static AppBaseApplication getsInstance() {
        return sInstance;
    }


    public static boolean isLogin() {
        return isLogin;
    }

    public static void setIsLogin(boolean isLogin) {
        AppBaseApplication.isLogin = isLogin;
    }


    public static void setsInstance(AppBaseApplication sInstance) {
        AppBaseApplication.sInstance = sInstance;
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public int getScreenWidth() {
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();// 屏幕宽度
        return width;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public int getScreenHeight() {
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();// 屏幕高度
        return height;
    }
}
