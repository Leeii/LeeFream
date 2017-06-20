package cn.leeii.simple.ui.main;

import android.os.Bundle;

import cn.leeii.simple.R;
import cn.leeii.simple.base.BaseFragment;
import cn.leeii.simple.di.component.BaseComponent;

/**
 * _ MainFragment _ Created by dgg on 2017/6/19.
 */

public class MainFragment extends BaseFragment<Main2Activity, MainPresenter> implements MainContract.IMainView {
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
}
