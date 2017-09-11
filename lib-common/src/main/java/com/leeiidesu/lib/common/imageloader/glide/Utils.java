package com.leeiidesu.lib.common.imageloader.glide;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Synthetic;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * _ Utils _ Created by dgg on 2017/7/26.
 */

class Utils {
    public static final int PAINT_FLAGS = Paint.DITHER_FLAG | Paint.FILTER_BITMAP_FLAG;
    private static final Paint DEFAULT_PAINT = new Paint(PAINT_FLAGS);
    private static final int CIRCLE_CROP_PAINT_FLAGS = PAINT_FLAGS | Paint.ANTI_ALIAS_FLAG;
    private static final Paint CIRCLE_CROP_SHAPE_PAINT = new Paint(CIRCLE_CROP_PAINT_FLAGS);
    private static final Paint CIRCLE_CROP_BITMAP_PAINT;
    private static final Paint STROKE_PAINT;

    static {
        CIRCLE_CROP_BITMAP_PAINT = new Paint(CIRCLE_CROP_PAINT_FLAGS);
        CIRCLE_CROP_BITMAP_PAINT.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        STROKE_PAINT = new Paint(CIRCLE_CROP_PAINT_FLAGS);
        STROKE_PAINT.setStyle(Paint.Style.STROKE);
    }

    // See #738.
    private static final List<String> MODELS_REQUIRING_BITMAP_LOCK =
            Arrays.asList(
                    "XT1097",
                    "XT1085");
    /**
     * https://github.com/bumptech/glide/issues/738 On some devices (Moto X with android 5.1) bitmap
     * drawing is not thread safe.
     * This lock only locks for these specific devices. For other types of devices the lock is always
     * available and therefore does not impact performance
     */
    private static final Lock BITMAP_DRAWABLE_LOCK =
            MODELS_REQUIRING_BITMAP_LOCK.contains(Build.MODEL)
                    && Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1
                    ? new ReentrantLock() : new NoLock();


    /**
     * Crop the image to a circle and resize to the specified width/height.  The circle crop will
     * have the same width and height equal to the min-edge of the result image.
     *
     * @param pool        The BitmapPool obtain a bitmap from.
     * @param inBitmap    The Bitmap to resize.
     * @param destWidth   The width in pixels of the final Bitmap.
     * @param destHeight  The height in pixels of the final Bitmap.
     * @param strokeWidth The image stroke width.
     * @param strokeColor The image stroke color.
     * @return The resized Bitmap (will be recycled if recycled is not null).
     */
    static Bitmap circleCrop(@NonNull BitmapPool pool, @NonNull Bitmap inBitmap,
                             int destWidth, int destHeight, int strokeWidth, @ColorInt int strokeColor) {
        int destMinEdge = Math.min(destWidth, destHeight);
        float radius = destMinEdge / 2f;

        int srcWidth = inBitmap.getWidth();
        int srcHeight = inBitmap.getHeight();

        float scaleX = destMinEdge / (float) srcWidth;
        float scaleY = destMinEdge / (float) srcHeight;
        float maxScale = Math.max(scaleX, scaleY);

        float scaledWidth = maxScale * srcWidth;
        float scaledHeight = maxScale * srcHeight;
        float left = (destMinEdge - scaledWidth) / 2f;
        float top = (destMinEdge - scaledHeight) / 2f;

        RectF destRect = new RectF(left, top, left + scaledWidth, top + scaledHeight);

        // Alpha is required for this transformation.
        Bitmap toTransform = getAlphaSafeBitmap(pool, inBitmap);

        Bitmap result = pool.get(destMinEdge, destMinEdge, Bitmap.Config.ARGB_8888);
        result.setHasAlpha(true);

        BITMAP_DRAWABLE_LOCK.lock();
        try {
            Canvas canvas = new Canvas(result);
            // Draw a circle
            canvas.drawCircle(radius, radius, radius, CIRCLE_CROP_SHAPE_PAINT);
            // Draw the bitmap in the circle
            canvas.drawBitmap(toTransform, null, destRect, CIRCLE_CROP_BITMAP_PAINT);
            if (strokeWidth > 0) {
                STROKE_PAINT.setStrokeWidth(strokeWidth);
                STROKE_PAINT.setColor(strokeColor);
                canvas.drawCircle(radius, radius, radius - strokeWidth / 2f, STROKE_PAINT);
            }

            clear(canvas);
        } finally {
            BITMAP_DRAWABLE_LOCK.unlock();
        }

        if (!toTransform.equals(inBitmap)) {
            pool.put(toTransform);
        }

        return result;
    }

    // Avoids warnings in M+.
    private static void clear(Canvas canvas) {
        canvas.setBitmap(null);
    }

    private static Bitmap getAlphaSafeBitmap(@NonNull BitmapPool pool,
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

    static Bitmap round(BitmapPool pool, Bitmap inBitmap, int width, int height,
                        int roundingRadius, int strokeWidth, int strokeColor) {
        Preconditions.checkArgument(width > 0, "width must be greater than 0.");
        Preconditions.checkArgument(height > 0, "height must be greater than 0.");

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
        BITMAP_DRAWABLE_LOCK.lock();
        try {
            Canvas canvas = new Canvas(result);

            //在主画布上画源图
            canvas.drawBitmap(inBitmap, -widthTranslate, -heightTranslate, paint);

            if (roundingRadius > 0) {
                //画圆角
                //前景
                Canvas foreground = new Canvas(result2);
                //画前景的透明背景
                foreground.drawColor(Color.TRANSPARENT);
                foreground.drawRoundRect(rect, roundingRadius, roundingRadius, paint);
                //清除引用
                clear(foreground);

                //设置交叉模式
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                //画前景
                canvas.drawBitmap(result2, 0, 0, paint);
            }

            if (strokeWidth > 0) {
                //取消设置交叉模式
                STROKE_PAINT.setStyle(Paint.Style.STROKE);
                STROKE_PAINT.setStrokeWidth(strokeWidth);
                STROKE_PAINT.setColor(strokeColor);

                float out = strokeWidth / 2f;

                rect.bottom -= out;
                rect.right -= out;
                rect.top += out;
                rect.left += out;

                canvas.drawRoundRect(rect, roundingRadius, roundingRadius, STROKE_PAINT);
            }

            clear(canvas);
            result2.recycle();
        } finally {
            BITMAP_DRAWABLE_LOCK.unlock();
        }

        if (!toTransform.equals(inBitmap)) {
            pool.put(toTransform);
        }

        return result;
    }

    private static final class NoLock implements Lock {

        @Synthetic
        NoLock() {
        }

        @Override
        public void lock() {
            // do nothing
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
            // do nothing
        }

        @Override
        public boolean tryLock() {
            return true;
        }

        @Override
        public boolean tryLock(long time, @NonNull TimeUnit unit) throws InterruptedException {
            return true;
        }

        @Override
        public void unlock() {
            // do nothing
        }

        @NonNull
        @Override
        public Condition newCondition() {
            throw new UnsupportedOperationException("Should not be called");
        }
    }
}
