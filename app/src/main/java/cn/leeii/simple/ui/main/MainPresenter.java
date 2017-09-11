package cn.leeii.simple.ui.main;

import com.leeiidesu.libmvp.mvp.BasePresenter;
import com.leeiidesu.libmvp.tool.RxTransformer;
import com.leeiidesu.libmvp.tool.SimpleObserver;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * _ MainPresenter _ Created by dgg on 2017/6/19.
 */

public class MainPresenter extends BasePresenter<MainContract.IMainView, MainContract.IMainModel> {

    @Inject
    MainPresenter(MainContract.IMainView mView, MainContract.IMainModel iModel) {
        super(mView, iModel);
    }

    void testApi(String name, String expressNo) {
        mModel.expressInfo(name, expressNo)
                .compose(RxTransformer.<String>applySchedulers(mView))
                .subscribe(new SimpleObserver<String>() {
                    @Override
                    public void onNext(@NonNull String userResponse) {
                        mView.Tips(userResponse);
                    }
                });
    }

}
