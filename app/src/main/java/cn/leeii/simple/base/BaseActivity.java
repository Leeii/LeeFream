package cn.leeii.simple.base;

import android.app.Dialog;

import com.leeiidesu.libmvp.base.AbstractActivity;
import com.leeiidesu.libmvp.mvp.BasePresenter;
import com.leeiidesu.lib.widget.loading.LoadingDialog;

import cn.leeii.simple.Simple;
import cn.leeii.simple.di.component.BaseComponent;

/**
 * _ BaseActivity _ Created by dgg on 2017/6/19.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AbstractActivity<P> {
    private Dialog mLoadingDialog;


    @Override
    protected void componentInject() {
        Simple simple = (Simple) mApplication;

        setupComponent(simple.getBaseComponent());
    }

    protected abstract void setupComponent(BaseComponent baseComponent);

    @Override
    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
    }

    @Override
    public void dismissLoading() {
        if (mLoadingDialog != null) mLoadingDialog.dismiss();
    }
}
