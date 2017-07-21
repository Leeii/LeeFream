package cn.leeii.simple.ui.ref.di;


import com.leeiidesu.libmvp.di.scope.ActivityScope;

import cn.leeii.simple.di.component.BaseComponent;
import cn.leeii.simple.ui.ref.RefreshTestActivity;
import dagger.Component;


/**
 * Created by leeiidesu on 2017/6/30.
 */
@ActivityScope
@Component(modules = RefreshTestModule.class, dependencies = BaseComponent.class)
public interface RefreshTestComponent {
    void inject(RefreshTestActivity activity);
}
