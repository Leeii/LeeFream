package cn.leeii.simple.ui.test;

import com.leeiidesu.libmvp.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * _ TestPluginPresenter _ Created by dgg on 2017/6/20.
 */
public class TestPluginPresenter extends BasePresenter<TestPluginContract.ITestPluginView, TestPluginContract.ITestPluginModel> {
    @Inject
    TestPluginPresenter(TestPluginContract.ITestPluginView mView, TestPluginContract.ITestPluginModel iModel) {
        super(mView, iModel);
    }
}
