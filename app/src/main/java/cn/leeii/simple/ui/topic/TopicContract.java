package cn.leeii.simple.ui.topic;

import cn.leeii.lib.mvp.IContract;

/**
 * Topic契约 Presenter 与 View 的方法接口.
 * TopicContract  Created by Lee on 2017/01/05 16:46
 */
public interface TopicContract {
    interface ITopicView extends IContract.IView<TopicPresenter> {
    }
}
