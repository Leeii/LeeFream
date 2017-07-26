package cn.leeii.simple.ui.videopicker.di;


import com.leeiidesu.libmvp.base.AbstractApplication;
import com.leeiidesu.libmvp.di.scope.ActivityScope;

import cn.leeii.simple.APIService;
import cn.leeii.simple.ui.videopicker.VideoPickerContract;
import cn.leeii.simple.ui.videopicker.VideoPickerModel;
import dagger.Module;
import dagger.Provides;


/**
 * _ VideoPickerModule _ Created by dgg on 2017/7/26.
 */
@Module
public class VideoPickerModule {
    private VideoPickerContract.IVideoPickerView view;

    public VideoPickerModule(VideoPickerContract.IVideoPickerView view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    VideoPickerContract.IVideoPickerView providerVideoPickerView() {
        return view;
    }

    @ActivityScope
    @Provides
    VideoPickerContract.IVideoPickerModel providerVideoPickerModel(AbstractApplication application, APIService service) {
        return new VideoPickerModel(application, service);
    }
}