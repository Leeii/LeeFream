package com.leeiidesu.lib.common.imageloader;

import android.graphics.drawable.Drawable;

/**
 * ImageLoadedCallback Created by leeiidesu on 2017/9/11.
 */

public abstract class ImageLoadedCallback {
    public void onLoadFinish(Drawable result) {
    }

    public void onLoadFailed() {
    }
}
