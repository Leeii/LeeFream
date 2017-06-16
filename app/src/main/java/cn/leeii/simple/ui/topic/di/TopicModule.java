package cn.leeii.simple.ui.topic.di;

import cn.leeii.libmvp.di.scope.ActivityScope;
import cn.leeii.simple.ui.topic.TopicContract;
import dagger.Module;
import dagger.Provides;

/**
 * 依赖的Module Created by Lee on 2017/01/05 16:46
 */
@Module
public class TopicModule {
    private TopicContract.ITopicView mView;

    public TopicModule(TopicContract.ITopicView mView) {
        this.mView = mView;
    }

    @Provides
    @ActivityScope
    public TopicContract.ITopicView providerView() {
        return mView;
    }
}
