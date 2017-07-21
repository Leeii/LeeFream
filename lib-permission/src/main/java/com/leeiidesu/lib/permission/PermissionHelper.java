package com.leeiidesu.lib.permission;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.support.annotation.NonNull;


/**
 * 权限申请辅助类 Created by leeiidesu on 2017/7/14.
 */

public class PermissionHelper {
    private static final String FRAGMENT_TAG = "PERMISSION_FRAGMENT__";

    private PermissionHelper() {

    }

    public static void request(@NonNull Activity activity,
                               @NonNull String[] permissions) {
        request(activity.getFragmentManager(), permissions, null, null);
    }

    public static void request(@NonNull Activity activity,
                               @NonNull String[] permissions,
                               Config config,
                               OnPermissionResultListener l) {
        request(activity.getFragmentManager(), permissions, config, l);
    }

    public static void request(@NonNull Fragment fragment,
                               @NonNull String[] permissions) {
        request(fragment.getFragmentManager(), permissions, null, null);
    }

    public static void request(@NonNull Fragment fragment,
                               @NonNull String[] permissions,
                               Config config,
                               OnPermissionResultListener l) {
        request(fragment.getFragmentManager(), permissions, config, l);
    }

    public static void request(@NonNull android.support.v4.app.Fragment fragment,
                               @NonNull String[] permissions) {
        request(fragment.getActivity().getFragmentManager(), permissions, null, null);
    }

    public static void request(@NonNull android.support.v4.app.Fragment fragment,
                               @NonNull String[] permissions,
                               Config config,
                               OnPermissionResultListener l) {
        request(fragment.getActivity().getFragmentManager(), permissions, config, l);
    }

    public static void request(@NonNull FragmentManager fm,
                               @NonNull String[] permissions,
                               Config config,
                               OnPermissionResultListener l) {
        PermissionFragment fragment = (PermissionFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (fragment == null) {
            fragment = PermissionFragment.newInstance(config, l, permissions);

            final FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(fragment, FRAGMENT_TAG);
            transaction.commit();

        } else if (Build.VERSION.SDK_INT >= 13 && fragment.isDetached()) {
            final FragmentTransaction transaction = fm.beginTransaction();
            transaction.attach(fragment);
            transaction.commit();
        } else {
            fragment.requestPermissions(permissions, config);
        }
    }
}
