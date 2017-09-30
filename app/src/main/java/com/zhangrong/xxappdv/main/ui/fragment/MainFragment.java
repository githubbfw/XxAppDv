package com.zhangrong.xxappdv.main.ui.fragment;

import android.view.View;

import com.zhangrong.xxappdv.R;
import com.zhangrong.xxappdv.base.AppBaseFragment;


/**
 * Created by zhang on 2016/11/21.
 */
public class MainFragment extends AppBaseFragment {
    @Override
    protected int setContentViewID() {
        return R.layout.frg_mainfragment_layout;
    }

    @Override
    protected void initViews(View view) {
        //常规初始化
        setTitleBar(false, getActivity().getResources().getString(R.string.tab_one), false, view);


    }

    @Override
    protected void loadData() {

    }

    // 回调接口，用于获取tab的选中状态
    private myOnTabSelected mTabListener;


    public interface myOnTabSelected {
        void myonTabSelected(int index, boolean showPopup);
    }

    public void setOnTabSelected(myOnTabSelected listener) {
        this.mTabListener = listener;
    }
}
