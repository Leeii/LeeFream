package cn.leeii.simple.ui.ref.di;


import com.leeiidesu.libmvp.base.AbstractApplication;
import com.leeiidesu.libmvp.di.scope.ActivityScope;

import cn.leeii.simple.APIService;
import cn.leeii.simple.ui.ref.RefreshTestContract;
import cn.leeii.simple.ui.ref.RefreshTestModel;
import dagger.Module;
import dagger.Provides;


/**
 * Created by leeiidesu on 2017/6/30.
 */
@Module
public class RefreshTestModule {
    private RefreshTestContract.IRefreshTestView view;

    public RefreshTestModule(RefreshTestContract.IRefreshTestView view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    RefreshTestContract.IRefreshTestView providerRefreshTestView() {
        return view;
    }

    @ActivityScope
    @Provides
    RefreshTestContract.IRefreshTestModel providerRefreshTestModel(AbstractApplication application, APIService service) {
        return new RefreshTestModel(application, service);
    }
}