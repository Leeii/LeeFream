package com.leeiidesu.lib.permission;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.leeiidesu.lib.permission.listener.OnPermissionResultListener;

import java.util.ArrayList;
import java.util.Locale;

/**
 * 权限申请Fragment Created by leeiidesu on 2017/7/14.
 */

public class PermissionFragment extends Fragment {
    static final String TAG = PermissionFragment.class.getSimpleName();

    private OnPermissionResultListener l;
    private Config mConfig;


    public void setOnPermissionResultListener(OnPermissionResultListener l) {
        this.l = l;
    }

    static PermissionFragment newInstance(Config config, OnPermissionResultListener l, String... permissions) {
        PermissionFragment fragment = new PermissionFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("permissions", permissions);
        bundle.putParcelable("config", config);

        fragment.setOnPermissionResultListener(l);

        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "permission fragment onAttach(context)");
        attachContext();
    }

    private void attachContext() {
        String[] permissions = getArguments().getStringArray("permissions");
        Config config = getArguments().getParcelable("config");

        assert permissions != null;
        requestPermissions(permissions, config);
    }


    void requestPermissions(String[] permissions, Config config) {
        mConfig = config;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            request(permissions);
        } else {
            if (l != null) {
                l.onGranted();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void request(String[] permissions) {
        boolean showRationale = false;

        ArrayList<String> deniedPermissions = new ArrayList<>();

        for (String permission : permissions) {
            if (getActivity().checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permission);
                if (shouldShowRequestPermissionRationale(permission)) {
                    showRationale = true;
                }
            }
        }

        if (deniedPermissions.size() > 0) {
            if (showRationale && mConfig != null && mConfig.rationaleText != null) {
                showRationaleDialog(deniedPermissions);
            } else
                requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), 0x1001);
        } else {
            if (l != null)
                l.onGranted();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void showRationaleDialog(final ArrayList<String> permissions) {
        final DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_NEGATIVE:
                        if (l != null)
                            l.onFailed(permissions);
                        break;
                    case DialogInterface.BUTTON_POSITIVE:
                        requestPermissions(permissions.toArray(new String[permissions.size()]), 0x1001);
                        break;
                }
            }
        };
        new AlertDialog.Builder(getActivity())
                .setMessage(mConfig.rationaleText)
                .setNegativeButton(mConfig.rationaleCancelText, listener)
                .setPositiveButton(mConfig.rationaleConfirmText, listener)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        if (l != null)
                            l.onFailed(permissions);
                    }
                }).show();
    }


    /**
     * 跳转到设置界面
     */
    private void startSetting() {
        try {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    .setData(Uri.parse("package:" + getActivity().getPackageName()));
            getActivity().startActivityForResult(intent, 0x2001);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                getActivity().startActivityForResult(intent, 0x2001);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        ArrayList<String> deniedPermissions = new ArrayList<>();
        if (requestCode == 0x1001) {
            for (int i = 0; i < permissions.length; i++) {
                Log.i(TAG, String.format(Locale.CHINA,
                        "PermissionName = %s,isGrant = %s",
                        permissions[i],
                        grantResults[i] == PackageManager.PERMISSION_GRANTED));
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    deniedPermissions.add(permissions[i]);
                }
            }

            if (deniedPermissions.size() > 0) {
                //包含请求失败的权限
                if (mConfig != null && mConfig.deniedText != null) {
                    //如果配置了失败时回调，弹出对话框提示用户
                    showDeniedDialog(deniedPermissions);
                } else {
                    //否则回调失败
                    if (l != null)
                        l.onFailed(deniedPermissions);
                }
            } else {
                //没有请求失败的则回调请求成功
                if (l != null)
                    l.onGranted();
            }
        }
    }

    /**
     * 弹出请求失败对话框，提示信息让用户去设置界面操作
     *
     * @param deniedPermissions 失败的权限们
     */
    private void showDeniedDialog(final ArrayList<String> deniedPermissions) {
        final DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_NEGATIVE:
                        if (l != null)
                            l.onFailed(deniedPermissions);
                        break;
                    case DialogInterface.BUTTON_POSITIVE:
                        startSetting();
                        break;
                }
            }
        };
        new AlertDialog.Builder(getActivity())
                .setMessage(mConfig.deniedText)
                .setNegativeButton(mConfig.deniedCancelText, listener)
                .setPositiveButton(mConfig.deniedConfirmText, listener)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        if (l != null)
                            l.onFailed(deniedPermissions);
                    }
                }).show();
    }
}
