package cn.leeii.simple.ui.test;

import com.leeiidesu.libmvp.base.AbstractApplication;
import com.leeiidesu.libmvp.mvp.BaseModel;

import cn.leeii.simple.APIService;

/**
 * TestModel Created by leeiidesu on 2017/9/18.
 */
public class TestModel extends BaseModel<AbstractApplication, APIService> implements TestContract.ITestModel {
    public TestModel(AbstractApplication simple, APIService apiService) {
        super(simple, apiService);
    }
}
