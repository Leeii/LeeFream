package cn.leeii.simple.ui.main;

import com.leeiidesu.libmvp.base.AbstractApplication;
import com.leeiidesu.libmvp.mvp.BaseModel;

import cn.leeii.simple.APIService;
import cn.leeii.simple.data.Response;
import cn.leeii.simple.data.entity.User;
import io.reactivex.Observable;

/**
 * _ MainModel _ Created by dgg on 2017/6/19.
 */

public class MainModel extends BaseModel<AbstractApplication, APIService> implements MainContract.IMainModel {
    public MainModel(AbstractApplication simple, APIService apiService) {
        super(simple, apiService);
    }

    @Override
    public Observable<Response<User>> login(int type, String username, String password) {
        return mApiService.login(type,username,password);
    }
}
