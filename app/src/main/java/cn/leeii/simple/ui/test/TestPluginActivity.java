package cn.leeii.simple.ui.test;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import cn.leeii.simple.R;
import cn.leeii.simple.base.BaseActivity;
import cn.leeii.simple.di.component.BaseComponent;
import cn.leeii.simple.ui.test.di.DaggerTestPluginComponent;
import cn.leeii.simple.ui.test.di.TestPluginModule;

/**
 * _ TestPluginActivity _ Created by dgg on 2017/6/20.
 */
public class TestPluginActivity extends BaseActivity<TestPluginPresenter> {
    @Override
    protected void trySetupData(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_test_plugin;
    }

    @Override
    protected void setupComponent(BaseComponent baseComponent) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        TestPluginFragment fragment = (TestPluginFragment) supportFragmentManager.findFragmentById(R.id.content);
        if (fragment == null) {
            fragment = new TestPluginFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content, fragment)
                    .commit();
        }

        DaggerTestPluginComponent
                .builder()
                .baseComponent(baseComponent)
                .testPluginModule(new TestPluginModule(fragment))
                .build()
                .inject(this);
    }
}
