package com.leeiidesu.lib.common.imageloader.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.leeiidesu.lib.common.imageloader.ImageConfiguration;
import com.leeiidesu.lib.common.imageloader.ImageEngine;
import com.leeiidesu.lib.common.imageloader.ImageLoadedCallback;

/**
 * GlideImageEngine Created by leeiidesu on 2017/9/11.
 */

public class GlideImageEngine implements ImageEngine {
    @Override
    public void clearMemoryCache(Context context) {
        Glide.get(context)
                .clearMemory();
    }

    @Override
    public void clearDiskCache(Context context) {
        Glide.get(context)
                .clearDiskCache();
    }

    @Override
    public void pauseRequests(Context context) {
        Glide.with(context)
                .pauseRequests();

    }

    @Override
    public void resumeRequests(Context context) {
        Glide.with(context)
                .resumeRequests();
    }

    @Override
    public void display(Object uri,
                        ImageView target,
                        ImageConfiguration config,
                        ImageLoadedCallback callback) {
        RequestBuilder<Drawable> load = Glide.with(target.getContext())
                .load(uri);

        loadImplements(load, target, target.getContext(), config, callback);
    }

    @Override
    public void loadImage(Object uri,
                          Context context,
                          ImageConfiguration config,
                          ImageLoadedCallback callback) {
        RequestBuilder<Drawable> load = Glide.with(context)
                .load(uri);

        loadImplements(load, null, context, config, callback);
    }

    private void loadImplements(RequestBuilder<Drawable> request,
                                ImageView target,
                                Context context,
                                ImageConfiguration config,
                                final ImageLoadedCallback callback) {
        RequestOptions options = obtainOption(context, config);

        //设置回调
        if (callback != null) {
            request.listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e,
                                            Object model,
                                            Target<Drawable> target,
                                            boolean isFirstResource) {
                    callback.onLoadFailed();
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource,
                                               Object model,
                                               Target<Drawable> target,
                                               DataSource dataSource,
                                               boolean isFirstResource) {
                    callback.onLoadFinish(resource);
                    return target == null;
                }
            });
        }

        //应用配置选项
        if (options != null) {
            request.thumbnail(0.1f)
                    .apply(options);
        }

        //加载
        if (target != null)
            request.into(target);
        else {
            request.preload();
        }
    }

    private RequestOptions obtainOption(Context context, ImageConfiguration config) {
        if (config == null) return null;

        RequestOptions options = new RequestOptions();

        //设置占位图
        Drawable placeholderDrawable;
        if ((placeholderDrawable = config.getPlaceholderDrawable(context.getResources())) != null) {
            options.placeholder(placeholderDrawable);
        }

        //设置错误占位图
        Drawable errorDrawable;
        if ((errorDrawable = config.getErrorHolderDrawable(context.getResources())) != null) {
            options.error(errorDrawable);
        }

        //设置取中心图
        options.centerCrop();

        if (config.isCircleCrop()) {
            //圆图
            options.transform(new CircleCropTransformation(config.getStrokeWidth(), config.getStrokeColor()));
        } else if (config.hasRound() || config.hasStroke()) {
            //圆角
            options.transform(
                    new RoundedTransformation(config.getRound(),
                            config.getStrokeWidth(),
                            config.getStrokeColor()));
        }

        return options;
    }

}
