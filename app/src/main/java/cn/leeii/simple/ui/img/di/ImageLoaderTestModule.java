package cn.leeii.simple.ui.img.di;


import com.leeiidesu.libmvp.base.AbstractApplication;
import com.leeiidesu.libmvp.di.scope.ActivityScope;

import cn.leeii.simple.ui.img.ImageLoaderTestContract;
import cn.leeii.simple.ui.img.ImageLoaderTestModel;
import dagger.Module;
import dagger.Provides;
import cn.leeii.simple.APIService;


/**
 * ImageLoaderTestModule Created by leeiidesu on 2017/9/11.
 */
@Module
public class ImageLoaderTestModule {
    private ImageLoaderTestContract.IImageLoaderTestView view;

    public ImageLoaderTestModule(ImageLoaderTestContract.IImageLoaderTestView view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ImageLoaderTestContract.IImageLoaderTestView providerImageLoaderTestView() {
        return view;
    }

    @ActivityScope
    @Provides
    ImageLoaderTestContract.IImageLoaderTestModel providerImageLoaderTestModel(AbstractApplication application, APIService service) {
        return new ImageLoaderTestModel(application, service);
    }
}