package com.zhangrong.xxappdv.main.ui.fragment;

import android.view.View;

import com.zhangrong.xxappdv.R;
import com.zhangrong.xxappdv.base.AppBaseFragment;


/**
 * Created by zhang on 2016/11/21.
 */
public class TabTwoFragment extends AppBaseFragment {
    @Override
    protected int setContentViewID() {
        return R.layout.frg_tabtwo_layout;
    }

    @Override
    protected void initViews(View view) {
        //常规初始化
        setTitleBar(false, getActivity().getResources().getString(R.string.tab_two), false, view);


    }

    @Override
    protected void loadData() {

    }
}
