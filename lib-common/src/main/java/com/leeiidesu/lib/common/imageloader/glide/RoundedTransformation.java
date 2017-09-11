package com.leeiidesu.lib.common.imageloader.glide;

import android.graphics.Bitmap;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.util.Preconditions;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

/**
 * _ RoundedTransformation _ Created by dgg on 2017/7/24.
 */

public class RoundedTransformation extends BitmapTransformation {
    private static final String ID = "com.leeiidesu.lib.common.a.glide.RoundedTransformation";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    private final int roundingRadius;

    private final int strokeWidth;

    private final
    @ColorInt
    int strokeColor;

    /**
     * @param roundingRadius the corner radius (in device-specific pixels).
     * @param strokeWidth
     * @param strokeColor
     * @throws IllegalArgumentException if rounding radius is 0 or less.
     */
    public RoundedTransformation(int roundingRadius, int strokeWidth, int strokeColor) {
        Preconditions.checkArgument(roundingRadius > 0 || strokeWidth > 0, "roundingRadius or strokeWidth must be greater than 0.");
        this.roundingRadius = roundingRadius;

        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
    }

    @Override
    protected Bitmap transform(
            @NonNull BitmapPool pool, @NonNull Bitmap inBitmap, int width, int height) {
        return Utils.round(pool, inBitmap, width, height, roundingRadius, strokeWidth, strokeColor);

    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof RoundedTransformation) {
            RoundedTransformation roundedTransformation = (RoundedTransformation) o;
            if (roundedTransformation.roundingRadius == roundingRadius &&
                    roundedTransformation.strokeWidth == strokeWidth &&
                    roundedTransformation.strokeColor == strokeColor)
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return ID.hashCode() + roundingRadius;
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);

        byte[] radiusData = ByteBuffer.allocate(4).putInt(roundingRadius).array();
        messageDigest.update(radiusData);
    }
}
