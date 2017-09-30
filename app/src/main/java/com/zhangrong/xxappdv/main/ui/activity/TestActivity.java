package com.zhangrong.xxappdv.main.ui.activity;

import android.os.Bundle;

import com.zhangrong.xxappdv.R;
import com.zhangrong.xxappdv.base.AppBaseActivity;

/**
 * Created by zhangrong on 2017/6/15.
 */

public class TestActivity extends AppBaseActivity {
    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.act_test_layout);
        setTitleBar(true, "没有封装的头部", false);
    }

    @Override
    protected void loadData() {

    }
}
