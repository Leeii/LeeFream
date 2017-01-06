package cn.leeii.lib.base;

import android.app.Application;
import android.widget.Toast;

import cn.leeii.lib.di.module.RequestModule;
import cn.leeii.lib.utils.LogUtil;
import cn.leeii.lib.utils.PreferenceHelper;
import okhttp3.Interceptor;

/**
 * Created by Lee on 2016/12/12.
 */

public abstract class AbstractApplication extends Application {

    private RequestModule mRequestModule;

    private Toast mToast;


    @Override
    public void onCreate() {
        super.onCreate();
        mRequestModule = new RequestModule(getBaseUrl(), getInterceptors());
        PreferenceHelper.init(this);
        LogUtil.IS_DEBUG = isDebug();
    }

    public RequestModule getRequestModule() {
        return mRequestModule;
    }

    protected Interceptor[] getInterceptors() {
        return null;
    }

    protected abstract String getBaseUrl();

    protected abstract boolean isDebug();

    public void Tips(String msg) {
        if (mToast != null) mToast.cancel();
        mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public void Tips(int msg) {
        if (mToast != null) mToast.cancel();
        mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public void TipsLong(String msg) {
        if (mToast != null) mToast.cancel();
        mToast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        mToast.show();
    }

    public void TipsLong(int msg) {
        if (mToast != null) mToast.cancel();
        mToast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        mToast.show();
    }
}
