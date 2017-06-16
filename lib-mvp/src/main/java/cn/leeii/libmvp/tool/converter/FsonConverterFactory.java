package cn.leeii.libmvp.tool.converter;

import com.alibaba.fastjson.serializer.SerializeConfig;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public final class FsonConverterFactory extends Converter.Factory {
    
    public static FsonConverterFactory create() {
        return new FsonConverterFactory(SerializeConfig.getGlobalInstance());
    }
    
    public static FsonConverterFactory create(SerializeConfig serializeConfig) {
        return new FsonConverterFactory(serializeConfig);
    }
    
    private final SerializeConfig serializeConfig;
    
    private FsonConverterFactory(SerializeConfig serializeConfig) {
        if (serializeConfig == null)
            throw new NullPointerException("SerializeConfig序列化配置对象为空");
        this.serializeConfig = serializeConfig;
    }
    
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type,
                                                            Annotation[] annotations,
                                                            Retrofit retrofit) {
        return new FsonResponseBodyConverter<>(type);
    }
    
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations,
                                                          Retrofit retrofit) {
        return new FsonRequestBodyConverter<>(serializeConfig);
    }
}
