package cn.leeii.simple.ui.test.di;


import com.leeiidesu.libmvp.base.AbstractApplication;
import com.leeiidesu.libmvp.di.scope.ActivityScope;

import cn.leeii.simple.APIService;
import cn.leeii.simple.ui.test.TestPluginContract;
import cn.leeii.simple.ui.test.TestPluginModel;
import dagger.Module;
import dagger.Provides;


/**
 * _ TestPluginModule _ Created by dgg on 2017/6/20.
 */
@Module
public class TestPluginModule {
    private TestPluginContract.ITestPluginView view;

    public TestPluginModule(TestPluginContract.ITestPluginView view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    TestPluginContract.ITestPluginView providerTestPluginView() {
        return view;
    }

    @ActivityScope
    @Provides
    TestPluginContract.ITestPluginModel providerTestPluginModel(AbstractApplication application, APIService service) {
        return new TestPluginModel(application, service);
    }
}