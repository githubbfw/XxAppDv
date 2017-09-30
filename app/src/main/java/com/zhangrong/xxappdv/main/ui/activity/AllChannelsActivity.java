package com.zhangrong.xxappdv.main.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.zhangrong.xxappdv.R;
import com.zhangrong.xxappdv.base.AppBarRvBaseActivity;

/**
 * Created by zhangrong on 2017/6/15.
 */

public class AllChannelsActivity extends AppBarRvBaseActivity {
    private View headView;//@1.头部view
    private View twoView;//@2.

    @Override
    public int setContentViewID() {
        return R.layout.act_allchannels_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(true, "全部频道", false);

    }

    /**
     * 注意：用layoutInflater初始化要添加的内容布局
     * adapter.addHeaderView(view);  加入布局
     */
    @Override
    protected void initAddLayout() {
        //@1.头部
        headView = layoutInflater.inflate(R.layout.index_head_layout, null);
        initHeadViews(headView);
        adapter.addHeaderView(headView);

        //@2.第二个布局
        twoView = layoutInflater.inflate(R.layout.item_allchannels_head, null);
        initTwoViews(twoView);
        adapter.addHeaderView(twoView);

        //@1.头部
        headView = layoutInflater.inflate(R.layout.index_head_layout, null);
        initHeadViews(headView);
        adapter.addHeaderView(headView);
        //@2.第二个布局
        twoView = layoutInflater.inflate(R.layout.item_allchannels_head, null);
        initTwoViews(twoView);
        adapter.addHeaderView(twoView);
        //@1.头部
        headView = layoutInflater.inflate(R.layout.index_head_layout, null);
        initHeadViews(headView);
        adapter.addHeaderView(headView);
        //@2.第二个布局
        twoView = layoutInflater.inflate(R.layout.item_allchannels_head, null);
        initTwoViews(twoView);
        adapter.addHeaderView(twoView);
        //@1.头部
        headView = layoutInflater.inflate(R.layout.index_head_layout, null);
        initHeadViews(headView);
        adapter.addHeaderView(headView);

        //@2.第二个布局
        twoView = layoutInflater.inflate(R.layout.item_allchannels_head, null);
        initTwoViews(twoView);
        adapter.addHeaderView(twoView); //@1.头部
        headView = layoutInflater.inflate(R.layout.index_head_layout, null);
        initHeadViews(headView);
        adapter.addHeaderView(headView);

        //@2.第二个布局
        twoView = layoutInflater.inflate(R.layout.item_allchannels_head, null);
        initTwoViews(twoView);
        adapter.addHeaderView(twoView); //@1.头部
        headView = layoutInflater.inflate(R.layout.index_head_layout, null);
        initHeadViews(headView);
        adapter.addHeaderView(headView);

        //@2.第二个布局
        twoView = layoutInflater.inflate(R.layout.item_allchannels_head, null);
        initTwoViews(twoView);
        adapter.addHeaderView(twoView); //@1.头部
        headView = layoutInflater.inflate(R.layout.index_head_layout, null);
        initHeadViews(headView);
        adapter.addHeaderView(headView);

        //@2.第二个布局
        twoView = layoutInflater.inflate(R.layout.item_allchannels_head, null);
        initTwoViews(twoView);
        adapter.addHeaderView(twoView); //@1.头部
        headView = layoutInflater.inflate(R.layout.index_head_layout, null);
        initHeadViews(headView);
        adapter.addHeaderView(headView);

        //@2.第二个布局
        twoView = layoutInflater.inflate(R.layout.item_allchannels_head, null);
        initTwoViews(twoView);
        adapter.addHeaderView(twoView); //@1.头部
        headView = layoutInflater.inflate(R.layout.index_head_layout, null);
        initHeadViews(headView);
        adapter.addHeaderView(headView);

        //@2.第二个布局
        twoView = layoutInflater.inflate(R.layout.item_allchannels_head, null);
        initTwoViews(twoView);
        adapter.addHeaderView(twoView);
    }


    //@1.头部
    private void initHeadViews(View headView) {

    }

    //@2.第二个布局
    private void initTwoViews(View twoView) {

    }

}
