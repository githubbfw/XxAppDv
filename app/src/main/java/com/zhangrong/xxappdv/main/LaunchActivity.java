package com.zhangrong.xxappdv.main;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.zhangrong.xxappdv.R;
import com.zhangrong.xxappdv.main.ui.activity.GuideActivity;
import com.zhangrong.xxappdv.utlis.ActivityUtil;


/**
 * Created by zhang on 2016/11/18.
 * <p/>
 * 启动页面
 */
public class LaunchActivity extends Activity {

    private final String ISFIRSTLAUNCHES = "isFirstLaunches";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置页面为全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_launch_layout);
        initViews();
        initAppRequestPermission();//初始化app开启的6.0的权限


    }

    /**
     * 初始化视图
     */

    private void initViews() {


    }

    /**
     * 初始化app开启的6.0的权限
     */
    private void initAppRequestPermission() {
        if (Build.VERSION.SDK_INT >= 23) {//6.0开启app
            SharedPreferences sharedPreferences = getSharedPreferences(ISFIRSTLAUNCHES, MODE_PRIVATE);
            final SharedPreferences.Editor editor = sharedPreferences.edit();

            boolean isFirstAwardPermission = sharedPreferences.getBoolean("isFirstAwardPermission", true);
            //提示弹框，进行6.0权限处理
            if (isFirstAwardPermission) {
                new AlertDialog.Builder(LaunchActivity.this)
                        .setTitle("开启XxX")
                        .setMessage("使用XxX，需要以下权限：\n \n访问存储空间\n读取设备信息\n读取位置信息")
                        .setPositiveButton("下一步", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                editor.putBoolean("isFirstAwardPermission", false);
                                editor.commit();
                                startPermission();
                            }
                        }).show();
            } else {
                startPermission();
            }
        } else {//其他版本开启app

            new Handler().postDelayed(new Runnable() {
                public void run() {//跳首页

                    ActivityUtil.goForward(LaunchActivity.this, MainActivity.class, true);

                }
            }, 3000);

        }

    }

    private void startPermission() {

        AndPermission.with(LaunchActivity.this)
                .requestCode(88861)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE,//sd权限
                        Manifest.permission.ACCESS_FINE_LOCATION,//定位
                        Manifest.permission.READ_PHONE_STATE//手机信息
                )
                .send();
    }

    //6.0权限结果处理回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionYes(88861)
    private void getLocationPermissionYes() {
        //权限获取成功开启app
        disposeFirstLaunchesIntent();

    }

    @PermissionNo(88861)
    private void getLocationPermissionNo() {
        Toast.makeText(LaunchActivity.this, "有权限未通过，无法使用app", Toast.LENGTH_SHORT).show();

    }

    //处理第一次启动跳转
    private void disposeFirstLaunchesIntent() {

        SharedPreferences sharedPreferences = getSharedPreferences(ISFIRSTLAUNCHES, MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean isFirstLaunches = sharedPreferences.getBoolean("isFirstLaunches", true);
        if (isFirstLaunches) {//跳转引导界面

            ActivityUtil.goForward(LaunchActivity.this, GuideActivity.class, true);
            initGuide(sharedPreferences, editor);

        } else {//跳转首页
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    ActivityUtil.goForward(LaunchActivity.this, MainActivity.class, true);
                }
            }, 3000);
        }
    }

    //改变isFirstLaunches启动值
    private void initGuide(SharedPreferences sharedPreferences, SharedPreferences.Editor editor) {
        editor.putBoolean("isFirstLaunches", false);
        editor.commit();

    }

}
