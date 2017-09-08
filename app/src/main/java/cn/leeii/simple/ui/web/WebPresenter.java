package cn.leeii.simple.ui.web;

import com.leeiidesu.libmvp.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * WebPresenter Created by leeiidesu on 2017/8/9.
 */
public class WebPresenter extends BasePresenter<WebContract.IWebView, WebContract.IWebModel> {
    @Inject
    WebPresenter(WebContract.IWebView mView, WebContract.IWebModel iModel) {
        super(mView, iModel);
    }
}
