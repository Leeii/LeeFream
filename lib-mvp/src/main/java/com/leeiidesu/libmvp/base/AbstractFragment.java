package com.leeiidesu.libmvp.base;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leeiidesu.libmvp.mvp.BasePresenter;
import com.leeiidesu.libmvp.mvp.IContract;
import com.leeiidesu.libmvp.widget.LoadingDialog;

import org.simple.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * _ AbstractFragment _ Created by dgg on 2017/6/19.
 */

public abstract class AbstractFragment<A extends AbstractActivity, P extends BasePresenter> extends Fragment implements IContract.IView<P> {
    private Unbinder unbinder;

    protected P mPresenter;

    //Activity
    protected A mActivity;

    private Dialog mLoadingDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(getContentViewResId(), container, false);
        unbinder = ButterKnife.bind(this, contentView);
        return contentView;
    }

    protected abstract int getContentViewResId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        unbinder = null;
    }


    @Override
    @SuppressWarnings("unchecked")
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (A) getActivity();
        componentInject();
        trySetupData(savedInstanceState);
    }

    protected abstract void componentInject();

    protected abstract void trySetupData(Bundle savedInstanceState);

    @Override
    public void onStart() {
        super.onStart();
        if (useEventBus())
            EventBus.getDefault().register(this);
    }

    protected boolean useEventBus() {
        return false;
    }


    @Override
    public void onStop() {
        super.onStop();
        if (useEventBus())
            EventBus.getDefault().unregister(this);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }

    @Inject
    @Override
    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }

    @Override
    public Intent intent() {
        return mActivity.getIntent();
    }

    @Override
    public Context context() {
        return mActivity;
    }

    @Override
    public void finishActivity() {
        mActivity.finish();
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

    @Override
    public void Tips(String msg) {
        mActivity.Tips(msg);
    }

    @Override
    public void TipsError(String msg) {
        mActivity.Tips(msg);
    }

    @Override
    public void startActivity(Class clazz, boolean finish) {
        startActivity(null, clazz, finish);
    }

    @Override
    public void startActivity(Intent intent, Class clazz, boolean finish) {
        if (intent == null) {
            intent = new Intent(mActivity, clazz);
        } else
            intent.setClass(mActivity, clazz);
        startActivity(intent);
        if (finish)
            mActivity.finish();
    }

    @Override
    public void startActivityForResult(int requestCode, Class clazz) {
        startActivityForResult(requestCode, null, clazz);
    }

    @Override
    public void startActivityForResult(int requestCode, Intent intent, Class clazz) {
        if (intent == null) {
            intent = new Intent(mActivity, clazz);
        } else
            intent.setClass(mActivity, clazz);

        startActivityForResult(intent, requestCode);
    }

    @Override
    @TargetApi(16)
    public void startActivity(Class clazz, boolean finish, Bundle option) {
        startActivity(null, clazz, finish, option);
    }

    @Override
    @TargetApi(16)
    public void startActivity(Intent intent, Class clazz, boolean finish, Bundle option) {
        if (intent == null)
            intent = new Intent(mActivity, clazz);
        else
            intent.setClass(mActivity, clazz);
        startActivity(intent, option);
        if (finish)
            mActivity.finish();
    }

    @Override
    @TargetApi(16)
    public void startActivityForResult(int requestCode, Class clazz, Bundle option) {
        startActivityForResult(requestCode, null, clazz, option);
    }

    @Override
    @TargetApi(16)
    public void startActivityForResult(int requestCode, Intent intent, Class clazz, Bundle option) {
        if (intent == null)
            intent = new Intent(mActivity, clazz);
        else
            intent.setClass(mActivity, clazz);
        startActivityForResult(intent, requestCode, option);
    }
}
