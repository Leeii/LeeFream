package cn.leeii.simple.ui.web;

import com.leeiidesu.libmvp.mvp.IContract;

/**
 * WebContract Created by leeiidesu on 2017/8/9.
 */
public interface WebContract {
    interface IWebView extends IContract.IView<WebPresenter> {

    }

    interface IWebModel extends IContract.IModel {

    }
}
