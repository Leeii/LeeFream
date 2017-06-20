package cn.leeii.simple.ui.test;

import com.leeiidesu.libmvp.base.AbstractApplication;
import com.leeiidesu.libmvp.mvp.BaseModel;

import cn.leeii.simple.APIService;

/**
 * _ TestPluginModel _ Created by dgg on 2017/6/20.
 */
public class TestPluginModel extends BaseModel<AbstractApplication, APIService> implements TestPluginContract.ITestPluginModel {
    public TestPluginModel(AbstractApplication simple, APIService apiService) {
        super(simple, apiService);
    }
}
