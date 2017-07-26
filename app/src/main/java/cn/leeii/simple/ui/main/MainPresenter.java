package cn.leeii.simple.ui.main;

import com.alibaba.fastjson.JSON;
import com.leeiidesu.libmvp.mvp.BasePresenter;
import com.leeiidesu.libmvp.tool.RxTransformer;
import com.leeiidesu.libmvp.tool.SimpleObserver;

import javax.inject.Inject;

import cn.leeii.simple.data.Response;
import cn.leeii.simple.data.entity.User;
import io.reactivex.annotations.NonNull;

/**
 * _ MainPresenter _ Created by dgg on 2017/6/19.
 */

public class MainPresenter extends BasePresenter<MainContract.IMainView, MainContract.IMainModel> {

    @Inject
    MainPresenter(MainContract.IMainView mView, MainContract.IMainModel iModel) {
        super(mView, iModel);
    }

    void login(String username, String password) {
        mModel.login(1, username, password)

                .compose(RxTransformer.<Response<User>>applySchedulers(mView))

                .subscribe(new SimpleObserver<Response<User>>() {
                    @Override
                    public void onNext(@NonNull Response<User> userResponse) {
                        User user = userResponse.value;
                        mView.Tips(JSON.toJSONString(user));
                    }
                });
    }
}
