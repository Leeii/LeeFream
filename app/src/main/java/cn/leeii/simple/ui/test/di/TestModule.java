package cn.leeii.simple.ui.test.di;


import com.leeiidesu.libmvp.base.AbstractApplication;
import com.leeiidesu.libmvp.di.scope.ActivityScope;

import cn.leeii.simple.ui.test.TestContract;
import cn.leeii.simple.ui.test.TestModel;
import dagger.Module;
import dagger.Provides;
import cn.leeii.simple.APIService;


/**
 * TestModule Created by leeiidesu on 2017/9/18.
 */
@Module
public class TestModule {
    private TestContract.ITestView view;

    public TestModule(TestContract.ITestView view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    TestContract.ITestView providerTestView() {
        return view;
    }

    @ActivityScope
    @Provides
    TestContract.ITestModel providerTestModel(AbstractApplication application, APIService service) {
        return new TestModel(application, service);
    }
}