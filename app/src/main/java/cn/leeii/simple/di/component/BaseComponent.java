package cn.leeii.simple.di.component;

import com.leeiidesu.libmvp.base.AbstractApplication;
import com.leeiidesu.libmvp.di.module.ApplicationModule;
import com.leeiidesu.libmvp.di.module.RequestModule;
import com.leeiidesu.libmvp.di.module.RxModule;

import javax.inject.Singleton;

import cn.leeii.simple.APIService;
import cn.leeii.simple.Simple;
import cn.leeii.simple.di.module.APIModule;
import dagger.Component;

/**
 * _ BaseComponent _ Created by dgg on 2017/6/19.
 */
@Singleton
@Component(modules = {ApplicationModule.class, RequestModule.class, RxModule.class, APIModule.class})
public interface BaseComponent {
    AbstractApplication application();

    APIService api();

    void inject(Simple simple);
}
