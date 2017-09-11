package cn.leeii.simple;


import com.leeiidesu.lib.common.imageloader.ImageConfiguration;
import com.leeiidesu.lib.common.imageloader.ImageLoader;
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


        ImageLoader.getInstance().init(
                new ImageConfiguration.Builder()
                        .placeholder(R.mipmap.loading2)
                        .errorholder(R.mipmap.faild)
                        .build()
        );
    }

    @Override
    protected boolean isDebug() {
        return true;
    }

    public BaseComponent getBaseComponent() {
        return mBaseComponent;
    }

    @Override
    protected Interceptor[] getInterceptors() {
        return new Interceptor[0];
    }
}
