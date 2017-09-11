package cn.leeii.simple.ui.img;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.leeiidesu.lib.common.imageloader.ImageConfiguration;
import com.leeiidesu.lib.common.imageloader.ImageLoader;

import butterknife.BindView;
import butterknife.OnClick;
import cn.leeii.simple.R;
import cn.leeii.simple.base.BaseActivity;
import cn.leeii.simple.di.component.BaseComponent;
import cn.leeii.simple.ui.img.di.DaggerImageLoaderTestComponent;
import cn.leeii.simple.ui.img.di.ImageLoaderTestModule;

/**
 * ImageLoaderTestActivity Created by leeiidesu on 2017/9/11.
 */
public class ImageLoaderTestActivity extends BaseActivity<ImageLoaderTestPresenter> implements ImageLoaderTestContract.IImageLoaderTestView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.img1)
    ImageView mImg1;
    @BindView(R.id.img2)
    ImageView mImg2;
    @BindView(R.id.img3)
    ImageView mImg3;
    @BindView(R.id.img4)
    ImageView mImg4;
    @BindView(R.id.img5)
    ImageView mImg5;
    @BindView(R.id.img6)
    ImageView mImg6;
    @BindView(R.id.input)
    EditText mInput;
    @BindView(R.id.status)
    TextView mStatus;

    @Override
    protected void trySetupData(Bundle savedInstanceState) {
        setSupportActionBar(mToolbar);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_image_loader_test;
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
        DaggerImageLoaderTestComponent
                .builder()
                .baseComponent(baseComponent)
                .imageLoaderTestModule(new ImageLoaderTestModule(this))
                .build()
                .inject(this);
    }

    @OnClick(R.id.load)
    public void onViewClicked() {
        String url = mInput.getText().toString().trim();
        ImageLoader.getInstance()
                .display(url, mImg1);
        ImageLoader.getInstance()
                .display(url, mImg2, new ImageConfiguration.Builder().stroke(10, Color.BLACK).build());
        ImageLoader.getInstance()
                .display(url, mImg3, new ImageConfiguration.Builder().circleCrop().build());
        ImageLoader.getInstance()
                .display(url, mImg4, new ImageConfiguration.Builder().circleCrop().stroke(10, Color.BLACK).build());
        ImageLoader.getInstance()
                .display(url, mImg5, new ImageConfiguration.Builder().round(20).build());
        ImageLoader.getInstance()
                .display(url, mImg6, new ImageConfiguration.Builder().round(20).stroke(10, Color.BLACK).build());
    }
}
