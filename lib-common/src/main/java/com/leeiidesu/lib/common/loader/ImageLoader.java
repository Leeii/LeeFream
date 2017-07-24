package com.leeiidesu.lib.common.loader;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.leeiidesu.lib.common.loader.glide.GlideLoaderImpl;

import java.io.File;

/**
 * _ ImageLoader _ Created by dgg on 2017/7/20.
 */

public class ImageLoader {
    private static ILoader mLoaderImpl = new GlideLoaderImpl();

    private static Option defaultOption = Option.builder().build();

    public static void load(String url, ImageView target) {
        load(url, target, defaultOption, null);
    }

    public static void load(String url, ImageView target, OnLoaderListener l) {
        load(url, target, defaultOption, l);
    }

    public static void load(String url, Context context, OnLoaderListener l) {
        load(context, url, null, defaultOption, l);
    }

    public static void load(String url, ImageView target, @NonNull Option option) {
        load(target.getContext(), url, target, option, null);
    }

    public static void load(String url, ImageView target, @NonNull Option option, OnLoaderListener l) {
        load(target.getContext(), url, target, option, l);
    }

    private static void load(Context context, String url, ImageView target, @NonNull Option option, OnLoaderListener l) {
        mLoaderImpl.load(context, url, target, option, l);
    }

    public static void load(Uri uri, ImageView target) {
        load(uri, target, defaultOption, null);
    }

    public static void load(Uri uri, ImageView target, OnLoaderListener l) {
        load(uri, target, defaultOption, l);
    }

    public static void load(Uri uri, Context context, OnLoaderListener l) {
        load(context, uri, null, defaultOption, l);
    }

    public static void load(Uri uri, ImageView target, @NonNull Option option) {
        load(target.getContext(), uri, target, option, null);
    }

    public static void load(Uri uri, ImageView target, @NonNull Option option, OnLoaderListener l) {
        load(target.getContext(), uri, target, option, l);
    }

    private static void load(Context context, Uri uri, ImageView target, @NonNull Option option, OnLoaderListener l) {
        mLoaderImpl.load(context, uri, target, option, l);
    }

    public static void load(File file, ImageView target) {
        load(file, target, defaultOption, null);
    }

    public static void load(File file, ImageView target, OnLoaderListener l) {
        load(file, target, defaultOption, l);
    }

    public static void load(File file, Context context, OnLoaderListener l) {
        load(context, file, null, defaultOption, l);
    }

    public static void load(File file, ImageView target, @NonNull Option option, OnLoaderListener l) {
        load(target.getContext(), file, target, option, l);
    }

    public static void load(File file, ImageView target, @NonNull Option option) {
        load(target.getContext(), file, target, option, null);
    }

    private static void load(Context context, File file, ImageView target, @NonNull Option option, OnLoaderListener l) {
        mLoaderImpl.load(context, file, target, option, l);
    }

    public static void load(@DrawableRes int res, ImageView target) {
        load(res, target, defaultOption, null);
    }

    public static void load(@DrawableRes int res, ImageView target, OnLoaderListener l) {
        load(res, target, defaultOption, l);
    }

    public static void load(@DrawableRes int res, Context context, OnLoaderListener l) {
        load(context, res, null, defaultOption, l);
    }

    public static void load(@DrawableRes int res, ImageView target, @NonNull Option option) {
        load(target.getContext(), res, target, option, null);
    }

    public static void load(@DrawableRes int res, ImageView target, @NonNull Option option, OnLoaderListener l) {
        load(target.getContext(), res, target, option, l);
    }

    private static void load(Context context, @DrawableRes int res, ImageView target, @NonNull Option option, OnLoaderListener l) {
        mLoaderImpl.load(context, res, target, option, l);
    }

    public static void setDefaultOption(Option defaultOption) {
        ImageLoader.defaultOption = defaultOption;
    }

    public static void setDefaultPlaceholder(@DrawableRes int placeholder) {
        ImageLoader.defaultOption.setPlaceholder(placeholder);
    }

    public static void setLoaderImpl(ILoader mLoaderImpl) {
        ImageLoader.mLoaderImpl = mLoaderImpl;
    }
}
