package cn.leeii.libmvp.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.leeii.libmvp.mvp.IContract;
import cn.leeii.libmvp.receiver.LeeReceiver;
import cn.leeii.libmvp.widget.LoadingDialog;

/**
 * AbstractFragment Created by Lee on 2017/1/3.
 */

public abstract class AbstractFragment<A extends AbstractActivity, P extends IContract.IPresenter> extends Fragment
        implements IContract.IView<P> {


    /**
     * 应用内广播
     */
    private LeeReceiver mLeeReceiver;

    /**
     * 应用内拦截器
     */
    private IntentFilter mFilter;

    /**
     * 本地广播
     */
    public LocalBroadcastManager mLocalBroad;


    protected P mPresenter;

    //Activity
    protected A mActivity;

    private LoadingDialog mLoadingDialog;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(getContentViewResId(), container, false);
        unbinder = ButterKnife.bind(this, contentView);
        return contentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

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
        trySetupData(savedInstanceState);

        // 判断是否有广播
        if (mLocalBroad != null && mLeeReceiver != null && mFilter != null)
            mLocalBroad.registerReceiver(mLeeReceiver, mFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLeeReceiver != null && mLocalBroad != null) {
            mLocalBroad.unregisterReceiver(mLeeReceiver);

        }
        mLeeReceiver = null;
        mLocalBroad = null;
        mFilter = null;
        mPresenter = null;
    }

    protected abstract void trySetupData(Bundle savedInstanceState);

    protected abstract int getContentViewResId();

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

    @Override
    public void showLoading() {
        if (mLoadingDialog == null) mLoadingDialog = new LoadingDialog(context());
        if (!mLoadingDialog.isShowing())
            mLoadingDialog.show();
    }

    @Override
    public void dismissLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) mLoadingDialog.dismiss();
    }

    @Override
    public void Tips(String msg) {
        mActivity.Tips(msg);
    }

    @Override
    public void TipsError(String msg) {
        mActivity.TipsError(msg);
    }

    @Override
    public void startActivity(Class clazz, boolean finish) {
        startActivity(null, clazz, finish);
    }

    @Override
    public void startActivity(Intent intent, Class clazz, boolean finish) {
        mActivity.startActivity(intent, clazz, finish);
    }

    @Override
    public void startActivityForResult(int requestCode, Class clazz) {
        startActivityForResult(requestCode, null, clazz);
    }

    @Override
    public void startActivityForResult(int requestCode, Intent intent, Class clazz) {
        mActivity.startActivityForResult(requestCode, intent, clazz);
    }

    @Override
    public void startActivity(Class activityClass, boolean finish, Bundle option) {
        startActivity(null, activityClass, finish, option);
    }

    @Override
    public void startActivity(Intent intent, Class activityClass, boolean finish, Bundle option) {
        mActivity.startActivity(intent, activityClass, finish, option);
    }

    @Override
    public void startActivityForResult(int requestCode, Class clazz, Bundle option) {
        startActivityForResult(requestCode, null, clazz, option);
    }

    @Override
    public void startActivityForResult(int requestCode, Intent intent, Class clazz, Bundle option) {
        mActivity.startActivityForResult(requestCode, intent, clazz, option);
    }

    @Override
    public void setLoadingDialogCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        if (mLoadingDialog != null) mLoadingDialog.setOnCancelListener(onCancelListener);
    }
}
