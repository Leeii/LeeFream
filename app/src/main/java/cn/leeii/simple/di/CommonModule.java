package cn.leeii.simple.di;

import javax.inject.Singleton;

import cn.leeii.simple.APIService;
import cn.leeii.simple.CacheService;
import dagger.Module;
import dagger.Provides;
import io.rx_cache.internal.RxCache;
import retrofit2.Retrofit;

/**
 * Created by Lee on 2016/12/23.
 */
@Module
public class CommonModule {


    @Singleton
    @Provides
    APIService providerApi(Retrofit retrofit) {
        return retrofit.create(APIService.class);
    }

    @Singleton
    @Provides
    CacheService providerCache(RxCache rxCache) {
        return rxCache.using(CacheService.class);
    }
}
