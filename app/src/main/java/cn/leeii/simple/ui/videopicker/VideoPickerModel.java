package cn.leeii.simple.ui.videopicker;

import com.leeiidesu.libmvp.base.AbstractApplication;
import com.leeiidesu.libmvp.mvp.BaseModel;

import cn.leeii.simple.APIService;

/**
 * _ VideoPickerModel _ Created by dgg on 2017/7/26.
 */
public class VideoPickerModel extends BaseModel<AbstractApplication, APIService> implements VideoPickerContract.IVideoPickerModel {
    public VideoPickerModel(AbstractApplication simple, APIService apiService) {
        super(simple, apiService);
    }
}
