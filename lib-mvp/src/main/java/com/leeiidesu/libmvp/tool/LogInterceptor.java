package com.leeiidesu.libmvp.tool;


import com.leeiidesu.libcommon.android.Logger;
import com.leeiidesu.libcommon.util.Check;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Set;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 日志拦截器 Created by Leeii on 2016/1/21.
 */
@SuppressWarnings("ALL")
public class LogInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        long start = System.nanoTime();
        // 判断请求
        Request request = chain.request();
        if (Logger.isPrint && request != null) {
            // 请求日志
            okio.Buffer buffer = new okio.Buffer();

            Headers headers = request.headers();
            if (headers != null) {
                Set<String> headerNames = headers.names();
                for (String headerName : headerNames)
                    Logger.i("Request头",
                            String.format("Header { Key = %s, Value = %s }",
                                    headerName,
                                    headers.get(headerName)));
            }
            Logger.i("Request信息",
                    String.format("BaseRequest { Method = %s, Url = %s }",
                            request.method(),
                            request.url()));
            // 请求参数
            RequestBody requestBody = request.body();
            if (requestBody != null) {
                requestBody.writeTo(buffer);
                // 编码格式
                Charset charset = Charset.forName("UTF-8");
                String params = buffer.readString(charset);
                // 做处理
                params = params.replaceAll("&", ", ");
                params = params.replaceAll("=", " = ");
                if (String.valueOf(requestBody.contentType())
                        .contains("multipart"))
                    Logger.i("Request参数", "Params { Pictures }");
                else
                    Logger.i("Request参数",
                            String.format("Params { %s }",
                                    URLDecoder.decode(params,
                                            "utf-8")));
            }
        }
        // 判断响应
        Response response = chain.proceed(request);
        if (Logger.isPrint && request != null && response != null) {
            long end = System.nanoTime();
            Logger.i("Response信息",
                    String.format("Resp { Code = %d, Message = %s }",
                            response.code(),
                            Check.isEmpty(response.message()) ? "Success"
                                    : response.message()));
            Logger.i("ResponseData", jsonFormat(response.message()));
            Logger.i("Connection耗时",
                    String.format("Connection { Time = %.1f 毫秒 }",
                            (end - start) / 1e6d));
        }
        return response;
    }

    /**
     * json 格式化
     *
     * @param bodyString
     * @return
     */
    public String jsonFormat(String bodyString) {
        String message;
        try {
            if (bodyString.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(bodyString);
                message = jsonObject.toString(4);
            } else if (bodyString.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(bodyString);
                message = jsonArray.toString(4);
            } else {
                message = bodyString;
            }
        } catch (JSONException e) {
            message = bodyString;
        }
        return message;
    }
}
