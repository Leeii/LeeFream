package cn.leeii.libmvp.utils;


import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;

import cn.leeii.libmvp.model.Screen;


/**
 * 界面工具 Created by Leeii on 2015/6/15.
 */
public class UIUtil {

    /**
     * dip转px
     *
     * @param context  上下文
     * @param dipValue dip值
     * @return px值
     */
    public static int dipToPx(Context context, int dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * px转dip
     *
     * @param context 上下文
     * @param pxValue px值
     * @return dip值
     */
    public static int pxToDip(Context context, int pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值
     *
     * @param context 上下文
     * @param spValue 字体大小
     * @return 字体大小的px值
     */
    public static int spToPx(Context context, float spValue) {
        final float fontScale = context.getResources()
                .getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 将sp值转换为dp值
     *
     * @param context 上下文
     * @param spValue 字体大小
     * @return 字体大小的px值
     */
    public static int spToDip(Context context, float spValue) {
        return pxToDip(context, spToPx(context, spValue));
    }

    /**
     * 获取屏幕信息，获得宽高点坐标(PX)
     *
     * @param context 上下文
     * @return 宽高点
     */
    public static Screen screenPx(Context context) {
        // 获取设备信息
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        // 获取屏幕宽度和高度
        return new Screen(dm.widthPixels, dm.heightPixels);
    }

    /**
     * 获取屏幕信息，获得宽高点坐标(DP)
     *
     * @param context 上下文
     * @return 宽高点
     */
    public static Screen screenDip(Context context) {
        // 获取设备信息
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        // 获取屏幕宽度和高度
        return new Screen(pxToDip(context, dm.widthPixels),
                pxToDip(context, dm.heightPixels));
    }

    /**
     * 测量view，通过getMeasuredWidth()获取宽度和高度 在onCreate方法中获取未绘制的控件宽高
     *
     * @param v 要测量的view
     */
    public static void measureView(View v) {
        if (v == null)
            return;
        int w =
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h =
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(w, h);
    }

    /**
     * 设置颜色透明度
     *
     * @param color 颜色
     * @param alpha 透明度
     * @return 带有透明度的颜色
     */
    public static int colorAlpha(int color, float alpha) {
        int alpha_int = Math.min(Math.max((int) (alpha * 255.0f), 0), 255);
        return Color.argb(alpha_int,
                Color.red(color),
                Color.green(color),
                Color.blue(color));
    }

    /**
     * 设置颜色透明度
     *
     * @param color 颜色16进制
     * @param alpha 透明度
     * @return 带有透明度的颜色
     */
    public static int colorAlpha(String color, float alpha) {
        return colorAlpha(Color.parseColor(color), alpha);
    }

}
