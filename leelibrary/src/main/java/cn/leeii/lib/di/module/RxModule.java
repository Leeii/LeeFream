package cn.leeii.lib.di.module;

import java.io.File;

import javax.inject.Singleton;

import cn.leeii.lib.base.AbstractApplication;
import cn.leeii.lib.utils.FileHelper;
import dagger.Module;
import dagger.Provides;
import io.rx_cache.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

/**
 * Created by Lee on 2017/1/4.
 */
@Module
public class RxModule {
    /**
     * 提供RXCache客户端
     *
     * @param cacheDir 缓存路径
     * @return RxCache
     */
    @Singleton
    @Provides
    RxCache provideRxCache(File cacheDir) {
        return new RxCache
                .Builder()
                .persistence(cacheDir, new GsonSpeaker());
    }

    /**
     * 提供缓存地址
     */
    @Singleton
    @Provides
    File provideCacheFile(AbstractApplication application) {
        return FileHelper.getCacheFile(application);
    }

}
