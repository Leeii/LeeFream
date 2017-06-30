package cn.leeii.simple.ui.main;

import android.app.Activity;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.leeiidesu.libcommon.android.Toaster;
import com.leeiidesu.libmvp.mvp.BasePresenter;

import javax.inject.Inject;

import cn.leeii.simple.data.Response;
import cn.leeii.simple.data.entity.User;
import cn.nekocode.rxlifecycle.RxLifecycle;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * _ MainPresenter _ Created by dgg on 2017/6/19.
 */

public class MainPresenter extends BasePresenter<MainContract.IMainView, MainContract.IMainModel> {
    @Inject
    MainPresenter(MainContract.IMainView mView, MainContract.IMainModel iModel) {
        super(mView, iModel);
    }

    public void login(String username, String password) {
        mModel.login(1, username, password)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycle.bind((Activity) mView.context()).<Response<User>>withObservable())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showLoading();
                    }
                })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.dismissLoading();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<User>>() {
                    @Override
                    public void accept(Response<User> userResponse) throws Exception {
                        Toast.makeText(mView.context(), JSON.toJSONString(userResponse), Toast.LENGTH_LONG).show();
                    }
                });


    }
}
