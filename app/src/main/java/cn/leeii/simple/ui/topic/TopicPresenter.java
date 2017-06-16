package cn.leeii.simple.ui.topic;

import javax.inject.Inject;

import cn.leeii.libmvp.mvp.BasePresenter;
import cn.leeii.simple.APIService;
import cn.leeii.simple.CacheService;

/**
 * TopicPresenter 逻辑处理.
 * Created by Lee on 2017/01/05 16:46
 */
public class TopicPresenter extends BasePresenter<TopicContract.ITopicView, APIService, CacheService> {
    @Inject
    TopicPresenter(TopicContract.ITopicView mView, APIService apiService, CacheService cacheService) {
        super(mView, apiService, cacheService);
    }

}
