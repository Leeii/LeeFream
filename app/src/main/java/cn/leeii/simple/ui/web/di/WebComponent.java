package cn.leeii.simple.ui.web.di;


import com.leeiidesu.libmvp.di.scope.ActivityScope;

import cn.leeii.simple.di.component.BaseComponent;
import cn.leeii.simple.ui.web.WebActivity;
import dagger.Component;


/**
 * WebComponent Created by leeiidesu on 2017/8/9.
 */
@ActivityScope
@Component(modules = WebModule.class, dependencies = BaseComponent.class)
public interface WebComponent {
    void inject(WebActivity activity);
}
