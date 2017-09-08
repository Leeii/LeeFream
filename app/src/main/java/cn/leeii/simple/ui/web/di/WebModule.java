package cn.leeii.simple.ui.web.di;


import com.leeiidesu.libmvp.base.AbstractApplication;
import com.leeiidesu.libmvp.di.scope.ActivityScope;

import cn.leeii.simple.ui.web.WebContract;
import cn.leeii.simple.ui.web.WebModel;
import dagger.Module;
import dagger.Provides;
import cn.leeii.simple.APIService;


/**
 * WebModule Created by leeiidesu on 2017/8/9.
 */
@Module
public class WebModule {
    private WebContract.IWebView view;

    public WebModule(WebContract.IWebView view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    WebContract.IWebView providerWebView() {
        return view;
    }

    @ActivityScope
    @Provides
    WebContract.IWebModel providerWebModel(AbstractApplication application, APIService service) {
        return new WebModel(application, service);
    }
}