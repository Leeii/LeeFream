package cn.leeii.simple.ui.main.di;

import com.leeiidesu.libmvp.base.AbstractApplication;
import com.leeiidesu.libmvp.di.scope.ActivityScope;

import cn.leeii.simple.APIService;
import cn.leeii.simple.ui.main.MainContract;
import cn.leeii.simple.ui.main.MainModel;
import dagger.Module;
import dagger.Provides;

/**
 * _ MainModule _ Created by dgg on 2017/6/19.
 */
@Module
public class MainModule {
    private MainContract.IMainView view;

    public MainModule(MainContract.IMainView view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MainContract.IMainView providerMainView() {
        return view;
    }

    @ActivityScope
    @Provides
    MainContract.IMainModel providerMainModel(AbstractApplication application, APIService service) {
        return new MainModel(application, service);
    }
}
