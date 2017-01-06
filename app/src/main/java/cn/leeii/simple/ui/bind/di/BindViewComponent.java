package cn.leeii.simple.ui.bind.di;

import cn.leeii.lib.di.scope.ActivityScope;
import cn.leeii.simple.di.BaseComponent;
import cn.leeii.simple.ui.bind.BindViewActivity;
import dagger.Component;

/**
 * Created by Lee on 2016/12/23.
 */
@ActivityScope
@Component(modules = {BindViewModule.class}, dependencies = BaseComponent.class)
public interface BindViewComponent {
    void inject(BindViewActivity activity);
}
