package cn.leeii.simple.ui.videopicker;

import com.leeiidesu.libmvp.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * _ VideoPickerPresenter _ Created by dgg on 2017/7/26.
 */
public class VideoPickerPresenter extends BasePresenter<VideoPickerContract.IVideoPickerView, VideoPickerContract.IVideoPickerModel> {
    @Inject
    VideoPickerPresenter(VideoPickerContract.IVideoPickerView mView, VideoPickerContract.IVideoPickerModel iModel) {
        super(mView, iModel);
    }
}
