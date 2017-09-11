package cn.leeii.simple.ui.main;

import com.leeiidesu.libmvp.mvp.IContract;

import io.reactivex.Observable;

/**
 * _ MainContract _ Created by dgg on 2017/6/19.
 */

public interface MainContract {
    interface IMainView extends IContract.IView<MainPresenter> {

    }

    interface IMainModel extends IContract.IModel{

        Observable<String> expressInfo(String name,String expressNo);
    }
}
