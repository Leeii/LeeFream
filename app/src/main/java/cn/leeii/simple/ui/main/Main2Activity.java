package cn.leeii.simple.ui.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import cn.leeii.simple.R;
import cn.leeii.simple.base.BaseActivity;
import cn.leeii.simple.di.component.BaseComponent;
import cn.leeii.simple.ui.main.di.DaggerMainComponent;
import cn.leeii.simple.ui.main.di.MainModule;

public class Main2Activity extends BaseActivity<MainPresenter> {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void trySetupData(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);

//        mToaster.showSingletonToast("tao");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_test;
    }

    @Override
    protected void setupComponent(BaseComponent baseComponent) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        MainFragment fragment = (MainFragment) supportFragmentManager.findFragmentById(R.id.content);
        if (fragment == null) {
            fragment = new MainFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content, fragment)
                    .commit();
        }

        DaggerMainComponent
                .builder()
                .baseComponent(baseComponent)
                .mainModule(new MainModule(fragment))
                .build()
                .inject(this);
    }


}
