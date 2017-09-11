package cn.leeii.simple.ui.img;

import com.leeiidesu.libmvp.base.AbstractApplication;
import com.leeiidesu.libmvp.mvp.BaseModel;

import cn.leeii.simple.APIService;

/**
 * ImageLoaderTestModel Created by leeiidesu on 2017/9/11.
 */
public class ImageLoaderTestModel extends BaseModel<AbstractApplication, APIService> implements ImageLoaderTestContract.IImageLoaderTestModel {
    public ImageLoaderTestModel(AbstractApplication simple, APIService apiService) {
        super(simple, apiService);
    }
}
