package com.zhangrong.xxappdv.net.netother;

import java.util.List;

/**
 * Created by Cosecant on 2015/8/23. 针对数据集的数据解析模型
 */
public class PageResult<T> {

    /**
     * 数据总条数
     **/
    public int total;

    /**
     * 数据结果集
     **/
    public List<T> data;

}
