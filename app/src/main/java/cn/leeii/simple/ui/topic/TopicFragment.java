package cn.leeii.simple.ui.topic;

import android.os.Bundle;

import cn.leeii.simple.R;
import cn.leeii.simple.base.BaseFragment;

/**
 * TopicFragment  内容页面
 * Created by Lee on 2017/01/05 16:46
 */

public class TopicFragment extends BaseFragment<TopicActivity, TopicPresenter> implements TopicContract.ITopicView {

    @Override
    protected void trySetupData(Bundle savedInstanceState) {
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_topic;
    }
}
