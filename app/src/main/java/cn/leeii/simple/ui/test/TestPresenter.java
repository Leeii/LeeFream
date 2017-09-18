package cn.leeii.simple.ui.test;

import com.leeiidesu.libmvp.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * TestPresenter Created by leeiidesu on 2017/9/18.
 */
public class TestPresenter extends BasePresenter<TestContract.ITestView, TestContract.ITestModel> {
    @Inject
    TestPresenter(TestContract.ITestView mView, TestContract.ITestModel iModel) {
        super(mView, iModel);
    }
}
