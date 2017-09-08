package cn.leeii.simple.ui.web;

import com.leeiidesu.libmvp.base.AbstractApplication;
import com.leeiidesu.libmvp.mvp.BaseModel;

import cn.leeii.simple.APIService;

/**
 * WebModel Created by leeiidesu on 2017/8/9.
 */
public class WebModel extends BaseModel<AbstractApplication, APIService> implements WebContract.IWebModel {
    public WebModel(AbstractApplication simple, APIService apiService) {
        super(simple, apiService);
    }
}
