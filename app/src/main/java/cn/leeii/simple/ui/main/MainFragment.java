package cn.leeii.simple.ui.main;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.leeiidesu.lib.common.loader.ImageLoader;
import com.leeiidesu.lib.permission.PermissionHelper;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.leeii.simple.R;
import cn.leeii.simple.base.BaseFragment;
import cn.leeii.simple.di.component.BaseComponent;
import cn.leeii.simple.ui.ref.RefreshTestActivity;

/**
 * _ MainFragment _ Created by dgg on 2017/6/19.
 */

public class MainFragment extends BaseFragment<Main2Activity, MainPresenter> implements MainContract.IMainView {


    Unbinder unbinder;
    @BindView(R.id.imageView2)
    ImageView mImageView2;
    Unbinder unbinder1;


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

    @OnClick({R.id.button2, R.id.button3, R.id.button4, R.id.button5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button2:
                mPresenter.login("18782251053", "0");
                break;
            case R.id.button3:
                startActivity(RefreshTestActivity.class, false);
                break;
            case R.id.button4:
//                startActivity(SettingActivity.class, false);

                PermissionHelper.request(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE});
                break;
            case R.id.button5:
                ImageLoader.load("http://upload-images.jianshu.io/upload_images/2542851-2ac4334507f3908f.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240"
                        , mImageView2);
                break;
        }
    }

}
