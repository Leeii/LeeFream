package cn.leeii.simple;


import com.leeiidesu.libmvp.base.AbstractApplication;

import cn.leeii.simple.di.component.BaseComponent;
import cn.leeii.simple.di.component.DaggerBaseComponent;
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
                .applicationModule(getApplicationModule())
                .requestModule(getRequestModule())
                .rxModule(getRxModule())
                .build();

        mBaseComponent.inject(this);
    }

    @Override
    protected boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    public BaseComponent getBaseComponent() {
        return mBaseComponent;
    }

    @Override
    protected String getBaseUrl() {
        return API.BASE_URL;
    }

    @Override
    protected Interceptor[] getInterceptors() {
        return new Interceptor[0];
    }
}
