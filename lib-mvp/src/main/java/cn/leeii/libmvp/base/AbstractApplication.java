package cn.leeii.libmvp.base;

import android.app.Application;

import com.leeiidesu.libcommon.android.Toaster;

import cn.leeii.libmvp.di.module.RequestModule;
import cn.leeii.libmvp.utils.LogUtil;
import cn.leeii.libmvp.utils.PreferenceHelper;
import okhttp3.Interceptor;

/**
 * Created by Lee on 2016/12/12.
 */

public abstract class AbstractApplication extends Application {

    private RequestModule mRequestModule;



    @Override
    public void onCreate() {
        super.onCreate();
        mRequestModule = new RequestModule(getBaseUrl(), getInterceptors());
        PreferenceHelper.init(this);
        LogUtil.IS_DEBUG = isDebug();

        Toaster toaster = new Toaster(this);
    }

    public RequestModule getRequestModule() {
        return mRequestModule;
    }

    protected Interceptor[] getInterceptors() {
        return null;
    }

    protected abstract String getBaseUrl();

    protected abstract boolean isDebug();
}
