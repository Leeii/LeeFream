package cn.leeii.simple.base;

import cn.leeii.libmvp.base.AbstractActivity;
import cn.leeii.libmvp.mvp.BasePresenter;
import cn.leeii.simple.Simple;
import cn.leeii.simple.di.BaseComponent;

/**
 * Created by Lee on 2016/12/2
 */

public abstract class BaseActivity<T extends BasePresenter> extends AbstractActivity<T> {
    protected Simple mApplication;

    @Override
    protected void componentInject() {
        mApplication = (Simple) getApplication();
        setupComponent(mApplication.getBaseComponent());
    }

    protected abstract void setupComponent(BaseComponent baseComponent);
}
