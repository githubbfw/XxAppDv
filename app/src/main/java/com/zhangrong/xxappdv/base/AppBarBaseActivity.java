package com.zhangrong.xxappdv.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.zhangrong.xxappdv.R;

/**
 * Created by zhangrong on 2017/6/15.
 * <p>
 * 封装TabBar的基础类
 */

public abstract class AppBarBaseActivity extends AppBaseActivity {
    private FrameLayout viewContent;
    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.communal_app_topbar);
        viewContent = (FrameLayout) findViewById(R.id.viewContent);
        //将继承 TopBarBaseActivity的布局解析到FrameLayout里面
        LayoutInflater.from(AppBarBaseActivity.this).inflate(getContentView(), viewContent);
        initView(savedInstanceState);
    }
    protected abstract int getContentView();
    protected abstract void initView(Bundle savedInstanceState);


}
