package com.leeiidesu.lib.common.imageloader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.leeiidesu.lib.common.imageloader.glide.GlideImageEngine;

/**
 * ImageLoader Created by leeiidesu on 2017/9/11.
 */

public class ImageLoader {
    private ImageConfiguration defaultConfiguration;

    private ImageEngine mImageEngine;

    private ImageLoader() {
        mImageEngine = new GlideImageEngine();
    }

    public static ImageLoader getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        static ImageLoader INSTANCE = new ImageLoader();
    }

    public void init(ImageConfiguration defaultConfiguration) {
        init(new GlideImageEngine(), defaultConfiguration);
    }

    public void init(ImageEngine engine, ImageConfiguration defaultConfiguration) {
        mImageEngine = engine;
        this.defaultConfiguration = defaultConfiguration;
    }

    public void display(Object path,
                        @NonNull ImageView target) {
        display(path, target, defaultConfiguration);
    }

    public void display(Object path,
                        @NonNull ImageView target,
                        ImageConfiguration configuration) {
        display(path, target, configuration, null);
    }

    public void display(Object path,
                        @NonNull ImageView target,
                        ImageConfiguration configuration,
                        ImageLoadedCallback callback) {
        mImageEngine.display(path, target, configuration, callback);
    }

    public void loadImage(Object path,
                          @NonNull Context context,
                          @NonNull ImageLoadedCallback callback) {
        loadImage(path, context, defaultConfiguration, callback);
    }

    public void loadImage(Object path,
                          @NonNull Context context,
                          ImageConfiguration configuration,
                          @NonNull ImageLoadedCallback callback) {
        mImageEngine.loadImage(path, context, configuration, callback);
    }


    public void clearMemoryCache(Context context) {
        mImageEngine.clearMemoryCache(context);
    }

    public void clearDiskCache(Context context) {
        mImageEngine.clearDiskCache(context);
    }

    public void pauseRequests(Context context) {
        mImageEngine.pauseRequests(context);

    }

    public void resumeRequests(Context context) {
        mImageEngine.resumeRequests(context);
    }

}
