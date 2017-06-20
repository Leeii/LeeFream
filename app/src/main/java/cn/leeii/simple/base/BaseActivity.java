package cn.leeii.simple.base;

import com.leeiidesu.libmvp.base.AbstractActivity;
import com.leeiidesu.libmvp.mvp.BasePresenter;

import cn.leeii.simple.Simple;
import cn.leeii.simple.di.component.BaseComponent;

/**
 * _ BaseActivity _ Created by dgg on 2017/6/19.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AbstractActivity<P> {
    @Override
    protected void componentInject() {
        Simple simple = (Simple) mApplication;

        setupComponent(simple.getBaseComponent());
    }

    protected abstract void setupComponent(BaseComponent baseComponent);
}
