package cn.leeii.simple.ui.test;

import android.os.Bundle;
import android.view.MenuItem;

import cn.leeii.simple.R;
import cn.leeii.simple.base.BaseActivity;
import cn.leeii.simple.di.component.BaseComponent;
import cn.leeii.simple.ui.test.di.DaggerTestComponent;
import cn.leeii.simple.ui.test.di.TestModule;

/**
 * TestActivity Created by leeiidesu on 2017/9/18.
 */
public class TestActivity extends BaseActivity<TestPresenter> implements TestContract.ITestView {
    @Override
    protected void trySetupData(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_test;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void setupComponent(BaseComponent baseComponent) {
        DaggerTestComponent
                .builder()
                .baseComponent(baseComponent)
                .testModule(new TestModule(this))
                .build()
                .inject(this);
    }
}
