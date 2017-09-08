package com.leeiidesu.lib.permission;

import com.leeiidesu.lib.permission.listener.OnPermissionResultListener;

/**
 * Request Created by leeiidesu on 2017/8/28.
 */

public interface Request {
    /**
     * 设置一次拒绝之后显示的提示
     *
     * @param rationale 提示文本
     */
    Request showOnRationale(String rationale);

    /**
     * 设置一次拒绝之后显示的提示
     *
     * @param rationale   提示文本
     * @param cancelText  取消按钮文字
     * @param confirmText 确认按钮文本
     */
    Request showOnRationale(String rationale,
                            String cancelText,
                            String confirmText);

    /**
     * 设置勾选不再提示之后显示的提示
     *
     * @param denied 提示文本
     */
    Request showOnDenied(String denied);

    /**
     * 设置勾选不再提示之后显示的提示
     *
     * @param denied      提示文本
     * @param cancelText  取消按钮文本
     * @param confirmText 确认按钮文本
     */
    Request showOnDenied(String denied,
                         String cancelText,
                         String confirmText);

    /**
     * 设置回调
     *
     * @param l 回调
     */
    Request listener(OnPermissionResultListener l);

    /**
     * 发起请求
     */
    void request();
}
