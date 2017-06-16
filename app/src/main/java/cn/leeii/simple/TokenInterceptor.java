package cn.leeii.simple;

import java.io.IOException;

import cn.leeii.libmvp.utils.PreferenceHelper;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *  Created by Lee on 2016/12/23.
 */

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder =
                chain.request()
                        .newBuilder();
        String token;
        if ((token = PreferenceHelper.getString("Token", null)) != null) {
            requestBuilder.addHeader("BodeAuth", token);
        }
        return chain.proceed(requestBuilder.build());
    }
}
