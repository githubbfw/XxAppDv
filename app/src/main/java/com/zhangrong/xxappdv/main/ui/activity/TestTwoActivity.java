package com.zhangrong.xxappdv.main.ui.activity;

import android.os.Bundle;

import com.zhangrong.xxappdv.R;
import com.zhangrong.xxappdv.base.AppBarBaseActivity;

/**
 * Created by zhangrong on 2017/6/15.
 */

public class TestTwoActivity extends AppBarBaseActivity {
    @Override
    protected int getContentView() {
        return R.layout.act_testtwo_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(true, "有头部的页面", false);
    }

    @Override
    protected void loadData() {

    }
}
