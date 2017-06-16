package cn.leeii.libmvp.mvp;

import javax.inject.Inject;

/**
 * Presenter 也要按照基本法  +1s  Created by Lee on 2016/12/26.
 */
public abstract class BasePresenter<V extends IContract.IView, API> implements IContract.IPresenter {
    protected V mView;
    protected API mApiService;


    public BasePresenter(V mView, API api) {
        this.mView = mView;
        mApiService = api;

    }

    @SuppressWarnings("unchecked")
    @Inject
    protected void setupListener() {
        mView.setPresenter(this);
    }
}
