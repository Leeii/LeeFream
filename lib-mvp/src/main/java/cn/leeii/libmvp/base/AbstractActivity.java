package cn.leeii.libmvp.base;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.leeii.libmvp.model.Action;
import cn.leeii.libmvp.mvp.BasePresenter;
import cn.leeii.libmvp.receiver.ReceiveResult;

/**
 * AbstractActivity Created by Lee on 2016/12/10.
 */
public abstract class AbstractActivity<T extends BasePresenter> extends AppCompatActivity implements ReceiveResult {


    private AbstractApplication mApplication;

    private Unbinder unbinder;

    @Inject
    protected T mPresenter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mApplication = null;
        mPresenter = null;
        unbinder.unbind();
        unbinder = null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectViewBefore(savedInstanceState);
        setContentView(getContentViewResId());
        unbinder = ButterKnife.bind(this);

        mApplication = (AbstractApplication) getApplication();

        componentInject();
        trySetupData(savedInstanceState);
    }

    protected abstract void componentInject();

    protected abstract int getContentViewResId();

    /**
     * 加载view之前
     *
     * @param savedInstanceState bundle
     */
    protected void injectViewBefore(Bundle savedInstanceState) {

    }

    /**
     * View 加载之后 初始化数据
     *
     * @param savedInstanceState bundle
     */
    protected abstract void trySetupData(Bundle savedInstanceState);


    /**
     * 显示一个toast
     *
     * @param msg
     */
    public void Tips(String msg) {
        mApplication.Tips(msg);
    }

    public void TipsError(String msg) {
        mApplication.Tips(msg);
    }


    public void startActivity(Class clazz, boolean finish) {
        startActivity(null, clazz, finish);
    }

    public void startActivity(Intent intent, Class clazz, boolean finish) {
        if (intent == null)
            intent = new Intent(this, clazz);
        else
            intent.setClass(this, clazz);
        startActivity(intent);
        if (finish)
            finish();
    }

    public void startActivityForResult(int requestCode, Class clazz) {
        startActivityForResult(requestCode, null, clazz);
    }

    public void startActivityForResult(int requestCode, Intent intent, Class clazz) {
        if (intent == null)
            intent = new Intent(this, clazz);
        else
            intent.setClass(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    @TargetApi(16)
    public void startActivity(Class clazz, boolean b, Bundle option) {
        startActivity(null, clazz, b, option);
    }

    @TargetApi(16)
    public void startActivity(Intent intent, Class clazz, boolean isFinish, Bundle option) {
        if (intent == null)
            intent = new Intent(this, clazz);
        else
            intent.setClass(this, clazz);
        startActivity(intent, option);
        if (isFinish)
            finish();
    }

    public void startActivityForResult(int requestCode, Class clazz, Bundle option) {
        startActivityForResult(requestCode, null, clazz, option);
    }

    @TargetApi(16)
    public void startActivityForResult(int requestCode, Intent intent, Class clazz, Bundle option) {
        if (intent == null)
            intent = new Intent(this, clazz);
        else
            intent.setClass(this, clazz);
        startActivityForResult(intent, requestCode, option);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) mPresenter.unSubscribe();
    }

    @Override
    public <RESULT extends Parcelable> void onReceive(Intent intent) {
        if (mPresenter != null) {
            String action = intent.getAction();
            RESULT result = intent.getParcelableExtra(Action.RESULT);
            mPresenter.dealReceive(action, result);
        }
    }

}
