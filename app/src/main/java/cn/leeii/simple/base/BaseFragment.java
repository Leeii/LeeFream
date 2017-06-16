package cn.leeii.simple.base;

import javax.inject.Inject;

import cn.leeii.libmvp.base.AbstractActivity;
import cn.leeii.libmvp.base.AbstractFragment;
import cn.leeii.libmvp.mvp.IContract;
import cn.leeii.simple.APIService;

/**
 * Created by Lee on 2017/1/3.
 */

public abstract class BaseFragment<A extends AbstractActivity,
        P extends IContract.IPresenter> extends AbstractFragment<A, P> {
    @Inject
    protected APIService api;
}
