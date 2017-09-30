package com.zhangrong.xxappdv.base.baseview;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.zhangrong.xxappdv.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2016/11/18.
 * <p>
 * 自定义tab底部菜单
 */
public class TabLinearLayout extends LinearLayout {
    //底部tab动画
    private AnimatorSet animator1;
    private AnimatorSet animator2;
    private AnimatorSet animator3;
    private AnimatorSet animator4;
    //底部菜单的图片数组
    private int[] mDrawableIds = new int[]{
            R.drawable.tab_home, R.drawable.tab_two, R.drawable.tab_three, R.drawable.tab_fore};
    //底部菜单的动画数组
    private AnimatorSet[] mAnimatorSetList = new AnimatorSet[]{
            animator1, animator2, animator3, animator4
    };
    // 底部菜单的文字数组
    private CharSequence[] mLabels = {
            getResources().getString(R.string.tab_one),
            getResources().getString(R.string.tab_two),
            getResources().getString(R.string.tab_three),
            getResources().getString(R.string.tab_fore)};

    // 存放底部菜单图片CheckedTextView的集合
    private List<CheckedTextView> mCheckedList = new ArrayList<CheckedTextView>();
    // 存放底部菜单文字TextView的集合
    private List<TextView> mCheckedTextList = new ArrayList<TextView>();

    // 存放底部菜单每一项的View的集合，如：首页 、个人中心两个底部
    private List<View> mViewList = new ArrayList<View>();


    // 存放图片上面小红点的集合
    private List<TextView> mRedIndicate = new ArrayList<TextView>();

    public TabLinearLayout(Context context) {
        super(context);
        init(context); // 初始化控件

    }


    public TabLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context); // 初始化控件

    }

    public TabLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context); // 初始化控件

    }

    private void init(Context context) {
        this.setOrientation(LinearLayout.HORIZONTAL);//设置lin为水平方向
        LayoutInflater inflater = LayoutInflater.from(context);//inflater加载布局
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.weight = 1.0f;//居中权重
        params.gravity = Gravity.CENTER_VERTICAL;
        int size = mLabels.length;//将文字数据的大小赋值给size
        //循环添加每个tab
        for (int i = 0; i < size; i++) {
            final int index = i;
            final View view = inflater.inflate(R.layout.tablin_item, null);
            final CheckedTextView itemName = (CheckedTextView) view.findViewById(R.id.item_name_iv);
            //初始化动画
            if (index == 0) {//tab一动画
                mAnimatorSetList[0] = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.tab_anim);
            } else if (index == 1) {//tab二动画
                mAnimatorSetList[1] = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.tab_anim);
            } else if (index == 2) {//tab三动画
                mAnimatorSetList[2] = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.tab_anim);
            } else if (index == 3) {//tab四动画
                mAnimatorSetList[3] = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.tab_anim);
            } else {//其他tab动画
                mAnimatorSetList[i] = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.tab_anim);
            }
            //设置CheckedTextView的图片
            itemName.setCompoundDrawablesRelativeWithIntrinsicBounds(null,
                    context.getResources().getDrawable(mDrawableIds[i]), null, null);
            //设置textview的文字
            final TextView itemText = (TextView) view.findViewById(R.id.item_text);
            itemText.setText(mLabels[i]);
            //图片头上小红点
            final TextView indicateTv = (TextView) view.findViewById(R.id.item_indicate);
            this.addView(view, params);
            itemName.setTag(index);//为CheckedTextView设置tag，为的是后面的区分与切换
            //设置tab动画
            if (itemName != null) {
                mAnimatorSetList[i].setTarget(itemName);
            }
            //存对应的view数据
            mCheckedList.add(itemName);//CheckedTextView
            mCheckedTextList.add(itemText);//菜单文字
            mRedIndicate.add(indicateTv);//小红点
            mViewList.add(view);//一个底部 （如首页)
            //给每个tab设置点击事件
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    setTabLinearViewCheack(index);
                    if (null != mTabListener) {
                        // tab切换监听
                        mTabListener.onTabSelected(index, true);
                    }
                }
            });
            //初始化底部菜单tab的选中，默认让第一个选中
            if (i == 0) {
                //选中与字体颜色
                itemName.setChecked(true);
                itemText.setTextColor(ContextCompat.getColor(getContext(), R.color.tab_checked_color));

            } else {
                itemName.setChecked(false);
                itemText.setTextColor(ContextCompat.getColor(getContext(), R.color.tab_no_checked_color));
            }


        }
    }


    /**
     * tabLinearLayout提供的设置tab选中的方法
     *
     * @param index
     */
    public void setTabLinearViewCheack(int index) {
        int size = mCheckedList.size();
        for (int i = 0; i < size; i++) {
            //遍历集合，设置选中
            CheckedTextView itemButton = mCheckedList.get(i);
            TextView itemText = mCheckedTextList.get(i);
            if ((Integer) itemButton.getTag() == index) {
                itemButton.setChecked(true);
                itemText.setTextColor(ContextCompat.getColor(getContext(), R.color.tab_checked_color));
                mAnimatorSetList[i].start();
                Log.i("tab", mLabels[index] + "tab被选中!!!");

            } else {
                itemButton.setChecked(false);
                itemText.setTextColor(ContextCompat.getColor(getContext(), R.color.tab_no_checked_color));

            }
        }

    }

    /**
     * tabLinearLayout提供的设置小红点显示的方法
     *
     * @param context    上下文
     * @param showNumber 设置显示数量
     * @param visible    是否显示，如果false，则都不显示
     */
    public void setRedIndicateViewDisplay(Context context, int tabPosition, String showNumber, boolean visible) {
        int size = mRedIndicate.size();
        TextView indicateTv = mRedIndicate.get(tabPosition);
        indicateTv.setVisibility(visible ? View.VISIBLE : View.GONE);
        indicateTv.setText(showNumber);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode != MeasureSpec.EXACTLY) {
            widthSpecSize = 0;
        }
        if (heightSpecMode != MeasureSpec.EXACTLY) {
            heightSpecSize = 0;
        }
        if (widthSpecMode == MeasureSpec.UNSPECIFIED || heightSpecMode == MeasureSpec.UNSPECIFIED) {
        }
        // 控件的最大高度，就是下边tab的背景最大高度
        int width;
        int height;
        width = Math.max(getMeasuredWidth(), widthSpecSize);
        height = Math.max(this.getBackground().getIntrinsicHeight(), heightSpecSize);
        setMeasuredDimension(width, height);
    }

    /**
     * tab选中的监听回调
     */

    private OnTabSelectedListener mTabListener;

    public interface OnTabSelectedListener {
        void onTabSelected(int index, boolean showPopup);
    }

    public void setOnTabSelectedListener(OnTabSelectedListener listener) {
        this.mTabListener = listener;
    }
}
