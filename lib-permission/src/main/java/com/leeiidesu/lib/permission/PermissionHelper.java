package com.leeiidesu.lib.permission;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;


/**
 * 权限申请辅助类 Created by leeiidesu on 2017/7/14.
 */

public class PermissionHelper {

    private final FragmentManager mFragmentManager;

    private PermissionHelper(FragmentManager fm) {
        this.mFragmentManager = fm;
    }


    public static PermissionHelper with(FragmentActivity activity) {
        return with(activity.getSupportFragmentManager());
    }

    public static PermissionHelper with(Fragment fragment) {
        return with(fragment.getFragmentManager());
    }

    public static PermissionHelper with(FragmentManager fm) {
        return new PermissionHelper(fm);
    }


    public Request permissions(String... permissions) {
        if (permissions != null && permissions.length > 0) {
            return new RequestImpl(mFragmentManager, permissions);
        }
        throw new IllegalArgumentException("Require at least one permission");
    }
}
