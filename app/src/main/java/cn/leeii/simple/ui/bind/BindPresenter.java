package cn.leeii.simple.ui.bind;

import javax.inject.Inject;

import cn.leeii.libmvp.di.scope.ActivityScope;
import cn.leeii.libmvp.model.Response;
import cn.leeii.libmvp.mvp.BasePresenter;
import cn.leeii.libmvp.request.ResponseSubscriber;
import cn.leeii.libmvp.request.RetryWithDelay;
import cn.leeii.libmvp.utils.LogUtil;
import cn.leeii.simple.APIService;
import cn.leeii.simple.CacheService;
import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.Reply;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * binder presenter Created by Lee on 2016/12/23.
 */

@ActivityScope
public class BindPresenter extends BasePresenter<BinderContract.IBinderView, APIService, CacheService> {

    @Inject
    BindPresenter(BinderContract.IBinderView mView, APIService api, CacheService mCacheService) {
        super(mView, api, mCacheService);
    }

    void login(String s, String s1) {
        addSubscribe(
                mCache.getUsers(
                        mApiService.login(s, s1, 1, "1.3"),
                        new DynamicKey(s),
                        new EvictDynamicKey(false))
                        .retryWhen(new RetryWithDelay(2, 2))
                        .doOnSubscribe(new Action0() {
                            @Override
                            public void call() {
                                mView.showLoading();
                                LogUtil.e("doTAG", "doOnSubscribe");
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .map(new Func1<Reply<Response>, Response>() {
                            @Override
                            public Response call(Reply<Response> responseReply) {
                                return responseReply.getData();
                            }
                        })
                        .doOnTerminate(new Action0() {
                            @Override
                            public void call() {
                                mView.dismissLoading();
                                LogUtil.e("doTAG", "doOnTerminate");
                            }
                        }),
                new ResponseSubscriber() {
                    @Override
                    public void onNext(Response response) {
                        mView.Tips(response.returnMsg);
                    }
                });
    }

}
