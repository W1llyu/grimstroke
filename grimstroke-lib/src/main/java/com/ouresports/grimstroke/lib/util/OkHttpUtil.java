package com.ouresports.grimstroke.lib.util;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author will
 * @date 2018/12/4
 */
public class OkHttpUtil {
    private static OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS);

    public static String httpGet(String url) {
        Request request = new okhttp3.Request.Builder().url(url).build();
        return httpGet(request);
    }

    public static String httpGet(Request request) {
        OkHttpClient okHttpClient = okHttpClientBuilder.build();
        Call call = okHttpClient.newCall(request);
        try {
            return call.execute().body().string();
        } catch (IOException e) {
            return null;
        }
    }

    public static Response execute(Request request) {
        OkHttpClient okHttpClient = okHttpClientBuilder.build();
        Call call = okHttpClient.newCall(request);
        try {
            return call.execute();
        } catch (IOException e) {
            return null;
        }
    }
}
