package com.zhangrong.xxappdv.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.zhangrong.xxappdv.R;
import com.zhangrong.xxappdv.base.baseother.BaseFragment;

import java.util.ArrayList;

/**
 * Created by zhang on 2016/11/21.
 */
public abstract class AppBaseFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(setContentViewID(), container, false);
        initViews(view);
        loadData();

        return view;
    }


    /***
     * 设置head tool_bar_back返回事件
     *
     * @param isVISIBLE 是否显示
     * @param view      加载的fragment布局对象
     *                  显示并启用 true  false 隐藏返回
     */
    public void setHeadBackBtnClick(boolean isVISIBLE, View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.tool_bar_back);

        if (isVISIBLE) {
            imageView.setVisibility(View.VISIBLE);
        }
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    getActivity().finish();
                }
            });
        }
    }

    /***
     * 设置head tool_bar_title的标题
     *
     * @param view         加载的fragment布局对象
     * @param TitleContent 内容
     */
    public void setHeadTitleText(String TitleContent, View view) {
        if (null == TitleContent) {
            return;
        }
        TextView tv = (TextView) view.findViewById(R.id.tool_bar_title);
        if (tv != null) {
            tv.setText(TitleContent);
        }

    }


    /***
     * 设置tool_bar_lefttext的标题
     *
     * @param TitleContent 内容
     */
    public void setHeadRightTitleText(Drawable leftDrawable, String TitleContent, View view) {
        if (null == TitleContent) {
            return;
        }
        TextView tv = (TextView) view.findViewById(R.id.tool_bar_righttext);
        tv.setCompoundDrawables(leftDrawable, null, null, null);
        if (tv != null) {
            tv.setText(TitleContent);
        }
    }

    /***
     * 设置tool_bar_lefttext的标题
     *
     * @param TitleContent 内容
     */
    public void setHeadRightTitleText(String TitleContent, View view) {
        if (null == TitleContent) {
            return;
        }
        TextView tv = (TextView) view.findViewById(R.id.tool_bar_righttext);
        if (tv != null) {
            tv.setText(TitleContent);
        }
    }

    public void setTitleBar(boolean leftShow, String TitleContent, boolean rightShow, View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.tool_bar_back);
        TextView title = (TextView) view.findViewById(R.id.tool_bar_title);
        TextView rightText = (TextView) view.findViewById(R.id.tool_bar_righttext);
        //返回处理
        if (leftShow) {
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    getActivity().finish();
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

    //数据转换
    public <T> ArrayList<T> fromJsonList(String json, Class<T> cls) {
        ArrayList<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            mList.add(new Gson().fromJson(elem, cls));
        }
        return mList;
    }


    /**
     * 每个fragment需要实现的抽象方法：一般在setContentViewID中加载布局、initViews中
     * 进行初始化，而在loadData中获取intent传递的数据和请求接口
     */
    protected abstract int setContentViewID();

    protected abstract void initViews(View view);

    protected abstract void loadData();

}
