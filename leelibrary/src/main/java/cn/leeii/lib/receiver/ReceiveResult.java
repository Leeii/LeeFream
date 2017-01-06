package cn.leeii.lib.receiver;

import android.content.Intent;
import android.os.Parcelable;

/**
 * 广播结果接口 Created by Leeii on 2016/1/21.
 */
public interface ReceiveResult {

    /**
     * 应用内广播接收
     *
     * @param intent 接收的意图
     */
    <T extends Parcelable> void onReceive(Intent intent);
}
