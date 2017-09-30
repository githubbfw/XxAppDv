package com.zhangrong.xxappdv.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;


import com.zhangrong.xxappdv.R;
import com.zhangrong.xxappdv.base.AppBaseFragmentActivity;
import com.zhangrong.xxappdv.base.baseview.TabLinearLayout;
import com.zhangrong.xxappdv.main.ui.fragment.TabForeFragment;
import com.zhangrong.xxappdv.main.ui.fragment.TabTwoFragment;
import com.zhangrong.xxappdv.main.ui.fragment.MainFragment;
import com.zhangrong.xxappdv.main.ui.fragment.TabThreeFragment;

/**
 * Created by zhang on 2016/11/18.
 * <p>
 * 首页
 */
public class MainActivity extends AppBaseFragmentActivity implements TabLinearLayout
        .OnTabSelectedListener, MainFragment.myOnTabSelected {
    private LinearLayout main_Layout;//lin
    private TabLinearLayout tabLinearLayout;//底部菜单

    private FragmentManager mFragmentManager = null;
    //fragment
    private MainFragment mainFragment;//tab一
    private TabTwoFragment tabTwoFragment;//tab二
    private TabThreeFragment tabThreeFragment;//tab三
    private TabForeFragment tabForeFragment;//tab四

    private int mIndex = 0;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.act_main_layout);
        main_Layout = (LinearLayout) findViewById(R.id.main_Layout);
        tabLinearLayout = (TabLinearLayout) findViewById(R.id.main_tab);
    }


    @Override
    protected void onResume() {
        super.onResume();
        onTabSelected(mIndex, false);
        tabLinearLayout.setTabLinearViewCheack(mIndex);
        tabLinearLayout.setOnTabSelectedListener(this);

    }

    //底部tab选中监听
    @Override
    public void onTabSelected(int index, boolean showPopup) {
        mFragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = mFragmentManager.beginTransaction();
        switch (index) {
            case 0://第一个tab
                hideFragments(transaction);
                if (null == mainFragment) {
                    mainFragment = new MainFragment();
                    mainFragment.setOnTabSelected(this);
                    transaction.add(R.id.main_Layout, mainFragment);
                } else {
                    transaction.show(mainFragment);
                }

                transaction.commitAllowingStateLoss();
                mIndex = index;
                break;
            case 1://第二个tab
                hideFragments(transaction);
                if (null == tabTwoFragment) {
                    tabTwoFragment = new TabTwoFragment();
                    transaction.add(R.id.main_Layout, tabTwoFragment);
                } else {
                    transaction.show(tabTwoFragment);
                }
                transaction.commitAllowingStateLoss();
                mIndex = index;

                break;
            case 2://第三个tab
                hideFragments(transaction);
                if (null == tabThreeFragment) {
                    tabThreeFragment = new TabThreeFragment();
                    transaction.add(R.id.main_Layout, tabThreeFragment);
                } else {
                    transaction.show(tabThreeFragment);
                }
                transaction.commitAllowingStateLoss();
                mIndex = index;
                break;
            case 3://第四个tab
                hideFragments(transaction);
                if (null == tabForeFragment) {
                    tabForeFragment = new TabForeFragment();
                    transaction.add(R.id.main_Layout, tabForeFragment);
                } else {
                    transaction.show(tabForeFragment);
                }
                transaction.commitAllowingStateLoss();
                mIndex = index;
                break;
            default:
                break;


        }
        mFragmentManager.executePendingTransactions();


    }

    //隐藏fragmnet
    private void hideFragments(FragmentTransaction transaction) {

        if (null != mainFragment) {//第一个tab
            transaction.hide(mainFragment);
        }

        if (null != tabTwoFragment) {//第二个tab
            transaction.hide(tabTwoFragment);
        }

        if (null != tabThreeFragment) {//第三个tab
            transaction.hide(tabThreeFragment);
        }

        if (null != tabForeFragment) {//第四个tab
            transaction.hide(tabForeFragment);
        }

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        // 自己记录fragment的位置,防止activity被系统回收时，fragment错乱的问题
        //  super.onSaveInstanceState(outState);  此方法打开，会导致程序崩溃，不能再次加载fragment，去能切换
        outState.putInt("index", mIndex);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mIndex = savedInstanceState.getInt("index");

    }


    @Override
    public void myonTabSelected(int index, boolean showPopup) {
        onTabSelected(index, showPopup);
        tabLinearLayout.setTabLinearViewCheack(index);
    }


    @Override
    protected void loadData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mainFragment != null) {
            mainFragment.onActivityResult(requestCode, resultCode, data);//向Tab一进行结果传递
        }
        if (tabThreeFragment != null) {
            tabThreeFragment.onActivityResult(requestCode, resultCode, data);//向Tab二进行结果传递
        }
        if (tabTwoFragment != null) {
            tabTwoFragment.onActivityResult(requestCode, resultCode, data);//向Tab三进行结果传递
        }

        if (tabForeFragment != null) {
            tabForeFragment.onActivityResult(requestCode, resultCode, data);//向Tab四进行结果传递
        }


    }


}
