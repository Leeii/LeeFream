package com.leeiidesu.libmvp.base;

import android.app.Application;

import com.leeiidesu.libcore.android.Logger;
import com.leeiidesu.libcore.android.PreferencesHelper;
import com.leeiidesu.libcore.android.Toaster;
import com.leeiidesu.libmvp.di.module.ApplicationModule;
import com.leeiidesu.libmvp.di.module.RequestModule;
import com.leeiidesu.libmvp.di.module.RxModule;

import javax.inject.Inject;

import okhttp3.Interceptor;

/**
 * _ AbstractApplication _ Created by dgg on 2017/6/19.
 */

public abstract class AbstractApplication extends Application {
    @Inject
    Toaster mToaster;

    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesHelper.init(this);
        Logger.isPrint = isDebug();
    }

    /**
     * 是否是debug模式
     */
    protected abstract boolean isDebug();

    /**
     * 请求拦截器
     */
    protected abstract Interceptor[] getInterceptors();


    /**
     * Toast提示
     *
     * @param msg 信息
     */
    public void showToast(String msg) {
        mToaster.showSingletonToast(msg);
    }

    /**
     * Toast提示
     *
     * @param msg 信息
     */
    public void showToast(int msg) {
        mToaster.showSingletonToast(msg);
    }

    public RxModule getRxModule() {
        return new RxModule();
    }

    public RequestModule getRequestModule() {
        return new RequestModule(getInterceptors());
    }

    public ApplicationModule getApplicationModule() {
        return new ApplicationModule(this);
    }
}
