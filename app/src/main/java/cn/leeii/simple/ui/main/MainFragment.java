package cn.leeii.simple.ui.main;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.leeiidesu.lib.common.loader.ImageLoader;
import com.leeiidesu.lib.permission.PermissionHelper;
import com.leeiidesu.lib.permission.listener.OnPermissionResultListener;
import com.leeiidesu.lib.widget.banner.BannerView;
import com.leeiidesu.libcore.android.Logger;
import com.leeiidesu.libmvp.tool.SimpleObserver;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import cn.leeii.simple.R;
import cn.leeii.simple.base.BaseFragment;
import cn.leeii.simple.di.component.BaseComponent;
import cn.leeii.simple.ui.web.WebActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * _ MainFragment _ Created by dgg on 2017/6/19.
 */

public class MainFragment extends BaseFragment<Main2Activity, MainPresenter> implements MainContract.IMainView, OnPermissionResultListener {


    @BindView(R.id.imageView2)
    ImageView mImageView2;
    @BindView(R.id.banner)
    BannerView mBanner;


    @Override
    protected void setupComponent(BaseComponent baseComponent) {

    }

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_bind;
    }

    @Override
    protected void trySetupData(Bundle savedInstanceState) {
        ArrayList<String> uris = new ArrayList<>();
        uris.add("http://www.jcodecraeer.com/uploads/userup/4117/myface.jpg");
        uris.add("http://upload.jianshu.io/users/upload_avatars/3846387/27dae6b8-88b2-430a-bba7-4cfaf00a6280.jpg");
        uris.add("http://upload.jianshu.io/users/upload_avatars/3846387/27dae6b8-88b2-430a-bba7-4cfaf00a6280.jpg");
        uris.add("http://www.jcodecraeer.com/uploads/userup/4117/myface.jpg");
        uris.add("http://upload-images.jianshu.io/upload_images/2525548-1258cc8bc5291aaa.png");
        uris.add("http://upload.jianshu.io/users/upload_avatars/3846387/27dae6b8-88b2-430a-bba7-4cfaf00a6280.jpg");


        mBanner.setBannerUrl(uris);


    }

    @OnClick({R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button2:
                mPresenter.login("18782251053", "0");
                break;
            case R.id.button3:
                PermissionHelper.with(this)
                        .permissions(Manifest.permission.CAMERA)
                        .showOnRationale("Rationale", "C", "Q")
                        .showOnDenied("Denied", "C", "Q")
                        .listener(this)
                        .request();

                break;
            case R.id.button4:
//                startActivity(SettingActivity.class, false);
                ArrayList<String> uris = new ArrayList<>();
                uris.add("http://www.jcodecraeer.com/uploads/userup/4117/myface.jpg");
                uris.add("http://upload.jianshu.io/users/upload_avatars/3846387/27dae6b8-88b2-430a-bba7-4cfaf00a6280.jpg");
                uris.add("http://upload-images.jianshu.io/upload_images/2525548-1258cc8bc5291aaa.png");
                uris.add("http://upload.jianshu.io/users/upload_avatars/3846387/27dae6b8-88b2-430a-bba7-4cfaf00a6280.jpg");


                mBanner.setBannerUrl(uris);
                break;
            case R.id.button5:
                ImageLoader.load("http://upload-images.jianshu.io/upload_images/2525548-1258cc8bc5291aaa.png"
                        , mImageView2);
                break;
            case R.id.button6:
                Observable.just(1)
                        .map(new Function<Integer, Object>() {
                            @Override
                            public Object apply(@NonNull Integer integer) throws Exception {
                                ImageLoader.clearDiskCache(context());

                                return "2";
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SimpleObserver<Object>() {
                            @Override
                            public void onNext(@NonNull Object o) {
                                ImageLoader.clearMemoryCache(context());
                                Tips("缓存清楚成功");
                            }
                        });

                break;
            case R.id.button7:
                startActivity(WebActivity.class, false);
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
