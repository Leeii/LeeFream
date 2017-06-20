package com.leeiidesu.libmvp.tool.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

public final class FsonRequestBodyConverter<T> implements
                                           Converter<T, RequestBody> {
    
    private static final MediaType MEDIA_TYPE =
                                              MediaType.parse("application/json; charset=UTF-8");
    
    private final SerializeConfig serializeConfig;
    
    public FsonRequestBodyConverter(SerializeConfig serializeConfig) {
        this.serializeConfig = serializeConfig;
    }
    
    @Override
    public RequestBody convert(T value) throws IOException {
        return RequestBody.create(MEDIA_TYPE,
                                  JSON.toJSONBytes(value, serializeConfig));
    }
}
