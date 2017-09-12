package cn.leeii.simple.ui.main;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import com.leeiidesu.libcore.android.Logger;
import com.leeiidesu.permission.PermissionHelper;
import com.leeiidesu.permission.callback.OnPermissionResultListener;

import java.util.ArrayList;

import butterknife.OnClick;
import cn.leeii.simple.R;
import cn.leeii.simple.base.BaseFragment;
import cn.leeii.simple.di.component.BaseComponent;
import cn.leeii.simple.ui.img.ImageLoaderTestActivity;

/**
 * _ MainFragment _ Created by dgg on 2017/6/19.
 */

public class MainFragment extends BaseFragment<Main2Activity, MainPresenter> implements MainContract.IMainView, OnPermissionResultListener {

    @Override
    protected void setupComponent(BaseComponent baseComponent) {

    }

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_bind;
    }

    @Override
    protected void trySetupData(Bundle savedInstanceState) {

    }

    @OnClick({R.id.testToast, R.id.testApi, R.id.testPermission, R.id.testImageLoader})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.testToast:
                showToast("Hello , boy.");
                break;
            case R.id.testApi:
                mPresenter.testApi("yuantong", "11111111111");
                break;
            case R.id.testPermission:
                PermissionHelper.with(this)
                        .permissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .showOnRationale("Rationale", "C", "Q")
                        .showOnDenied("Denied", "C", "Q")
                        .listener(this)
                        .request();
                break;
            case R.id.testImageLoader:
                startActivity(ImageLoaderTestActivity.class, false);
                break;
        }
    }

    @Override
    public void onGranted() {
        Logger.e("onGranted");
    }

    @Override
    public void onFailed(ArrayList<String> deniedPermissions) {
        Logger.e("onFailed");
    }
}
