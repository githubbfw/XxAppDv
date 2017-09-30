package com.zhangrong.xxappdv.main.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zhangrong.xxappdv.R;
import com.zhangrong.xxappdv.main.MainActivity;
import com.zhangrong.xxappdv.utlis.ImgUitl;

import java.util.ArrayList;

/**
 * Created by zhang on 2016/5/25.
 * <p>
 * 引导页面
 */
public class GuideActivity extends Activity implements View.OnClickListener,
        ViewPager.OnPageChangeListener {

    private TextView tvGuideGoBtn;//立即体验按钮
    private ViewPager vpGuidePager;//引导
    private RadioGroup rbGuidePoint;//引导小点

    private int[] images = new int[]{
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};//引导图片

    private ArrayList<View> views;//引导图片集合
    private ImageView imageView;
    private GuideBasePageAdapter pageAdapter;//Adapter




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面为全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.act_guide_layout);
        initView();//初始化视图

    }

    /**
     * 初始化视图
     */
    private void initView() {

        tvGuideGoBtn = (TextView) findViewById(R.id.guide_go);
        tvGuideGoBtn.setOnClickListener(this);
        vpGuidePager = (ViewPager) findViewById(R.id.guide_pager);
        rbGuidePoint = (RadioGroup) findViewById(R.id.guide_group);

        views = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            //添加图片到集合中
            imageView = new ImageView(this);
            Bitmap bitmap = ImgUitl.readBitMap(this, images[i]);
            imageView.setImageBitmap(bitmap);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            views.add(imageView);

        }

        pageAdapter = new GuideBasePageAdapter();
        vpGuidePager.setAdapter(pageAdapter);//绑定
        vpGuidePager.setOnPageChangeListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.guide_go:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

                break;

        }
    }

    //当当前页面被滑动时调用
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        RadioButton button = (RadioButton) rbGuidePoint.getChildAt(position);
        button.setChecked(true);

        if (position == views.size() - 1) {

            rbGuidePoint.setVisibility(View.GONE);
            tvGuideGoBtn.setVisibility(View.VISIBLE);

        } else {

//            rbGuidePoint.setVisibility(View.VISIBLE);
            rbGuidePoint.setVisibility(View.GONE);
            tvGuideGoBtn.setVisibility(View.GONE);

        }
    }

    //当新的页面被选中时调用
    @Override
    public void onPageSelected(int position) {

    }

    //当滑动状态改变时调用
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //引导页的Viewpage的Adapter
    public class GuideBasePageAdapter extends PagerAdapter {

        //获得当前界面数
        @Override
        public int getCount() {

            if (views != null) {

                return views.size();
            } else {
                return 0;
            }
        }

        //销毁arg1位置的界面
        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(views.get(position % views.size()));
        }

        //初始化arg1位置的界面
        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager) container).addView(views.get(position));
            return views.get(position);
        }

        //判断是否由对象生成界面
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destoryBitmaps();

    }

    /**
     * 销毁ImageView资源，回收内存
     */
    private void destoryBitmaps() {
        for (int i = 0; i < views.size(); i++) {
            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
            Bitmap bmp = drawable.getBitmap();
            if (null != bmp && !bmp.isRecycled()) {
                imageView.setBackgroundResource(0);
                drawable.setCallback(null);
                bmp.recycle();
            }
        }

        views.clear();
        views = null;
        imageView = null;
        pageAdapter = null;
        vpGuidePager = null;
        images = null;
    }
    //字体不变
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );

        return res;

    }
}
