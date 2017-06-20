package cn.leeii.simple.ui.main;

import com.leeiidesu.libmvp.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * _ MainPresenter _ Created by dgg on 2017/6/19.
 */

public class MainPresenter extends BasePresenter<MainContract.IMainView, MainContract.IMainModel> {
    @Inject
    MainPresenter(MainContract.IMainView mView, MainContract.IMainModel iModel) {
        super(mView, iModel);
    }
}
