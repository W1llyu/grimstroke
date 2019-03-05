package com.ouresports.grimstroke.lib.streamlink;

import com.alibaba.fastjson.JSONObject;
import com.ouresports.grimstroke.lib.exception.LibServiceException;
import com.ouresports.grimstroke.lib.util.OkHttpUtil;
import com.ouresports.grimstroke.lib.util.SecretUtil;
import lombok.Data;
import okhttp3.Request;

/**
 *
 * @author will
 * @date 2018/12/19
 */
public class Douyu {
    private static final String API_URL = "http://www.douyutv.com/api/v1/room";

    private String roomId;
    private Long timestamp;

    public Douyu(String roomId) {
        this.roomId = roomId;
        this.timestamp = System.currentTimeMillis() / 1000;
    }

    public String getStreamLink() throws LibServiceException{
        try {
            String body = getResponseBody();
            Response res = JSONObject.parseObject(body, Response.class);
            return res.getData().getHlsUrl();
        } catch (Exception e) {
            throw new LibServiceException("获取douyu直播流失败");
        }
    }

    private String getResponseBody() {
        String url = String.format("%s/%s?aid=wp&client_sys=wp&time=%d&auth=%s", API_URL, roomId, timestamp, getAuth());
        Request request = new okhttp3.Request.Builder()
                .url(url)
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36")
                .build();
        return OkHttpUtil.httpGet(request);
    }

    private String getAuth() {
        return SecretUtil.md5(String.format("room/%s?aid=wp&client_sys=wp&time=%dzNzMV1y4EMxOHS6I5WKm", roomId, timestamp));
    }

    @Data
    private static class Response {
        private ResponseData data;
    }

    @Data
    static class ResponseData {
        private String hlsUrl;
    }
}
