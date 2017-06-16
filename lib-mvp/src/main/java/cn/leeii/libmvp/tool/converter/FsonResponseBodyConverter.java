package cn.leeii.libmvp.tool.converter;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public final class FsonResponseBodyConverter<T> implements
                                            Converter<ResponseBody, T> {
    
    private final Type type;
    
    FsonResponseBodyConverter(Type type) {
        this.type = type;
    }
    
    @Override
    public T convert(ResponseBody value) throws IOException {
        String string = value.string();
        return JSON.parseObject(string, type);
    }
}
