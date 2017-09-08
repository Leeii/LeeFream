package com.leeiidesu.lib.permission;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.leeiidesu.lib.permission.listener.OnPermissionResultListener;

/**
 * RequestImpl Created by leeiidesu on 2017/8/28.
 */

class RequestImpl implements Request {
    private static final String FRAGMENT_TAG = "PERMISSION_FRAGMENT__";
    private final FragmentManager mFragmentManager;
    private final String[] permissions;
    private Config config = null;
    private OnPermissionResultListener listener;

    RequestImpl(FragmentManager mFragmentManager, String[] permissions) {
        this.mFragmentManager = mFragmentManager;
        this.permissions = permissions;
    }

    @Override
    public Request showOnRationale(String rationale) {
        return showOnRationale(rationale, "取消", "我知道了");
    }

    @Override
    public Request showOnRationale(String rationale, String cancelText, String confirmText) {
        assertConfig();

        config.rationaleText = rationale;
        config.rationaleCancelText = cancelText;
        config.rationaleConfirmText = confirmText;
        return this;
    }

    @Override
    public Request showOnDenied(String denied) {
        return showOnDenied(denied, "取消", "去设置");
    }


    @Override
    public Request showOnDenied(String denied, String cancelText, String confirmText) {
        assertConfig();
        config.deniedText = denied;
        config.deniedCancelText = cancelText;
        config.deniedConfirmText = confirmText;
        return this;
    }

    @Override
    public Request listener(OnPermissionResultListener l) {
        this.listener = l;
        return this;
    }

    private synchronized void assertConfig() {
        if (config == null) {
            config = new Config();
        }
    }

    @Override
    public void request() {
        request(mFragmentManager, permissions, config, listener);
    }

    private void request(@NonNull FragmentManager fm,
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
            fragment.setOnPermissionResultListener(l);
            final FragmentTransaction transaction = fm.beginTransaction();
            transaction.attach(fragment);
            transaction.commit();
        } else {
            fragment.setOnPermissionResultListener(l);
            fragment.requestPermissions(permissions, config);
        }
    }
}
