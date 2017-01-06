package cn.leeii.simple.ui.bind.di;

import cn.leeii.lib.di.scope.ActivityScope;
import cn.leeii.simple.ui.bind.BinderContract;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Lee on 2016/12/23.
 */
@Module
public class BindViewModule {

    private BinderContract.IBinderView mView;

    public BindViewModule(BinderContract.IBinderView mView) {
        this.mView = mView;
    }

    @Provides
    String providerString() {
        return "H哈哈哈";
    }

    @ActivityScope
    @Provides
    BinderContract.IBinderView providerView() {
        return mView;
    }
}