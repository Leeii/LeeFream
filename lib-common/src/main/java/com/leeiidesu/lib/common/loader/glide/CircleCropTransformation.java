package com.leeiidesu.lib.common.loader.glide;

import android.graphics.Bitmap;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * _ CircleCropTransformation _ Created by dgg on 2017/7/26.
 */

public class CircleCropTransformation extends BitmapTransformation {
    private static final int VERSION = 1;
    private static final String ID = "com.leeiidesu.lib.common.loader.glide.CircleCropTransformation." + VERSION;
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    private final int strokeWidth;

    private final
    @ColorInt
    int strokeColor;

    public CircleCropTransformation(int strokeWidth, int strokeColor) {
        // Intentionally empty.
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
    }


    // Bitmap doesn't implement equals, so == and .equals are equivalent here.
    @SuppressWarnings("PMD.CompareObjectsWithEquals")
    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap inBitmap,
                               int destWidth, int destHeight) {
        return Utils.circleCrop(pool, inBitmap, destWidth, destHeight, strokeWidth, strokeColor);
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof CircleCropTransformation) {
            CircleCropTransformation roundedTransformation = (CircleCropTransformation) o;
            if (roundedTransformation.strokeWidth == strokeWidth &&
                    roundedTransformation.strokeColor == strokeColor)
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
