package cn.leeii.libmvp.di.module;

import javax.inject.Singleton;

import cn.leeii.libmvp.base.AbstractApplication;
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
}
