package com.zhangrong.xxappdv.net.netother;

import java.io.Serializable;

/**
 * Created by zhang on 2016/6/2.
 * 接口解析第一层包
 */
public class HttpResult<T> implements Serializable {


    /**ֵ状态值：ture/false **/
    public boolean Code;


    /** 错误时的消息类型 **/
    public String Message;


    /** 返回json字符串结果 **/
    public T ResultJson;


    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + Code +
                ", Message='" + Message + '\'' +
                ", ResultJson='" + ResultJson + '\'' +
                '}';
    }


}
