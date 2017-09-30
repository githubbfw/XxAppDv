package com.zhangrong.xxappdv.main.ui.fragment;

import android.view.View;

import com.zhangrong.xxappdv.R;
import com.zhangrong.xxappdv.base.AppBarRvBaseFragment;
import com.zhangrong.xxappdv.base.AppBaseFragment;

/**
 * Created by zhang on 2016/11/21.
 */
public class TabForeFragment extends AppBarRvBaseFragment {

    @Override
    protected void initView(View view) {
        //常规初始化
        setTitleBar(false, getActivity().getResources().getString(R.string.tab_fore), false, view);
    }

    /**
     * 注意：用layoutInflater初始化要添加的内容布局
     * adapter.addHeaderView(view);  加入布局
     */
    @Override
    protected void initAddLayout() {

    }

    @Override
    protected void loadData() {

    }

}
