package cn.leeii.simple.ui.bind;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;

import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import cn.leeii.simple.R;
import cn.leeii.simple.R2;
import cn.leeii.simple.base.BaseActivity;
import cn.leeii.simple.di.BaseComponent;
import cn.leeii.simple.ui.bind.di.BindViewModule;
import cn.leeii.simple.ui.bind.di.DaggerBindViewComponent;

/**
 * Created by Lee on 2016/12/10.
 */
public class BindViewActivity extends BaseActivity<BindPresenter> {


    @BindView(R2.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void setupComponent(BaseComponent baseComponent) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            new RxPermissions(this).requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        toolbar.setTitle("哈哈哈哈哈");
        setSupportActionBar(toolbar);

        BindViewFragment fragment = new BindViewFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.content, fragment).commit();

        DaggerBindViewComponent
                .builder()
                .baseComponent(baseComponent)
                .bindViewModule(new BindViewModule(fragment))
                .build()
                .inject(this);
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_bind;
    }

    @Override
    protected void trySetupData(Bundle savedInstanceState) {
        // 设置一个exit transition
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Explode());
        }
    }
}
