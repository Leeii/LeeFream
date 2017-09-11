package cn.leeii.simple;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * _ APIService _ Created by dgg on 2017/6/19.
 */

public interface APIService {

    void getWxData(@Field("OpenId") String openId, @Field("Tel") String tel, @Field("UserName") String userName);


    @GET("http://www.kuaidi100.com/query")
    Observable<String> getExpressInfo(@Query("type") String expressName, @Query("postid") String expressNo);

}
