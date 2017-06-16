package cn.leeii.simple.di;

import javax.inject.Singleton;

import cn.leeii.libmvp.base.AbstractApplication;
import cn.leeii.libmvp.di.module.ApplicationModule;
import cn.leeii.libmvp.di.module.RequestModule;
import cn.leeii.libmvp.di.module.RxModule;
import cn.leeii.simple.APIService;
import cn.leeii.simple.CacheService;
import dagger.Component;

/**
 * Created by Lee on 2016/12/23.
 */
@Singleton
@Component(modules = {RequestModule.class, ApplicationModule.class, CommonModule.class, RxModule.class})
public interface BaseComponent {

    AbstractApplication application();

    APIService api();

    CacheService cache();
}
