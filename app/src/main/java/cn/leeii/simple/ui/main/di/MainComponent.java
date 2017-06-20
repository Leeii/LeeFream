package cn.leeii.simple.ui.main.di;

import com.leeiidesu.libmvp.di.scope.ActivityScope;

import cn.leeii.simple.di.component.BaseComponent;
import cn.leeii.simple.ui.main.Main2Activity;
import dagger.Component;

/**
 * _ MainComponent _ Created by dgg on 2017/6/19.
 */
@ActivityScope
@Component(modules = MainModule.class, dependencies = BaseComponent.class)
public interface MainComponent {
    void inject(Main2Activity activity);
}
