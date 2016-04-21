package com.dalong.gideview;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ImageView mPicture1,mPicture2,mPicture3,mPicture4;
    private ViewPager mViewpager;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private GideFragment gideFragment1;
    private GideFragment2 gideFragment2;
    private GideFragment3 gideFragment3;
    private GideFragment4 gideFragment4;
    private GideAdapter mGideAdapter;
    private ImageView[] mPictures;
    private int mCurrentIndex;
    private View mCurrentPicture;
    private View mPreviousPicture;
    private View mNextPicture;

    private float mPreviousAlpha = 0;
    private float mNextAlpha = 0;
    private float mCurrentAlpha = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initView();
        initFragments();

    }

    /**
     *  初始化fragments
     */
    private void initFragments() {
        gideFragment1=new GideFragment();
        gideFragment2=new GideFragment2();
        gideFragment3=new GideFragment3();
        gideFragment4=new GideFragment4();
        mFragments.add(gideFragment1);
        mFragments.add(gideFragment2);
        mFragments.add(gideFragment3);
        mFragments.add(gideFragment4);
        mGideAdapter=new GideAdapter(getSupportFragmentManager(),mFragments);
        mViewpager.setAdapter(mGideAdapter);
        mViewpager.setOnPageChangeListener(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mPicture1=(ImageView)findViewById(R.id.picture1);
        mPicture2=(ImageView)findViewById(R.id.picture2);
        mPicture3=(ImageView)findViewById(R.id.picture3);
        mPicture4=(ImageView)findViewById(R.id.picture4);
        mViewpager=(ViewPager)findViewById(R.id.gide_viewpager);


        /**
         * 默认设置所有图片通明度都为0
         */
        setPictureAlpha(mPicture1, 0f);
        setPictureAlpha(mPicture2, 0f);
        setPictureAlpha(mPicture3, 0f);
        setPictureAlpha(mPicture4, 0f);

        /**
         * 图片集合
         */
        mPictures = new ImageView[4];
        mPictures[0] = mPicture1;
        mPictures[1] = mPicture2;
        mPictures[2] = mPicture3;
        mPictures[3] = mPicture4;

        /**
         * 默认设置第一个图片通明度为1
         */
        changePicture(0);
    }

    /**
     * 设置所有图片的通明度显示
     * @param index  当前传入的图片位置
     */
    public void changePicture(int index) {
        //设置当前的位置是index
        mCurrentIndex = index;
        //同时设置当前的位置的通明度为1其他上一个或者下一个都有0
        mCurrentAlpha = 1;
        mPreviousAlpha = 0;
        mNextAlpha = 0;

        //根据传入的index来设置前一个图片和后一个图片是谁，如果没有前一个或者后一个设置为null
        switch (index) {
            case 0:
                mCurrentPicture = mPicture1;
                mPreviousPicture = null;
                mNextPicture = mPicture3;
                break;
            case 1:
                mCurrentPicture = mPicture2;
                mPreviousPicture = mPicture1;
                mNextPicture = mPicture3;
                break;
            case 2:
                mCurrentPicture = mPicture3;
                mPreviousPicture = mPicture2;
                mNextPicture = mPicture4;
                break;
            case 3:
                mCurrentPicture = mPicture4;
                mPreviousPicture = mPicture3;
                mNextPicture = null;
                break;

            default:
                break;
        }
        //设置当前图片，上一个，和下一个图片的通明度
        if (mPreviousPicture != null)
            setPictureAlpha(mPreviousPicture, mPreviousAlpha);
        if (mNextPicture != null)
            setPictureAlpha(mNextPicture, mNextAlpha);
        if (mCurrentPicture != null)
            setPictureAlpha(mCurrentPicture, mCurrentAlpha);
    }

    /**
     *  设置view图片通明度方法
     * @param view  需要设置通明度的view
     * @param mAlpha  通明度值
     */
    private void setPictureAlpha(View view, float mAlpha) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
            AlphaAnimation alpha = new AlphaAnimation(mAlpha,mAlpha);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            view.startAnimation(alpha);
        } else {
            view.setAlpha(mAlpha);
        }
    }

    /**
     * viewpager滑动监听
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //当postion大于图片的集合数量的时候直接return
        if (position > mPictures.length-1)
            return;
        // 设置当前position的通明度显示
        setPictureAlpha(mPictures[position], 1-positionOffset);
        //当现在的position还有下一个图片就同时设置下面图片的通明度显示
        if (position + 1 < mPictures.length)
            setPictureAlpha(mPictures[position+1], positionOffset);
        //设置当前position
        mCurrentIndex = position;
    }

    /**
     * viewpager选中监听
     * @param position
     */
    @Override
    public void onPageSelected(int position) {

        switch (position){
            case 0:
                gideFragment1.startAnim();
                break;
            case 1:
                gideFragment2.startAnim();
                break;
            case 2:
                gideFragment3.startAnim();
                break;
            case 3:
                gideFragment4.startAnim();
                break;
        }

    }

    /**
     * 在状态改变的时候调用
     * state这个参数有三种状态（0，1，2）,state==0的时默示什么都没做,state ==1的时默示正在滑动，state==2的时默示滑动完毕了。
     * @param state
     */
    @Override
    public void onPageScrollStateChanged(int state) {
        //当滚动完毕时设置picture显示
        if (state == 2) {
            changePicture(mCurrentIndex);
        }
    }
}
