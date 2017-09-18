package cn.leeii.simple.ui.test;

import com.leeiidesu.libmvp.mvp.IContract;

/**
 * TestContract Created by leeiidesu on 2017/9/18.
 */
public interface TestContract {
    interface ITestView extends IContract.IView<TestPresenter> {

    }

    interface ITestModel extends IContract.IModel {

    }
}
