package cn.leeii.simple.ui.ref;

import com.leeiidesu.libmvp.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * Created by leeiidesu on 2017/6/30.
 */
public class RefreshTestPresenter extends BasePresenter<RefreshTestContract.IRefreshTestView, RefreshTestContract.IRefreshTestModel> {
    @Inject
    RefreshTestPresenter(RefreshTestContract.IRefreshTestView mView, RefreshTestContract.IRefreshTestModel iModel) {
        super(mView, iModel);
    }
}
