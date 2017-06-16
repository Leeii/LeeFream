package cn.leeii.simple;

import java.util.concurrent.TimeUnit;

import cn.leeii.libmvp.model.Response;
import io.rx_cache.DynamicKey;
import io.rx_cache.EvictProvider;
import io.rx_cache.LifeCache;
import io.rx_cache.Reply;
import rx.Observable;

/**
 * Created by Lee on 2017/1/4.
 */

public interface CacheService {
    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<Response>> getUsers(Observable<Response> oUsers, DynamicKey idLastUserQueried, EvictProvider evictProvider);
}