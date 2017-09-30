package com.zhangrong.xxappdv.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhang on 2016/5/21.
 * <p>
 * app异常管理
 */
public class AppCrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "AppCrashHandler";  //标记
    private static final boolean DEBUG = true;  //默认判断，用于sd卡

    private static final String PATH = Environment.getExternalStorageDirectory()
            .getPath() + "/ZhiZhu/crashLog/";//存储异常路径

    private static final String FILE_NAME = "spider_crash_log"; //异常文件名
    private static final String FILE_NAME_SUFFTX = ".txt";//异常文件的后缀

    private static AppCrashHandler sInstance = new AppCrashHandler();//创建对象
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;//默认异常处理
    private Context context;//上下文

    private AppCrashHandler() {
    }

    public static AppCrashHandler getInstance() { //获得实例
        return sInstance;
    }

    /**
     * 初始化
     */
    public void init(Context mcontext) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler(); //存储默认的异常处理对象
        Thread.setDefaultUncaughtExceptionHandler(this); //设置为我们的异常处理
        context = mcontext.getApplicationContext();

    }

    /**
     * 这个是最关键的函数，当程序中有未被捕获的异常，系统将会自动调用#uncaughtException方法
     *
     * @param thread 捕获不是异常的线程信息
     * @param ex     捕获出现异常的线程信息
     */

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        //将异常写到sd卡
        dumpExceptionSD(ex);
        //上传异常到服务器
        uploadExceptionServes(ex);

        ex.printStackTrace();//打印异常堆栈信息
        //可以进行提示操作，拓展
        Toast.makeText(context, "不好意思程序崩溃了，我们正在不断的完善！！！", Toast.LENGTH_SHORT).show();
        //系统默认的提示框----我们使用自定义的
//        if (mDefaultCrashHandler != null) {
//            mDefaultCrashHandler.uncaughtException(thread, ex);//记录异常
//        } else {
//            android.os.Process.killProcess(Process.myPid());//杀死线程
//        }
        Process.killProcess(Process.myPid());//杀死进程


    }


    /**
     * 上传异常到服务器
     */
    private void uploadExceptionServes(Throwable ex) {
        //上传操作

    }

    /**
     * 写异常到sd卡
     */
    private void dumpExceptionSD(Throwable ex) {
        //如果sd卡不存在或无法使用，则无法把异常信息写入sd卡
        if (!Environment.getExternalStorageState().equals(Environment.
                MEDIA_MOUNTED)) {
            if (DEBUG) {
                Log.w(TAG, "没有安装sd卡，不能进行存储，跳过");
                return;
            }

        }

        //判断创建文件路径
        File dir = new File(PATH);
        if (!dir.exists()) {
            //如果不存在，创建文件路径
            dir.mkdirs();
        }

        //当前系统时间
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
        File file = new File(PATH + FILE_NAME + time + FILE_NAME_SUFFTX);
        try {
            //打印输出流
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println(time); //打印时间
            dumpPhoneInfo(pw);//打印使用信息
            pw.println();
            ex.printStackTrace(pw); //打印堆栈信息
            pw.close(); //关闭流


        } catch (IOException e) {

            Log.e(TAG, "写入异常和配置信息到文件");
        }


    }

    //拿到手机配置信息的方法
    private void dumpPhoneInfo(PrintWriter pw) {
        //获取配置信息
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            pw.print("APP Version: "); //app版本号
            pw.print(pi.versionName);
            pw.print("_");
            pw.println(pi.versionCode);

            //android版本号
            pw.print("OS Version: ");
            pw.print(Build.VERSION.RELEASE);
            pw.print("_");
            pw.println(Build.VERSION.SDK_INT);

            //手机制造商
            pw.print("Vendor:");
            pw.println(Build.MANUFACTURER);

            //手机型号
            pw.print("Model: ");
            pw.println(Build.MODEL);

            //获取本机号码
            TelephonyManager phoneMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String strPhoneNum = phoneMgr.getLine1Number();
            pw.print("PhoneNum: ");
            pw.print(strPhoneNum);

            //cpu框架
            pw.print("CPU ABT: ");
            pw.println(Build.CPU_ABI);

        } catch (PackageManager.NameNotFoundException e) {

        }
    }
}
