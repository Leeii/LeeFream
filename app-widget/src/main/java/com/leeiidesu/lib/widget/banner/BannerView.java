package com.leeiidesu.lib.widget.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.leeiidesu.lib.common.imageloader.ImageLoader;
import com.leeiidesu.lib.widget.R;
import com.leeiidesu.libcore.android.Logger;

/**
 * _ BannerView _ Created by dgg on 2017/7/21.
 */

public class BannerView extends FrameLayout implements ViewPager.OnPageChangeListener, CallBack {
    //是否需要指示器
    private final boolean needIndicator;
    //是否无限循环
    private boolean unLimitedLoop;

    //是否自动切换
    private boolean autoSwitch;
    //自动切换时间间隔
    private long autoSwitchTimeMillis;

    //ViewPager
    private ViewPager mViewPager;
    private Indicator mIndicatorView;

    private BannerAdapter mBannerAdapter;

    //handler用于定时切换
    private Handler mHandler;


    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.BannerView);
        //自动切换
        this.autoSwitch = a.getBoolean(R.styleable.BannerView_autoSwitch, true);

        if (autoSwitch) {
            //自动切换间隔
            this.autoSwitchTimeMillis = a.getInteger(R.styleable.BannerView_autoSwitchTimeMillis, 2000);
        }
        //是否无限循环
        this.unLimitedLoop = a.getBoolean(R.styleable.BannerView_unLimitedLoop, false);
        //是否需要指示器
        this.needIndicator = a.getBoolean(R.styleable.BannerView_needIndicator, true);

        a.recycle();


        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(this);

        mViewPager.setCurrentItem(Integer.MAX_VALUE / 2, false);
    }

    private void init(Context context) {
        mViewPager = new ViewPager(context);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        mViewPager.setLayoutParams(params);

        addView(mViewPager, 0);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (needIndicator) {
            int childCount = getChildCount();
            w:
            if (childCount > 0) {
                for (int i = 0; i < childCount; i++) {
                    View child = getChildAt(i);
                    if (child instanceof Indicator) {
                        setIndicatorView((Indicator) child);
                        break w;
                    }
                }
                addDefaultIndicator();
            } else {
                addDefaultIndicator();
            }
            Logger.e(this, "child = " + childCount);
        }
        Logger.e(this, "getChildCount = " + getChildCount());

    }

    private void addDefaultIndicator() {
        IndicatorView defaultIndicatorView = new IndicatorView(getContext());
        LayoutParams params2 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        params2.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;

        float dp16 = getResources().getDisplayMetrics().density * 16;
        params2.bottomMargin = (int) dp16;

        defaultIndicatorView.setLayoutParams(params2);

        addView(defaultIndicatorView);
        setIndicatorView(defaultIndicatorView);
    }

    void setIndicatorView(Indicator mIndicatorView) {
        this.mIndicatorView = mIndicatorView;
    }

    @Override
    protected void onAttachedToWindow() {
        Logger.e(this, "onAttachedToWindow");
        super.onAttachedToWindow();
        startAutoSwitch();
    }

    private void startAutoSwitch() {
        if (autoSwitch) {
            if (mHandler == null) {
                mHandler = new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        switchPage();
                    }
                };
            }
            mHandler.sendEmptyMessageDelayed(0, autoSwitchTimeMillis);
        }
    }

    private void stopAutoSwitch() {
        mHandler.removeMessages(0);
    }


    private void switchPage() {
        int currentItem = mViewPager.getCurrentItem();

        if (unLimitedLoop) {
            mViewPager.setCurrentItem(currentItem + 1, true);
        } else {
            boolean b = currentItem + 1 == mPagerAdapter.getCount();
            mViewPager.setCurrentItem(b ? 0 : currentItem + 1, !b);
        }

        mHandler.sendEmptyMessageDelayed(0, autoSwitchTimeMillis);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Logger.e(this, "onDetachedFromWindow");
        stopAutoSwitch();
        mHandler = null;
    }


    public void setBannerAdapter(BannerAdapter mBannerAdapter) {
        this.mBannerAdapter = mBannerAdapter;

        if (mBannerAdapter != null) {
            mBannerAdapter.setNotifyCallBack(this);

            onNotify();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //nothing
    }

    @Override
    public void onPageSelected(int position) {
        if (mIndicatorView != null)
            mIndicatorView.setSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                stopAutoSwitch();
                break;

            case ViewPager.SCROLL_STATE_IDLE:
                if (!mHandler.hasMessages(0))
                    startAutoSwitch();
                break;
        }
    }

    /**
     * ViewPager的Adapter
     */
    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return unLimitedLoop ? Integer.MAX_VALUE : mBannerAdapter.getCount();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View inflate = LayoutInflater.from(container.getContext())
                    .inflate(R.layout.layout_banner_item, container, false);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.image);
            TextView title = (TextView) inflate.findViewById(R.id.title);
            int mPosition = position % mBannerAdapter.getCount();

            ImageLoader.getInstance().display(mBannerAdapter.getBannerUrl(mPosition), imageView);

            if (mBannerAdapter.getBannerTitle(mPosition) == null) {
                title.setVisibility(GONE);
            } else {
                title.setVisibility(VISIBLE);
                title.setText(mBannerAdapter.getBannerTitle(mPosition));
            }

            container.addView(inflate);
            return inflate;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };

    @Override
    public void onNotify() {
        mPagerAdapter.notifyDataSetChanged();
        mIndicatorView.setSize(mBannerAdapter.getCount());
        mIndicatorView.setSelected(0);
    }


    public static abstract class BannerAdapter {
        private CallBack callBack;

        public abstract String getBannerUrl(int position);

        public abstract CharSequence getBannerTitle(int position);

        public abstract int getCount();

        public void notifyDataSetChanged() {
            callBack.onNotify();
        }

        private void setNotifyCallBack(CallBack callBack) {
            this.callBack = callBack;
        }
    }
}
