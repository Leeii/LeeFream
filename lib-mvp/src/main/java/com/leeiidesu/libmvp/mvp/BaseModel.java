package com.leeiidesu.libmvp.mvp;

import javax.inject.Inject;

/**
 * _ BaseModel _ Created by dgg on 2017/6/19.
 */

public class BaseModel<APPLICATION, API> implements IContract.IModel {

    protected final APPLICATION mApplication;
    protected final API mApiService;

    @Inject
    public BaseModel(APPLICATION application, API api) {
        this.mApplication = application;
        this.mApiService = api;
    }
}
