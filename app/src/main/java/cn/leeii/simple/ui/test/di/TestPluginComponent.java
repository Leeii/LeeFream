package cn.leeii.simple.ui.test.di;


import com.leeiidesu.libmvp.di.scope.ActivityScope;

import cn.leeii.simple.di.component.BaseComponent;
import cn.leeii.simple.ui.test.TestPluginActivity;
import dagger.Component;


/**
 * _ TestPluginComponent _ Created by dgg on 2017/6/20.
 */
@ActivityScope
@Component(modules = TestPluginModule.class, dependencies = BaseComponent.class)
public interface TestPluginComponent {
    void inject(TestPluginActivity activity);
}
