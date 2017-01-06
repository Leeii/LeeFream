package cn.leeii.lib.mvp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;

import javax.inject.Inject;

import cn.leeii.lib.model.Action;
import cn.leeii.lib.model.Response;
import cn.leeii.lib.request.ResponseSubscriber;
import cn.leeii.lib.utils.StringUtil;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Presenter 也要按照基本法  +1s  Created by Lee on 2016/12/26.
 */
public abstract class BasePresenter<V extends IContract.IView, API, C> implements IContract.IPresenter {
    protected V mView;

    private CompositeSubscription mSubscriptions;

    protected API mApiService;

    protected C mCache;

    public BasePresenter(V mView, API api, C mCache) {
        this.mView = mView;
        mApiService = api;
        this.mCache = mCache;
        mSubscriptions = new CompositeSubscription();
    }

    @SuppressWarnings("unchecked")
    @Inject
    protected void setupListener() {
        mView.setPresenter(this);
    }

    @Override
    public void postBroadcast(String action, Parcelable data) {
        if (StringUtil.isEmpty(action))
            throw new NullPointerException("无广播Action");
        Intent intent = new Intent();
        intent.setAction(action);
        if (data != null) // 判断结果
            intent.putExtra(Action.RESULT, data);
        LocalBroadcastManager.getInstance(mView.context())
                .sendBroadcast(intent);
    }

    @Override
    public void subscribe() {
    }

    @Override
    public final void unSubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public final void addSubscribe(Subscription subscription) {
        mSubscriptions.add(subscription);
    }

    @Override
    public void addSubscribe(Observable<Response> observable, ResponseSubscriber subscription) {
        Subscription subscribe = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscription);
        addSubscribe(subscribe);
    }

    @Override
    public <T extends Parcelable> void dealReceive(String action, T result) {

    }
}
