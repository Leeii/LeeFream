package cn.leeii.simple.ui.ref;

import com.leeiidesu.libmvp.base.AbstractApplication;
import com.leeiidesu.libmvp.mvp.BaseModel;

import cn.leeii.simple.APIService;

/**
 * Created by leeiidesu on 2017/6/30.
 */
public class RefreshTestModel extends BaseModel<AbstractApplication, APIService> implements RefreshTestContract.IRefreshTestModel {
    public RefreshTestModel(AbstractApplication simple, APIService apiService) {
        super(simple, apiService);
    }
}
