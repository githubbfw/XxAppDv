package com.zhangrong.xxappdv.base;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhangrong.xxappdv.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangrong on 2017/6/15.
 * <p>
 * 固定bar带Rv的Fragment基类
 * 注意:需要修改bar可以重写setContentViewID方法
 */

public abstract class AppBarRvBaseFragment extends AppBaseFragment {
    //recyclerview
    public RecyclerView rv_layout;//主rv
    public List<String> rvData;
    public BaseQuickAdapter adapter;
    public LinearLayoutManager manager;
    public LayoutInflater layoutInflater;

    @Override
    protected int setContentViewID() {
        return R.layout.frg_communal_app_bar_rv;
    }

    @Override
    protected void initViews(View view) {
        //主recyclerveiw
        rv_layout = (RecyclerView) view.findViewById(R.id.rv_layout);
        manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_layout.setLayoutManager(manager);
        initAdapter();
        //添加主rv内容
        initAddLayout();
        rv_layout.setAdapter(adapter);

        initView(view);
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
        layoutInflater = LayoutInflater.from(getActivity());

    }

    protected abstract void initView(View view);

    /**
     * 注意：用layoutInflater初始化要添加的内容布局
     * adapter.addHeaderView(view);  加入布局
     */
    protected abstract void initAddLayout();


}
