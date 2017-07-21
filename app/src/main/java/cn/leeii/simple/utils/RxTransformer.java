package cn.leeii.simple.utils;

import android.app.Activity;

import com.leeiidesu.libmvp.mvp.IContract;

import cn.nekocode.rxlifecycle.RxLifecycle;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by leeiidesu on 2017/6/30.
 */

public class RxTransformer {
    public static <T> ObservableTransformer<T, T> applySchedulers(final IContract.IView mView) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .compose(RxLifecycle.bind((Activity) mView.context()).<T>withObservable())
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
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<T, T> applySchedulersWithOutLoading(final IContract.IView mView) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .compose(RxLifecycle.bind((Activity) mView.context()).<T>withObservable())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
