package cn.leeii.simple.ui.test.di;


import com.leeiidesu.libmvp.di.scope.ActivityScope;

import cn.leeii.simple.di.component.BaseComponent;
import cn.leeii.simple.ui.test.TestActivity;
import dagger.Component;


/**
 * TestComponent Created by leeiidesu on 2017/9/18.
 */
@ActivityScope
@Component(modules = TestModule.class, dependencies = BaseComponent.class)
public interface TestComponent {
    void inject(TestActivity activity);
}
