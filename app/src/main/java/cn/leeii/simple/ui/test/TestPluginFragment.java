package cn.leeii.simple.ui.test;

import android.os.Bundle;

import cn.leeii.simple.R;
import cn.leeii.simple.base.BaseFragment;

/**
 * _ TestPluginFragment _ Created by dgg on 2017/6/20.
 */
public class TestPluginFragment extends BaseFragment<TestPluginActivity, TestPluginPresenter> implements TestPluginContract.ITestPluginView {
    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_test_plugin;
    }

    @Override
    protected void trySetupData(Bundle savedInstanceState) {

    }
}