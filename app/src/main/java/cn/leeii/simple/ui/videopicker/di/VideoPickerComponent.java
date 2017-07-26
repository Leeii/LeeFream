package cn.leeii.simple.ui.videopicker.di;


import com.leeiidesu.libmvp.di.scope.ActivityScope;

import cn.leeii.simple.di.component.BaseComponent;
import cn.leeii.simple.ui.videopicker.VideoPickerActivity;
import dagger.Component;


/**
 * _ VideoPickerComponent _ Created by dgg on 2017/7/26.
 */
@ActivityScope
@Component(modules = VideoPickerModule.class, dependencies = BaseComponent.class)
public interface VideoPickerComponent {
    void inject(VideoPickerActivity activity);
}
