package com.leeiidesu.lib.common.loader.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.util.Preconditions;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

/**
 * _ RoundedCorners _ Created by dgg on 2017/7/24.
 */

public class RoundedCorners extends BitmapTransformation {
    private static final String ID = "com.leeiidesu.lib.common.loader.glide.RoundedCorners";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    private final int roundingRadius;

    /**
     * @param roundingRadius the corner radius (in device-specific pixels).
     * @throws IllegalArgumentException if rounding radius is 0 or less.
     */
    public RoundedCorners(int roundingRadius) {
        Preconditions.checkArgument(roundingRadius > 0, "roundingRadius must be greater than 0.");
        this.roundingRadius = roundingRadius;
    }

    /**
     * @param roundingRadius the corner radius (in device-specific pixels).
     * @throws IllegalArgumentException if rounding radius is 0 or less.
     * @deprecated Use {@link #RoundedCorners(int)}
     */
    @Deprecated
    public RoundedCorners(@SuppressWarnings("unused") BitmapPool bitmapPool, int roundingRadius) {
        this(roundingRadius);
    }

    /**
     * @param roundingRadius the corner radius (in device-specific pixels).
     * @throws IllegalArgumentException if rounding radius is 0 or less.
     * @deprecated Use {@link #RoundedCorners(int)}
     */
    @Deprecated
    public RoundedCorners(@SuppressWarnings("unused") Context context, int roundingRadius) {
        this(roundingRadius);
    }

    @Override
    protected Bitmap transform(
            @NonNull BitmapPool pool, @NonNull Bitmap inBitmap, int width, int height) {
        Preconditions.checkArgument(width > 0, "width must be greater than 0.");
        Preconditions.checkArgument(height > 0, "height must be greater than 0.");
        Preconditions.checkArgument(roundingRadius > 0, "roundingRadius must be greater than 0.");

        int heightTranslate = 0;
        int widthTranslate = 0;

        if (height < inBitmap.getHeight()) {
            heightTranslate = (inBitmap.getHeight() - height) / 2;
        }
        if (width < inBitmap.getWidth()) {
            widthTranslate = (inBitmap.getWidth() - width) / 2;
        }

        // Alpha is required for this transformation.
        Bitmap toTransform = getAlphaSafeBitmap(pool, inBitmap);
        Bitmap result = pool.get(width, height, Bitmap.Config.ARGB_8888);
        Bitmap result2 = pool.get(width, height, Bitmap.Config.ARGB_8888);

        result.setHasAlpha(true);
        result2.setHasAlpha(true);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        RectF rect = new RectF(0, 0, result.getWidth(), result.getHeight());
        //画布
        Canvas canvas = new Canvas(result);
        //前景
        Canvas foreground = new Canvas(result2);
        //画前景的透明背景
        foreground.drawColor(Color.TRANSPARENT);
        //画圆角
        foreground.drawRoundRect(rect, roundingRadius, roundingRadius, paint);
        //清除引用
        clear(foreground);

        //在主画布上画源图
        canvas.drawBitmap(inBitmap, -widthTranslate, -heightTranslate, paint);
        //设置交叉模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        //画前景
        canvas.drawBitmap(result2, 0, 0, paint);


        clear(canvas);

        result2.recycle();


        if (!toTransform.equals(inBitmap)) {
            pool.put(toTransform);
        }

        return result;
    }

    // Avoids warnings in M+.
    private void clear(Canvas canvas) {
        canvas.setBitmap(null);
    }

    private Bitmap getAlphaSafeBitmap(@NonNull BitmapPool pool,
                                      @NonNull Bitmap maybeAlphaSafe) {
        if (Bitmap.Config.ARGB_8888.equals(maybeAlphaSafe.getConfig())) {
            return maybeAlphaSafe;
        }

        Bitmap argbBitmap = pool.get(maybeAlphaSafe.getWidth(), maybeAlphaSafe.getHeight(),
                Bitmap.Config.ARGB_8888);
        new Canvas(argbBitmap).drawBitmap(maybeAlphaSafe, 0 /*left*/, 0 /*top*/, null /*pain*/);

        // We now own this Bitmap. It's our responsibility to replace it in the pool outside this method
        // when we're finished with it.
        return argbBitmap;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof RoundedCorners) && ((RoundedCorners) o).roundingRadius == roundingRadius;
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
