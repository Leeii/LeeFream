package com.leeiidesu.lib.widget.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.leeiidesu.lib.common.loader.ImageLoader;
import com.leeiidesu.lib.widget.R;
import com.leeiidesu.libcore.android.UIUtil;

import java.util.ArrayList;

/**
 * _ BannerView _ Created by dgg on 2017/7/21.
 */

public class BannerView extends FrameLayout implements ViewPager.OnPageChangeListener {
    private boolean unLimitedLoop;
    private boolean autoSwitch;
    private long autoSwitchTimeMillis;
    private ViewPager mViewPager;
    private BannerAdapter mBannerAdapter;
    private Indicator mIndicatorView;
    private long touchTime;

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.BannerView);
        if (mIndicatorView != null && mIndicatorView instanceof IndicatorView) {
            IndicatorView view = (IndicatorView) mIndicatorView;
            float indicatorWidth = a.getDimension(R.styleable.BannerView_indicatorWidth, UIUtil.dipToPx(context, 8));
            float indicatorSpaceWidth = a.getDimension(R.styleable.BannerView_indicatorSpaceWidth, UIUtil.dipToPx(context, 4));

            view.setIndicatorWidth((int) indicatorWidth);
            view.setSpaceWidth((int) indicatorSpaceWidth);

            int selectedColor = a.getColor(R.styleable.BannerView_indicatorSelectedColor, Color.WHITE);
            int indicatorNormalColor = a.getColor(R.styleable.BannerView_indicatorNormalColor, Color.GRAY);

            view.setSelectedColor(selectedColor);
            view.setNormalColor(indicatorNormalColor);

            boolean indicatorNormalHollow = a.getBoolean(R.styleable.BannerView_indicatorNormalHollow, false);

            view.setHollowNormalIndicator(indicatorNormalHollow);
            if (indicatorNormalHollow) {
                float hollowIndicatorStrokeWidth = a.getDimension(R.styleable.BannerView_hollowIndicatorStrokeWidth, 0);

                view.setIndicatorStrokeWidth((int) hollowIndicatorStrokeWidth);


            }
        }

        boolean autoSwitch = a.getBoolean(R.styleable.BannerView_autoSwitch, true);

        this.autoSwitch = autoSwitch;

        if (autoSwitch) {
            this.autoSwitchTimeMillis = a.getInteger(R.styleable.BannerView_autoSwitchTimeMillis, 2000);
        }
        this.unLimitedLoop = a.getBoolean(R.styleable.BannerView_unLimitedLoop, false);

        a.recycle();


        mBannerAdapter = new BannerAdapter(unLimitedLoop);
        mViewPager.setAdapter(mBannerAdapter);
        mViewPager.addOnPageChangeListener(this);


        mViewPager.setCurrentItem(Integer.MAX_VALUE / 2, false);
    }

    private Handler mHandler;

    public void setIndicatorView(Indicator mIndicatorView) {
        this.mIndicatorView = mIndicatorView;
        if (mIndicatorView instanceof View) {
            View indicatorView = (View) mIndicatorView;

            LayoutParams params2 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            params2.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;

            params2.bottomMargin = UIUtil.dipToPx(indicatorView.getContext(), 16);

            indicatorView.setLayoutParams(params2);

            addView(indicatorView);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();


        if (autoSwitch) {
            mHandler = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    if (System.currentTimeMillis() - touchTime > autoSwitchTimeMillis) {
                        int currentItem = mViewPager.getCurrentItem();

                        if (unLimitedLoop) {
                            mViewPager.setCurrentItem(currentItem + 1, true);
                        } else {
                            boolean b = currentItem + 1 == mBannerAdapter.getCount();
                            mViewPager.setCurrentItem(b ? 0 : currentItem + 1, !b);
                        }

                        if (mHandler != null)
                            mHandler.sendEmptyMessageDelayed(0, autoSwitchTimeMillis);
                    }
                }
            };
            mHandler.sendEmptyMessageDelayed(0, autoSwitchTimeMillis);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mHandler != null) {
            mHandler = null;
        }
    }

    private void init(Context context) {
        mViewPager = new ViewPager(context);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        mViewPager.setLayoutParams(params);

        addView(mViewPager);
        IndicatorView mIndicatorView = new IndicatorView(context);
        LayoutParams params2 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        params2.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;

        params2.bottomMargin = UIUtil.dipToPx(context, 16);

        mIndicatorView.setLayoutParams(params2);

        addView(mIndicatorView);

        this.mIndicatorView = mIndicatorView;


    }

    public void setBannerUrl(ArrayList<Uri> data) {
        if (data == null) return;

        mBannerAdapter.setData(data);
        if (unLimitedLoop && data.size() != 0) {
            int i = Integer.MAX_VALUE / 2 / data.size();
            mViewPager.setCurrentItem(i * data.size(), false);
        }
        mIndicatorView.setSize(data.size());
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.touchTime = System.currentTimeMillis();
                break;
            case MotionEvent.ACTION_UP:
                if (autoSwitch && mHandler != null) {
                    mHandler.sendEmptyMessageDelayed(0, autoSwitchTimeMillis);
                }
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mIndicatorView.setSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    private static class BannerAdapter extends PagerAdapter {
        private ArrayList<Uri> data;
        private boolean unLimitedLoop;

        private int size;

        BannerAdapter(boolean unLimitedLoop) {
            this.unLimitedLoop = unLimitedLoop;
        }


        public void setData(ArrayList<Uri> data) {
            this.data = data;

            size = data == null || data.size() == 0 ? 0 : unLimitedLoop ? Integer.MAX_VALUE : data.size();

            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return size;
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

            ImageLoader.load(data.get(position % data.size()), imageView);

            container.addView(inflate);
            return inflate;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
