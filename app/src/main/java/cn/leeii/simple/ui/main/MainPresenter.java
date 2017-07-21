package cn.leeii.simple.ui.main;

import com.alibaba.fastjson.JSON;
import com.leeiidesu.libcore.android.Toaster;
import com.leeiidesu.libmvp.mvp.BasePresenter;

import javax.inject.Inject;

import cn.leeii.simple.data.Response;
import cn.leeii.simple.data.entity.User;
import cn.leeii.simple.utils.RxTransformer;
import io.reactivex.functions.Consumer;

/**
 * _ MainPresenter _ Created by dgg on 2017/6/19.
 */

public class MainPresenter extends BasePresenter<MainContract.IMainView, MainContract.IMainModel> {
    private final Toaster mToaster;

    @Inject
    MainPresenter(MainContract.IMainView mView, MainContract.IMainModel iModel, Toaster mToaster) {
        super(mView, iModel);
        this.mToaster = mToaster;
    }

    void login(String username, String password) {
        mModel.login(1, username, password)

                .compose(RxTransformer.<Response<User>>applySchedulers(mView))

                .subscribe(new Consumer<Response<User>>() {
                    @Override
                    public void accept(Response<User> userResponse) throws Exception {
                        User user = userResponse.value;
                        mToaster.showSingletonToast(JSON.toJSONString(userResponse));
//                        Toast.makeText(mView.context(), JSON.toJSONString(userResponse), Toast.LENGTH_LONG).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
