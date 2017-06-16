package cn.leeii.simple.ui.bind;

import cn.leeii.libmvp.mvp.IContract;

/**
 * Created by Lee on 2016/12/26.
 */

public interface BinderContract {
    interface IBinderView extends IContract.IView<BindPresenter> {
        void loadend(String message);
    }
}
