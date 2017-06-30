package cn.leeii.simple;

import cn.leeii.simple.data.Response;
import cn.leeii.simple.data.entity.User;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * _ APIService _ Created by dgg on 2017/6/19.
 */

public interface APIService {

    void getWxData(@Field("OpenId") String openId, @Field("Tel") String tel, @Field("UserName") String userName);

    @POST("/api/account/Login")
    @FormUrlEncoded
    Observable<Response<User>> login(@Field("LoginState") int type, @Field("UserName") String username, @Field("Password") String password);
}
