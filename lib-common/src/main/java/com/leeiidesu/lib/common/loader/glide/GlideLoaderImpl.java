package com.leeiidesu.lib.common.loader.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.leeiidesu.lib.common.loader.ILoader;
import com.leeiidesu.lib.common.loader.OnLoaderListener;
import com.leeiidesu.lib.common.loader.Option;

import java.io.File;

/**
 * _ GlideLoaderImpl _ Created by dgg on 2017/7/21.
 */

public class GlideLoaderImpl implements ILoader {
    @Override
    public void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    @Override
    public void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

    @Override
    public void pauseLoad(Context context) {
        Glide.with(context).pauseRequests();
    }

    @Override
    public void resumeLoad(Context context) {
        Glide.with(context).resumeRequests();
    }

    @Override
    public void load(@NonNull Context context, @NonNull String url, @Nullable ImageView target, @NonNull Option option, @Nullable OnLoaderListener l) {
        RequestBuilder<Drawable> load = Glide.with(context).applyDefaultRequestOptions(new RequestOptions()).load(url);

        doLoad(load, target, option, l);
    }


    @Override
    public void load(@NonNull Context context, @NonNull Uri uri, @Nullable ImageView target, @NonNull Option option, @Nullable OnLoaderListener l) {
        RequestBuilder<Drawable> load = Glide.with(context).load(uri);
        doLoad(load, target, option, l);
    }

    @Override
    public void load(@NonNull Context context, @NonNull File file, @Nullable ImageView target, @NonNull Option option, @Nullable OnLoaderListener l) {
        RequestBuilder<Drawable> load = Glide.with(context).load(file);
        doLoad(load, target, option, l);
    }

    @Override
    public void load(@NonNull Context context, @DrawableRes int res, @Nullable ImageView target, @NonNull Option option, @Nullable OnLoaderListener l) {
        RequestBuilder<Drawable> load = Glide.with(context).load(res);
        doLoad(load, target, option, l);
    }

    private void doLoad(RequestBuilder<Drawable> load, ImageView target, Option option, final OnLoaderListener l) {
        if (target == null && l == null)
            throw new IllegalArgumentException("不能target 与 listener 同时为null");
        //设置占位图
        RequestOptions requestOptions = RequestOptions.placeholderOf(option.getPlaceholder());

        if (option.getError() != 0) {
            //设置加载失败图
            requestOptions.error(option.getError());
        }
        if (option.isCircleCrop()) {
            //圆图
            requestOptions.circleCrop();
        } else if (option.hasRound()) {
            //圆角
            requestOptions.transform(new RoundedCorners(option.getRound()));
        }

        //回调
        if (l != null) {
            load.listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    l.onLoadFailed();
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    l.onLoadFinish(resource);
                    //不设置到ImageView就拦截
                    return target == null;
                }
            });
        }

        load.thumbnail(0.1f)
                .apply(requestOptions);
        if (target != null)
            load.into(target);
        else {
            load.preload();
        }
    }

}
