package cn.leeii.simple.ui.img.di;


import com.leeiidesu.libmvp.di.scope.ActivityScope;

import cn.leeii.simple.di.component.BaseComponent;
import cn.leeii.simple.ui.img.ImageLoaderTestActivity;
import dagger.Component;


/**
 * ImageLoaderTestComponent Created by leeiidesu on 2017/9/11.
 */
@ActivityScope
@Component(modules = ImageLoaderTestModule.class, dependencies = BaseComponent.class)
public interface ImageLoaderTestComponent {
    void inject(ImageLoaderTestActivity activity);
}
