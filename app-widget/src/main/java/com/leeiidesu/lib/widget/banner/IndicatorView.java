package com.leeiidesu.lib.widget.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.leeiidesu.lib.widget.R;

/**
 * _ IndicatorView _ Created by dgg on 2017/7/24.
 */

public class IndicatorView extends View implements Indicator {
    private int size;
    private int indicatorWidth;
    private int spaceWidth;

    private int selectedColor = Color.WHITE;
    private int normalColor = Color.GRAY;

    private Paint selectedPaint;
    private Paint normalPaint;

    private boolean hollowNormalIndicator;
    private int indicatorStrokeWidth;

    private int selected;


    public IndicatorView(Context context) {
        this(context, null);
    }


    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView);

        float density = context.getResources().getDisplayMetrics().density;


        //指示器宽度
        this.indicatorWidth = (int) a.getDimension(R.styleable.IndicatorView_indicatorWidth, density * 8);
        //指示器间隔
        this.spaceWidth = (int) a.getDimension(R.styleable.IndicatorView_indicatorSpaceWidth, density * 4);

        //选中颜色
        this.selectedColor = a.getColor(R.styleable.IndicatorView_indicatorSelectedColor, Color.WHITE);
        //未选中颜色
        this.normalColor = a.getColor(R.styleable.IndicatorView_indicatorNormalColor, Color.GRAY);

        //是否空心
        this.hollowNormalIndicator = a.getBoolean(R.styleable.IndicatorView_indicatorNormalHollow, false);

        if (hollowNormalIndicator) {

            //空心线条宽度
            this.indicatorStrokeWidth = (int) a.getDimension(R.styleable.IndicatorView_hollowIndicatorStrokeWidth, density);
        }

        a.recycle();
        //初始化画笔等
        init();
    }

    private void init() {
        selectedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectedPaint.setColor(selectedColor);

        normalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        normalPaint.setColor(normalColor);
        if (hollowNormalIndicator) {
            normalPaint.setStyle(Paint.Style.STROKE);
            normalPaint.setStrokeWidth(indicatorStrokeWidth);
        }
    }

    @Override
    public void setSize(int size) {
        if (this.size == size) return;
        this.size = size;

        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = size == 0 ? 0 : (indicatorWidth + spaceWidth) * size - spaceWidth;
        setLayoutParams(layoutParams);
    }

    public void setIndicatorWidth(int indicatorWidth) {
        this.indicatorWidth = indicatorWidth;
    }

    public void setSpaceWidth(int spaceWidth) {
        this.spaceWidth = spaceWidth;
    }

    public void setSelectedColor(@ColorInt int selectedColor) {
        this.selectedColor = selectedColor;
        selectedPaint.setColor(selectedColor);
    }

    public void setNormalColor(@ColorInt int normalColor) {
        this.normalColor = normalColor;
        normalPaint.setColor(normalColor);
    }

    public void setHollowNormalIndicator(boolean hollowNormalIndicator) {
        this.hollowNormalIndicator = hollowNormalIndicator;
        normalPaint.setStyle(hollowNormalIndicator ? Paint.Style.STROKE : Paint.Style.FILL);
    }

    public void setIndicatorStrokeWidth(int indicatorStrokeWidth) {
        this.indicatorStrokeWidth = indicatorStrokeWidth;

        normalPaint.setStrokeWidth(indicatorStrokeWidth);
    }

    @Override
    public void setSelected(int selected) {
        this.selected = (size == 0) ? 0 : selected % size;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(indicatorWidth, MeasureSpec.EXACTLY);
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(size == 0 ? 0 : (indicatorWidth + spaceWidth) * size - spaceWidth, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < size; i++) {

            int rx = (indicatorWidth + spaceWidth) * i + indicatorWidth / 2;
            int ry = indicatorWidth / 2;

            if (i == selected) {
                canvas.drawCircle(rx, ry, ry, selectedPaint);
            } else {
                if (hollowNormalIndicator) {

                    canvas.drawCircle(rx, ry, ry - indicatorStrokeWidth / 2, normalPaint);
                } else
                    canvas.drawCircle(rx, ry, ry, normalPaint);
            }
        }

    }
}
