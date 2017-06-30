package cn.leeii.simple.ui.main;

import com.leeiidesu.libmvp.mvp.IContract;

import cn.leeii.simple.data.Response;
import cn.leeii.simple.data.entity.User;
import io.reactivex.Observable;

/**
 * _ MainContract _ Created by dgg on 2017/6/19.
 */

public interface MainContract {
    interface IMainView extends IContract.IView<MainPresenter> {

    }

    interface IMainModel extends IContract.IModel{

        Observable<Response<User>> login(int type, String username, String password);

    }
}
