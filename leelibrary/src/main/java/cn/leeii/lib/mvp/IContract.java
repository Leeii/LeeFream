package cn.leeii.lib.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import cn.leeii.lib.model.Response;
import cn.leeii.lib.request.ResponseSubscriber;
import rx.Observable;
import rx.Subscription;

/**
 * Created by Lee on 2016/12/14.
 */

public interface IContract {
    interface IView<P extends IPresenter> {
        void setPresenter(P presenter);

        void addAction(String... actions);

        Intent intent();

        Context context();

        void finishActivity();

        void showLoading();

        void dismissLoading();

        void Tips(String msg);

        void Tips(int msg);

        /**
         * 跳转不带参数
         */
        void startActivity(Class clazz, boolean finish);

        /**
         * 跳转Activity
         */
        void startActivity(Intent intent, Class clazz, boolean finish);

        /**
         * 带结果的Activity跳转 无参数
         */
        void startActivityForResult(int requestCode, Class clazz);

        /**
         * 带结果的Activity跳转 有参数
         */
        void startActivityForResult(int requestCode, Intent intent, Class clazz);

        void startActivity(Class activityClass, boolean finish, Bundle option);

        void startActivity(Intent intent, Class activityClass, boolean finish, Bundle option);

        /**
         * 带结果的Activity跳转 无参数
         */
        void startActivityForResult(int requestCode, Class clazz, Bundle option);

        /**
         * 带结果的Activity跳转 有参数
         */
        void startActivityForResult(int requestCode, Intent intent, Class clazz, Bundle option);
    }

    interface IPresenter {
        void postBroadcast(String action, Parcelable data);

        void subscribe();

        void unSubscribe();

        void addSubscribe(Subscription subscription);

        void addSubscribe(Observable<Response> observable, ResponseSubscriber subscription);

        <T extends Parcelable> void dealReceive(String action, T result);
    }
}
