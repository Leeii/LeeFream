package cn.leeii.simple.base;

import android.app.Dialog;

import com.leeiidesu.libmvp.base.AbstractFragment;
import com.leeiidesu.libmvp.mvp.BasePresenter;
import com.leeiidesu.lib.widget.loading.LoadingDialog;

import cn.leeii.simple.Simple;
import cn.leeii.simple.di.component.BaseComponent;

/**
 * _ BaseFragment _ Created by dgg on 2017/6/19.
 */

public abstract class BaseFragment<A extends BaseActivity, P extends BasePresenter>
        extends AbstractFragment<A, P> {

    private Dialog mLoadingDialog;

    @Override
    protected void componentInject() {
        Simple simple = (Simple) mActivity.getApplication();

        setupComponent(simple.getBaseComponent());
    }

    protected void setupComponent(BaseComponent baseComponent) {

    }


    public void setLoadingDialog(Dialog mLoadingDialog) {
        this.mLoadingDialog = mLoadingDialog;
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(mActivity);
        }
        mLoadingDialog.show();
    }

    @Override
    public void dismissLoading() {
        if (mLoadingDialog != null) mLoadingDialog.dismiss();
    }
}
