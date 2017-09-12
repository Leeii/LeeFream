package com.leeiidesu.libmvp.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.leeiidesu.libmvp.mvp.BasePresenter;
import com.leeiidesu.libmvp.mvp.IContract;

import org.simple.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * _ AbstractActivity _ Created by dgg on 2017/6/19.
 */

public abstract class AbstractActivity<P extends BasePresenter> extends AppCompatActivity implements IContract.IView<P> {
    protected AbstractApplication mApplication;

    protected P mPresenter;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (AbstractApplication) getApplication();
        beforeInjectView();
        setContentView(getLayoutResId());
        mUnBinder = ButterKnife.bind(this);
        componentInject();
        trySetupData(savedInstanceState);
    }

    protected abstract void trySetupData(Bundle savedInstanceState);

    protected abstract void componentInject();

    protected abstract
    @LayoutRes
    int getLayoutResId();

    /**
     * 在setContentView之前
     */
    protected void beforeInjectView() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mApplication = null;
        mPresenter = null;
        mUnBinder.unbind();
        mUnBinder = null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (useEventBus())
            EventBus.getDefault().register(this);
    }

    protected boolean useEventBus() {
        return false;
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (useEventBus())
            EventBus.getDefault().unregister(this);
    }

    @Inject
    @Override
    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }

    @Override
    public Intent intent() {
        return getIntent();
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void showToast(String msg) {
        mApplication.showToast(msg);
    }

    @Override
    public void startActivity(Class clazz, boolean finish) {
        startActivity(null, clazz, finish);
    }

    @Override
    public void startActivity(Intent intent, Class clazz, boolean finish) {
        if (intent == null) {
            intent = new Intent(this, clazz);
        } else
            intent.setClass(this, clazz);
        startActivity(intent);
        if (finish)
            finish();
    }

    @Override
    public void startActivityForResult(int requestCode, Class clazz) {
        startActivityForResult(requestCode, null, clazz);
    }

    @Override
    public void startActivityForResult(int requestCode, Intent intent, Class clazz) {
        if (intent == null) {
            intent = new Intent(this, clazz);
        } else
            intent.setClass(this, clazz);

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
            intent = new Intent(this, clazz);
        else
            intent.setClass(this, clazz);
        startActivity(intent, option);
        if (finish)
            finish();
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
            intent = new Intent(this, clazz);
        else
            intent.setClass(this, clazz);
        startActivityForResult(intent, requestCode, option);
    }
}
