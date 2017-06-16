package cn.leeii.simple.ui.topic.di;

import cn.leeii.libmvp.di.scope.ActivityScope;
import cn.leeii.simple.di.BaseComponent;
import cn.leeii.simple.ui.topic.TopicActivity;
import dagger.Component;

/**
 * 依赖注入component Created by Lee on 2017/01/05 16:46
 */
@ActivityScope
@Component(dependencies = BaseComponent.class, modules = {TopicModule.class})
public interface TopicComponent {
    void inject(TopicActivity activity);
}
