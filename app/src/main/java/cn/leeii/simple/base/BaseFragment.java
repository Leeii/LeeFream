package cn.leeii.simple.base;

import com.leeiidesu.libmvp.base.AbstractFragment;
import com.leeiidesu.libmvp.mvp.BasePresenter;

import cn.leeii.simple.Simple;
import cn.leeii.simple.di.component.BaseComponent;

/**
 * _ BaseFragment _ Created by dgg on 2017/6/19.
 */

public abstract class BaseFragment<A extends BaseActivity, P extends BasePresenter>
        extends AbstractFragment<A, P> {
    @Override
    protected void componentInject() {
        Simple simple = (Simple) mActivity.getApplication();

        setupComponent(simple.getBaseComponent());
    }

    protected void setupComponent(BaseComponent baseComponent) {

    }
}
