package cn.leeii.simple;

import cn.leeii.lib.model.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Lee on 2016/12/12.
 */

public interface APIService {
    @FormUrlEncoded
    @POST("/Api/Account/Login")
    Observable<Response> login(@Field("UserName") String phoneNo,
                           @Field("password") String password,
                           @Field("loginDevice") int device,
                           @Field("clientVersion") String versionName);

    @FormUrlEncoded
    @POST("/api/Account/GetLikeInteractArticle")
    Observable<Response> collectionArticle(@Field("pageIndex") int pageIndex);
}
