package com.leeiidesu.lib.common.imageloader;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

/**
 * ImageConfiguration Created by leeiidesu on 2017/9/11.
 */

public class ImageConfiguration {
    // 边框宽度
    private final int mStrokeWidth;
    // 边框颜色
    private final int mStrokeColor;

    // 圆角
    private final int mRound;
    // 圆图
    private final boolean circleCrop;

    // 占位图
    private final int placeholderRes;
    // 错误占位图
    private final int errorHolderRes;
    // 占位图
    private final Drawable placeholderDrawable;
    // 错误占位图
    private final Drawable errorHolderDrawable;

    private ImageConfiguration(Builder builder) {
        this.mStrokeColor = builder.mStrokeColor;
        this.mStrokeWidth = builder.mStrokeWidth;
        this.mRound = builder.mRound;
        this.circleCrop = builder.circleCrop;
        this.placeholderRes = builder.placeholderRes;
        this.errorHolderRes = builder.errorHolderRes;
        this.placeholderDrawable = builder.placeholderDrawable;
        this.errorHolderDrawable = builder.errorHolderDrawable;
    }

    public Drawable getPlaceholderDrawable(Resources resources) {
        return placeholderRes != 0 ? resources.getDrawable(placeholderRes) : placeholderDrawable;
    }

    public Drawable getErrorHolderDrawable(Resources resources) {
        return errorHolderRes != 0 ? resources.getDrawable(errorHolderRes) : errorHolderDrawable;
    }

    public boolean isCircleCrop() {
        return circleCrop;
    }

    public int getRound() {
        return mRound;
    }

    public boolean hasRound() {
        return mRound > 0;
    }

    public boolean hasStroke() {
        return mStrokeWidth > 0;
    }

    public int getStrokeColor() {
        return mStrokeColor;
    }

    public int getStrokeWidth() {
        return mStrokeWidth;
    }

    public static class Builder {
        // 边框宽度
        private int mStrokeWidth;
        // 边框颜色
        private int mStrokeColor;

        // 圆角
        private int mRound;
        // 圆图
        private boolean circleCrop;

        // 占位图
        private int placeholderRes;
        // 错误占位图
        private int errorHolderRes;
        // 占位图
        private Drawable placeholderDrawable;
        // 错误占位图
        private Drawable errorHolderDrawable;

        public Builder setStrokeWidth(int mStrokeWidth) {
            this.mStrokeWidth = mStrokeWidth;
            return this;
        }

        public Builder setStrokeColor(int mStrokeColor) {
            this.mStrokeColor = mStrokeColor;
            return this;
        }

        public Builder round(int mRound) {
            this.mRound = mRound;
            return this;
        }

        public Builder circleCrop() {
            this.circleCrop = true;
            return this;
        }

        public Builder placeholder(@DrawableRes int placeholderRes) {
            this.placeholderRes = placeholderRes;
            return this;
        }

        public Builder errorholder(@DrawableRes int errorHolderRes) {
            this.errorHolderRes = errorHolderRes;
            return this;
        }

        public Builder placeholder(Drawable placeholderDrawable) {
            this.placeholderDrawable = placeholderDrawable;
            return this;
        }

        public Builder errorholder(Drawable errorHolderDrawable) {
            this.errorHolderDrawable = errorHolderDrawable;
            return this;
        }

        public Builder stroke(int width, @ColorInt int color) {
            setStrokeWidth(width);
            setStrokeColor(color);
            return this;
        }

        public ImageConfiguration build() {
            return new ImageConfiguration(this);
        }


    }
}
