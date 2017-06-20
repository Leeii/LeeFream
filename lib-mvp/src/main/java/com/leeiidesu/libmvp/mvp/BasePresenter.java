package com.leeiidesu.libmvp.mvp;

import javax.inject.Inject;

/**
 * Presenter 也要按照基本法  +1s  Created by Lee on 2016/12/26.
 */
public abstract class BasePresenter<V extends IContract.IView, MODEL> implements IContract.IPresenter {
    protected V mView;
    protected MODEL mModel;


    public BasePresenter(V mView, MODEL model) {
        this.mView = mView;
        mModel = model;
    }

    @SuppressWarnings("unchecked")
    @Inject
    protected void setupListener() {
        mView.setPresenter(this);
    }
}
