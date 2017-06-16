package cn.leeii.libmvp.request;

import cn.leeii.libmvp.model.Response;
import rx.Subscriber;

/**
 * ResponseSubscriber  Created by Lee on 2016/12/14.
 */

public abstract class ResponseSubscriber extends Subscriber<Response> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public abstract void onNext(Response response);
}
