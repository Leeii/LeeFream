package cn.leeii.simple.di.module;

import javax.inject.Singleton;

import cn.leeii.simple.APIService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * _ APIModule _ Created by dgg on 2017/6/19.
 */
@Module
public class APIModule {
    @Singleton
    @Provides
    APIService providerAPIService(Retrofit retrofit) {
        return retrofit.create(APIService.class);
    }
}
