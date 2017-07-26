package cn.leeii.simple.ui.videopicker.loader;

import android.content.Context;
import android.provider.MediaStore;
import android.provider.MediaStore.Video.Media;
import android.support.v4.content.CursorLoader;

/**
 * _ VideoLoader _ Created by dgg on 2017/7/26.
 */

public class VideoLoader extends CursorLoader {
    final String[] IMAGE_PROJECTION = {
            Media._ID,
            Media.DATA,
            Media.BUCKET_ID,
            Media.BUCKET_DISPLAY_NAME,
            Media.DATE_ADDED,
            Media.SIZE,
            Media.DURATION
    };



    public VideoLoader(Context context) {
        super(context);

        setProjection(IMAGE_PROJECTION);
        setUri(Media.EXTERNAL_CONTENT_URI);
        setSortOrder(Media.DATE_ADDED + " DESC");

        setSelection(
                MediaStore.MediaColumns.MIME_TYPE + "=? or " +
                        MediaStore.MediaColumns.MIME_TYPE + "=? or " +
                        MediaStore.MediaColumns.MIME_TYPE + "=? or " +
                        MediaStore.MediaColumns.MIME_TYPE + "=? ");
        String[] selectionArgs;


        selectionArgs = new String[]{"video/mpeg", "video/mp4", "video/3gpp", "video/avi"};

        setSelectionArgs(selectionArgs);
    }


}
