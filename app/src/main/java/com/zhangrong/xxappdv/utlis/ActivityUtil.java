package com.zhangrong.xxappdv.utlis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;

import com.zhangrong.xxappdv.R;


/**
 * Created by xiaozhang on 2016/5/26.
 * <p/>
 * Activity辅助类
 */
public class ActivityUtil {


    /**
     * 跳转至应用详情页面
     */
    public static void runToAppInfoDetailActivity(Context context) {

        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        context.startActivity(intent);

    }


    /**
     * 执行到下一个页面
     *
     * @param activity
     * @param cls            目标跳转页面
     * @param isCloseCurrent 是否关闭当前页面
     */
    public static void goForward(Activity activity, Class<?> cls, boolean isCloseCurrent) {

        activity.startActivity(new Intent(activity, cls));
        if (isCloseCurrent) activity.finish();
        activity.overridePendingTransition(R.anim.act_slide_in_right, R.anim.act_slide_out_left);

    }


    /**
     * 执行到下一个页面
     *
     * @param activity       当前Activity对象
     * @param targetAction
     * @param isCloseCurrent 是否关闭当前页面
     */
    public static void goForward(Activity activity, String targetAction, boolean isCloseCurrent) {

        activity.startActivity(new Intent(targetAction));
        if (isCloseCurrent) activity.finish();
        activity.overridePendingTransition(R.anim.act_slide_in_right, R.anim.act_slide_out_left);


    }


    /**
     * 执行到下一个页面
     *
     * @param activity 当前Activity对象
     * @param cls      目标跳转页面
     */
    public static void goForward(Activity activity, Class<?> cls, Bundle data, boolean isFinishCurrent) {

        Intent intent = new Intent(activity, cls);
        if (data != null) intent.putExtras(data);//当数据包不为空时写入数据
        activity.startActivity(intent);
        if (isFinishCurrent) activity.finish();
        activity.overridePendingTransition(R.anim.act_slide_in_right, R.anim.act_slide_out_left);

    }

    /**
     * 执行到下一个页面
     *
     * @param activity     当前Activity对象
     * @param targetAction 目标跳转页面意向
     */
    public static void goForward(Activity activity, String targetAction,
                                 Bundle data, boolean isFinishCurrent) {

        Intent intent = new Intent(targetAction);
        if (data != null) intent.putExtras(data);//当数据包不为空时写入数据
        activity.startActivity(intent);
        if (isFinishCurrent) activity.finish();
        activity.overridePendingTransition(R.anim.act_slide_in_right, R.anim.act_slide_out_left);


    }


    /**
     * 执行到下一个页面
     *
     * @param activity    当前Activity对象
     * @param cls         目标跳转页面
     * @param requestCode 页面请求码
     */
    public static void goForward(Activity activity, Class<?> cls, int requestCode) {

        activity.startActivityForResult(new Intent(activity, cls), requestCode);
        activity.overridePendingTransition(R.anim.act_slide_in_right, R.anim.act_slide_out_left);

    }


    /**
     * 执行到下一个页面
     *
     * @param activity    当前Activity对象
     * @param cls         目标跳转页面
     * @param requestCode 页面请求码
     * @param data        需要发送的数据
     */
    public static void goForward(Activity activity, Class<?> cls, int requestCode, Bundle data) {

        Intent intent = new Intent(activity, cls);
        if (data != null) intent.putExtras(data);//当数据包不为空时写入数据
        activity.startActivityForResult(intent, requestCode);
        activity.overridePendingTransition(R.anim.act_slide_in_right, R.anim.act_slide_out_left);


    }

    /**
     * 执行到下一个页面
     *
     * @param activity    当前Activity对象
     * @param fragment    当前fragment对象
     * @param cls         目标跳转页面
     * @param requestCode 页面请求码
     */
    public static void goForward(Activity activity, Fragment fragment, Class<?> cls, int requestCode) {


        fragment.startActivityForResult(new Intent(activity, cls), requestCode);
        activity.overridePendingTransition(R.anim.act_slide_in_right, R.anim.act_slide_out_left);


    }


    /**
     * 执行到下一个页面
     *
     * @param activity    当前Activity对象
     * @param fragment    当前fragment对象
     * @param cls         目标跳转页面
     * @param requestCode 页面请求码
     * @param data        需要发送的数据
     */
    public static void goForward(Activity activity, Fragment fragment, Class<?> cls,
                                 int requestCode, Bundle data) {

        Intent intent = new Intent(activity, cls);
        if (data != null) intent.putExtras(data);//当数据包不为空时写入数据
        fragment.startActivityForResult(intent, requestCode);
        activity.overridePendingTransition(R.anim.act_slide_in_right, R.anim.act_slide_out_left);


    }


    /**
     * 页面返回，带动画
     *
     * @param activity 当前Activity对象
     */
    public static void goBack(Activity activity) {

        activity.finish();
        activity.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);


    }


    /**
     * 页面返回，带动画
     *
     * @param activity   当前Activity对象
     * @param resultCode 请求结果码
     */
    public static void goBack(Activity activity, int resultCode) {

        activity.setResult(resultCode);
        activity.finish();
        activity.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);


    }


    /**
     * 页面返回，带动画
     *
     * @param activity   当前Activity对象
     * @param resultCode 请求结果码
     * @param data       需要返回的数据
     */
    public static void goBack(Activity activity, int resultCode, Intent data) {


        activity.setResult(resultCode, data);
        activity.finish();
        activity.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);


    }


    /**
     * 强制退出应用程序
     */
    public static void abortExit() {


        System.exit(0);//java代码式退出
        android.os.Process.killProcess(android.os.Process.myUid());
        android.os.Process.killProcess(android.os.Process.myPid());
        android.os.Process.killProcess(android.os.Process.myTid());


    }


}
