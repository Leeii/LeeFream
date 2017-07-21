package com.leeiidesu.lib.common.loader;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import java.io.File;

/**
 * _ ILoader _ Created by dgg on 2017/7/20.
 */

public interface ILoader {

    void clearMemoryCache(Context context);

    void clearDiskCache(Context context);

    void pauseLoad(Context context);

    void resumeLoad(Context context);

    void load(@NonNull Context context, @NonNull String url, @Nullable ImageView target, @NonNull Option option, @Nullable OnLoaderListener l);

    void load(@NonNull Context context, @NonNull Uri uri, @Nullable ImageView target, @NonNull Option option, @Nullable OnLoaderListener l);

    void load(@NonNull Context context, @NonNull File file, @Nullable ImageView target, @NonNull Option option, @Nullable OnLoaderListener l);

    void load(@NonNull Context context, @DrawableRes int res, @Nullable ImageView target, @NonNull Option option, @Nullable OnLoaderListener l);
}
