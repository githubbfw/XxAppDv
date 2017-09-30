package com.zhangrong.xxappdv.base;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhangrong.xxappdv.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangrong on 2017/6/15.
 * <p>
 * 封装TabBar带Rv的基础类
 * 注意:需要修改bar可以重写setContentViewID方法
 * 并且需要重新给跟布局加状态栏处理代码
 * android:background="#FFFFFF"
 * android:fitsSystemWindows="true"
 * android:orientation="vertical"
 * android:windowTranslucentNavigation="true"
 */

public abstract class AppBarRvBaseActivity extends AppBaseActivity {
    //recyclerview
    public RecyclerView rv_layout;//主rv
    public List<String> rvData;
    public BaseQuickAdapter adapter;
    public LinearLayoutManager manager;
    public LayoutInflater layoutInflater;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(setContentViewID());
        initView(savedInstanceState);
        //主recyclerveiw
        rv_layout = (RecyclerView) findViewById(R.id.rv_layout);
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_layout.setLayoutManager(manager);
        initAdapter();
        //添加主rv内容
        initAddLayout();
        rv_layout.setAdapter(adapter);
    }

    public int setContentViewID() {
        return R.layout.act_communal_app_bar_rv;
    }


    @Override
    protected void loadData() {

    }

    /**
     * 初始化主adapter
     */
    private void initAdapter() {
        rvData = new ArrayList<>();
        adapter = new BaseQuickAdapter(R.layout.communal_null_recyclerview_layout, rvData) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, Object o) {

            }

        };
        layoutInflater = LayoutInflater.from(AppBarRvBaseActivity.this);

    }

    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 注意：用layoutInflater初始化要添加的内容布局
     * adapter.addHeaderView(view);  加入布局
     */
    protected abstract void initAddLayout();


}
