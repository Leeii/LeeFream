package cn.leeii.libmvp.di.module;

import java.io.File;

import javax.inject.Singleton;

import cn.leeii.libmvp.base.AbstractApplication;
import cn.leeii.libmvp.utils.FileHelper;
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
        return FileHelper.getCacheFile(application);
    }
}
