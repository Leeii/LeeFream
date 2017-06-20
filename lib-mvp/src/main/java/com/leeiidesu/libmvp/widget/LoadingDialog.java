package com.leeiidesu.libmvp.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.leeiidesu.libcommon.android.UIUtil;

import cn.leeii.libmvp.R;

/**
 * 加载的圈圈 Created by Lee on 2016/12/26.
 */

public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context, R.style.dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 取标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        // 获取所有字段，包括私有字段，但不包括父类字段

        FrameLayout content = new FrameLayout(getContext());

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        content.setLayoutParams(params);
        int dp8 = UIUtil.dipToPx(getContext(), 8);
        content.setPadding(dp8, dp8, dp8, dp8);

        ProgressWheel progress_wheel = new ProgressWheel(getContext());

        progress_wheel.setLayoutParams(new FrameLayout.LayoutParams(dp8 * 8, dp8 * 8)); //64dp
        progress_wheel.setBarColor(Color.parseColor("#0c3343"));
        progress_wheel.setBarWidth(dp8 / 4);
        progress_wheel.setFillRadius(true);

        content.addView(progress_wheel);

        setContentView(content);
    }
}
