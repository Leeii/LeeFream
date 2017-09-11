package cn.leeii.simple.ui.img;

import com.leeiidesu.libmvp.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * ImageLoaderTestPresenter Created by leeiidesu on 2017/9/11.
 */
public class ImageLoaderTestPresenter extends BasePresenter<ImageLoaderTestContract.IImageLoaderTestView, ImageLoaderTestContract.IImageLoaderTestModel> {
    @Inject
    ImageLoaderTestPresenter(ImageLoaderTestContract.IImageLoaderTestView mView, ImageLoaderTestContract.IImageLoaderTestModel iModel) {
        super(mView, iModel);
    }
}
