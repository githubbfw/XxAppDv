package com.zhangrong.xxappdv.net.utils;

/**
 * Created by zhang on 2016/8/23.
 * <p/>
 * 数据转换工具
 */
public class DataConvertUtils {

    /**
     * int类型为空的数据转换
     *
     * @param jsonValue
     * @param defaultValue 默认值
     * @return
     */
    public final static int IntIsNullConvert(Object jsonValue, int defaultValue) {
        if (jsonValue == null || "".equals(jsonValue.toString().trim())) {

            return defaultValue;
        }
        try {
            return Integer.valueOf(jsonValue.toString());
        } catch (Exception e) {
            try {
                return Double.valueOf(jsonValue.toString()).intValue();
            } catch (Exception e1) {
                return defaultValue;

            }
        }

    }

    /**
     * long类型为空的数据转换
     *
     * @param jsonValue
     * @param defaultValue 默认值
     * @return
     */
    public final static long LongIsNullConvert(Object jsonValue, long defaultValue) {
        if (jsonValue == null || "".equals(jsonValue.toString().trim())) {

            return defaultValue;
        }
        try {
            return Long.parseLong(jsonValue.toString());
        } catch (Exception e) {
            try {
                return Long.valueOf(jsonValue.toString()).longValue();
            } catch (Exception e1) {
                return defaultValue;

            }
        }

    }

    /**
     * double类型为空的数据转换
     *
     * @param jsonValue
     * @param defaultValue 默认值
     * @return
     */
    public final static double DoubleIsNullConvert(Object jsonValue, double defaultValue) {
        if (jsonValue == null || "".equals(jsonValue.toString().trim())) {

            return defaultValue;
        }
        try {
            return Double.parseDouble(jsonValue.toString());
        } catch (Exception e) {
            try {
                return Double.valueOf(jsonValue.toString()).doubleValue();
            } catch (Exception e1) {
                return defaultValue;

            }
        }

    }

    /**
     * String类型为空的数据转换
     *
     * @param jsonValue
     * @param defaultValue
     * @return
     */
    public final static String StringIsNullConvert(Object jsonValue, String defaultValue) {
        if (jsonValue == null || "".equals(jsonValue.toString().trim())) {

            return defaultValue;
        }

        return jsonValue.toString();

    }


}
