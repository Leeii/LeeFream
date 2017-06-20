package com.leeiidesu.libmvp.di.module;

import com.leeiidesu.libmvp.base.AbstractApplication;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lee on 2017/1/4.
 */
@Module
public class RxModule {
    /**
     * 提供缓存地址
     */
    @Singleton
    @Provides
    File provideCacheFile(AbstractApplication application) {
        return application.getCacheDir();
    }
}
