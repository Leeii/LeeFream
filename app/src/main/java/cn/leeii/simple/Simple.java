package cn.leeii.simple;


import cn.leeii.lib.base.AbstractApplication;
import cn.leeii.lib.di.module.ApplicationModule;
import cn.leeii.simple.di.BaseComponent;
import cn.leeii.simple.di.DaggerBaseComponent;
import okhttp3.Interceptor;

/**
 * Created by Lee on 2016/12/13.
 */

public class Simple extends AbstractApplication {

    private BaseComponent mBaseComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        mBaseComponent = DaggerBaseComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .requestModule(getRequestModule())
                .build();
    }

    public BaseComponent getBaseComponent() {
        return mBaseComponent;
    }

    @Override
    protected String getBaseUrl() {
        return BuildConfig.API_HOST_PATH;
    }

    @Override
    protected boolean isDebug() {
        return true;
    }

    @Override
    protected Interceptor[] getInterceptors() {
        return new Interceptor[]{
                new TokenInterceptor()
        };
    }
}
