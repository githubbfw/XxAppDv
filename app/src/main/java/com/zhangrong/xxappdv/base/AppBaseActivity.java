package com.zhangrong.xxappdv.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangrong.xxappdv.R;
import com.zhangrong.xxappdv.base.baseother.BaseActivity;


/**
 * Created by zhang on 2016/11/18.
 * <p>
 * app的基类
 */
public abstract class AppBaseActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews(savedInstanceState);
        loadData();

    }


    /***
     * 设置head tool_bar_back返回事件
     *
     * @param isVISIBLE 是否显示
     *                  显示并启用 true  false 隐藏返回
     */
    public void setHeadBackBtnClick(boolean isVISIBLE) {
        ImageView imageView = (ImageView) findViewById(R.id.tool_bar_back);

        if (isVISIBLE) {
            imageView.setVisibility(View.VISIBLE);
        }
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    /***
     * 设置head tool_bar_title的标题
     *
     * @param TitleContent 内容
     */
    public void setHeadTitleText(String TitleContent) {
        if (null == TitleContent) {
            return;
        }
        TextView tv = (TextView) findViewById(R.id.tool_bar_title);
        if (tv != null) {
            tv.setText(TitleContent);
        }

    }


    /***
     * 设置tool_bar_lefttext的标题
     *
     * @param TitleContent 内容
     */
    public void setHeadRightTitleText(Drawable leftDrawable, String TitleContent) {
        if (null == TitleContent) {
            return;
        }
        TextView tv = (TextView) findViewById(R.id.tool_bar_righttext);
        tv.setCompoundDrawables(leftDrawable, null, null, null);
        if (tv != null) {
            tv.setText(TitleContent);
        }
    }

    public void setTitleBar(boolean leftShow, String TitleContent, boolean rightShow) {
        ImageView imageView = (ImageView) findViewById(R.id.tool_bar_back);
        TextView title = (TextView) findViewById(R.id.tool_bar_title);
        TextView rightText = (TextView) findViewById(R.id.tool_bar_righttext);
        //返回处理
        if (leftShow) {
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    finish();
                }
            });
        }
        //标题
        if (TextUtils.isEmpty(TitleContent)) {
            return;
        }
        if (title != null) {
            title.setText(TitleContent);
        }
        //右边文字处理
        if (rightShow) {
            rightText.setVisibility(View.VISIBLE);
        } else {
            rightText.setVisibility(View.GONE);
        }

    }

    /**
     * 每个页面需要实现的抽象方法：一般在initviews中加载布局、进行初始化，而在loadData中
     * 获取intent传递的数据和请求接口
     */
    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void loadData();
}
