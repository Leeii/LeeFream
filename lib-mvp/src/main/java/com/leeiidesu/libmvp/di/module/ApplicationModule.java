package com.leeiidesu.libmvp.di.module;

import com.leeiidesu.libcommon.android.Toaster;
import com.leeiidesu.libmvp.base.AbstractApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lee on 2016/12/23.
 */
@Module
public class ApplicationModule {
    private AbstractApplication mApplication;

    public ApplicationModule(AbstractApplication mApplication) {
        this.mApplication = mApplication;
    }

    @Singleton
    @Provides
    public AbstractApplication providerApplication() {
        return mApplication;
    }


    @Singleton
    @Provides
    public Toaster providerToaster() {
        return new Toaster(mApplication);
    }
}
