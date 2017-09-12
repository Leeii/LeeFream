package com.leeiidesu.libmvp.di.module;

import com.leeiidesu.libmvp.tool.LogInterceptor;
import com.leeiidesu.libmvp.tool.converter.FastJsonConverterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Lee on 2016/12/23.
 */
@Module
public class RequestModule {

    private Interceptor[] mInterceptors;

    public RequestModule(Interceptor... mInterceptors) {
        this.mInterceptors = mInterceptors;
    }

    /**
     * @param intercept 拦截器
     */
    @Singleton
    @Provides
    OkHttpClient provideClient(Interceptor intercept, Cache cache) {
        final OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        return configureClient(okHttpClient, intercept, cache);
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient client, String httpUrl) {
        final Retrofit.Builder builder = new Retrofit.Builder();
        return configureRetrofit(builder, client, httpUrl);
    }

    @Singleton
    @Provides
    Interceptor provideIntercept() {
        return new LogInterceptor();//打印请求信息的拦截器
    }

    @Singleton
    @Provides
    Cache provideCache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1024 * 1024);//设置缓存路径和大小 10M
    }


    /**
     * 配置okHttpclient
     */
    private OkHttpClient configureClient(OkHttpClient.Builder okHttpClient,
                                         Interceptor intercept,
                                         Cache cache) {
        OkHttpClient.Builder builder = okHttpClient
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .cache(cache)
                .addNetworkInterceptor(intercept);
        if (mInterceptors != null && mInterceptors.length > 0) {
            //如果外部提供了interceptor的数组则遍历添加
            for (Interceptor interceptor : mInterceptors) {
                builder.addInterceptor(interceptor);
            }
        }
        return builder.build();
    }

    /**
     * 配置Retrofit
     */
    private Retrofit configureRetrofit(Retrofit.Builder builder, OkHttpClient client, String httpUrl) {
        return builder
                .baseUrl(httpUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用rxJava
                .addConverterFactory(FastJsonConverterFactory.create())//使用FastJson
                .build();
    }
}
