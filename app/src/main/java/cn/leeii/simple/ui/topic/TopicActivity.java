package cn.leeii.simple.ui.topic;

import android.os.Bundle;

import cn.leeii.simple.R;
import cn.leeii.simple.base.BaseActivity;
import cn.leeii.simple.di.BaseComponent;
import cn.leeii.simple.ui.topic.di.DaggerTopicComponent;
import cn.leeii.simple.ui.topic.di.TopicModule;

/**
 * TopicActivity 页面
 * Created by Lee on 2017/01/05 16:46
 */

public class TopicActivity extends BaseActivity<TopicPresenter> {
    @Override
    protected void setupComponent(BaseComponent baseComponent) {
        TopicFragment fragment =
                (TopicFragment) getSupportFragmentManager().findFragmentById(R.id.content);
        if (fragment == null) {
            // Create the fragment
            fragment = new TopicFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content, fragment)
                    .commit();
        }

        DaggerTopicComponent
                .builder()
                .baseComponent(baseComponent)
                .topicModule(new TopicModule(fragment))
                .build()
                .inject(this);
    }

    @Override
    protected int getContentViewResId() { return R.layout.activity_topic; }

    @Override
    protected void trySetupData(Bundle savedInstanceState) {

    }
}
