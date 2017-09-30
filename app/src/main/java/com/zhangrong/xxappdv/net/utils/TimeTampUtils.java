package com.zhangrong.xxappdv.net.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/11/2.
 * <p/>
 * 获取时间类
 */

public class TimeTampUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static long timeTamp() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        String time = "1984-12-28 00:00:00";

        long stamp = 0;
        try {
            Date date1 = dateFormat.parse(time);
            Date date2 = new Date();
            stamp = date2.getTime() - date1.getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }

        return stamp;

    }

    public static String timeUtil(String t) {
        String time = "";
        if (!TextUtils.isEmpty(t) && t.length() > 19) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(t.substring(0, 19).replace("T", " "));
                long ti = (System.currentTimeMillis() - date.getTime()) / 1000;
                long temp = 0;
                if (ti < 60) {
                    time = "刚刚";
                } else if ((temp = ti / 60) < 60) {
                    time = String.format("%d分钟前", temp);
                } else if ((temp = temp / 60) < 24) {
                    time = String.format("%d小时前", temp);
                } else if ((temp = temp / 24) < 30) {
                    time = String.format("%d天前", temp);
                } else if ((temp = temp / 30) < 12) {
                    time = String.format("%d个月前", temp);
                } else {
                    temp = temp / 12;
                    time = String.format("%d年前", temp);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return time;
    }

    public static String getTime() {
        Date date = new Date();
        // 取得系统日期:
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String Time = simple.format(date);


        return Time;
    }

    public static String getHmTime() {
        Date date = new Date();
        // 取得系统日期:
        SimpleDateFormat simple = new SimpleDateFormat("HHmm");
        String Time = simple.format(date);


        return Time;
    }

    public static String getHmsTime() {
        Date date = new Date();
        // 取得系统日期:
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        String Time = simple.format(date);


        return Time;
    }


}
