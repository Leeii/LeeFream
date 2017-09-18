package com.leeiidesu.libmvp.mvp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Lee on 2016/12/14.
 */

public interface IContract {
    interface IView<P extends IPresenter> {
        void setPresenter(P presenter);

        Intent intent();

        Context context();

        void finishActivity();

        void showLoading();

        void dismissLoading();

        void showToast(String msg);

        void showToast(int msg);

        /**
         * 跳转不带参数
         *
         * @param clazz  目标activity
         * @param finish 是否结束当前页面
         */
        void startActivity(Class clazz, boolean finish);

        /**
         * 跳转Activity
         *
         * @param clazz  目标activity
         * @param finish 是否结束当前页面
         */
        void startActivity(Intent intent, Class clazz, boolean finish);

        /**
         * 带结果的Activity跳转 无参数
         *
         * @param clazz 目标activity
         */
        void startActivityForResult(int requestCode, Class clazz);

        /**
         * 带结果的Activity跳转 有参数
         *
         * @param clazz 目标activity
         */
        void startActivityForResult(int requestCode, Intent intent, Class clazz);

        /***** ↓ 带效果跳转 ↓ *****/
        @TargetApi(16)
        void startActivity(Class activityClass, boolean finish, Bundle option);

        @TargetApi(16)
        void startActivity(Intent intent, Class activityClass, boolean finish, Bundle option);

        @TargetApi(16)
        void startActivityForResult(int requestCode, Class clazz, Bundle option);

        @TargetApi(16)
        void startActivityForResult(int requestCode, Intent intent, Class clazz, Bundle option);

        /***** ↑ 带效果跳转 ↑ *****/
    }

    interface IPresenter {

    }

    interface IModel {

    }
}
