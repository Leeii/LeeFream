package com.leeiidesu.lib.common.imageloader;

import android.content.Context;
import android.widget.ImageView;

/**
 * ImageEngine Created by leeiidesu on 2017/9/11.
 */

public interface ImageEngine {
    /**
     * 清除内存缓存
     */
    void clearMemoryCache(Context context);

    /**
     * 清除磁盘缓存
     */
    void clearDiskCache(Context context);


    /**
     * 暂停加载
     */
    void pauseRequests(Context context);

    /**
     * 恢复加载
     */
    void resumeRequests(Context context);

    /**
     * 显示图片
     *
     * @param path     图片path
     * @param target   目标ImageView
     * @param config   选项
     * @param callback 回调
     */
    void display(Object path, ImageView target, ImageConfiguration config, ImageLoadedCallback callback);


    /**
     * 加载图片
     *
     * @param path     图片uri
     * @param context  上下文
     * @param config   选项
     * @param callback 回调
     */
    void loadImage(Object path, Context context, ImageConfiguration config, ImageLoadedCallback callback);
}