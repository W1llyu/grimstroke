package com.ouresports.grimstroke.lib.aliyun.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ouresports.grimstroke.lib.aliyun.entity.*;
import com.ouresports.grimstroke.lib.exception.LibServiceException;
import com.ouresports.grimstroke.lib.util.OkHttpUtil;
import com.ouresports.grimstroke.lib.util.SecretUtil;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * @author will
 * @date 2018/12/4
 */
@Service
@ConfigurationProperties(prefix="aliyun.vod")
@Setter
public class AliyunVodService extends AliyunBaseService {
    private String templateGroupId;
    private String liveStreamPushDomain;
    private String liveStreamPlayDomain;
    private String liveStreamKey;

    private static final String appName = "ouresports";
    private final static String HTTP_METHOD = "GET";

    public VodUploadAuthResponse getVideoUploadAuth(VodUploadInfo aliVideo) throws LibServiceException {
        Map<String, String> params = Maps.newHashMap();
        params.put("Action", "CreateUploadVideo");
        params.put("Title", aliVideo.getTitle());
        params.put("FileName", aliVideo.getFileName());
        params.put("TemplateGroupId", templateGroupId);
        return getResponseBody(params, VodUploadAuthResponse.class);
    }

    public VodUploadAuthResponse refreshUploadVideoAuth(String videoId) throws LibServiceException {
        Map<String, String> params = Maps.newHashMap();
        params.put("Action", "RefreshUploadVideo");
        params.put("VideoId", videoId);
        VodUploadAuthResponse vodAuth = getResponseBody(params, VodUploadAuthResponse.class);
        vodAuth.setVideoId(videoId);
        return vodAuth;
    }

    public VodPlayAuthResponse getVideoPlayAuth(String videoId) throws LibServiceException {
        Map<String, String> params = Maps.newHashMap();
        params.put("Action", "GetVideoPlayAuth");
        params.put("VideoId", videoId);
        return getResponseBody(params, VodPlayAuthResponse.class);
    }

    public VodDetailResponse getVideoDetail(String videoId) throws LibServiceException {
        Map<String, String> params = Maps.newHashMap();
        params.put("Action", "GetVideoInfo");
        params.put("VideoId", videoId);
        return getResponseBody(params, VodDetailResponse.class);
    }

    public String generateStreamPushUrl(String name) {
        long timestamp = (System.currentTimeMillis() / 1000) + 3600;
        String seed = String.format("/%s/%s-%d-0-0-%s", appName, name, timestamp, liveStreamKey);
        return String.format("rtmp://%s/%s/%s?vhost=%s&auth_key=%d-0-0-%s", liveStreamPushDomain, appName, name, liveStreamPlayDomain, timestamp, SecretUtil.md5(seed));
    }

    public String generateStreamPlayUrl(String name) {
        long timestamp = (System.currentTimeMillis() / 1000) + 600;
        String seed = String.format("/%s/%s.m3u8-%d-0-0-%s", appName, name, timestamp, liveStreamKey);
        return String.format("http://%s/%s/%s.m3u8?auth_key=%d-0-0-%s", liveStreamPlayDomain, appName, name, timestamp, SecretUtil.md5(seed));
    }

    private <T extends AliyunResponse>T getResponseBody(Map<String, String> params, Class<T> klass) throws LibServiceException {
        T vodAuth;
        try {
            String url = generateOpenAPIURL(generatePublicParameters(), params);
            String body = OkHttpUtil.httpGet(url);
            vodAuth = JSONObject.parseObject(body, klass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new LibServiceException("");
        }
        assert vodAuth != null;
        if (vodAuth.getCode() != null) {
            throw new LibServiceException(String.format("%s;%s", vodAuth.getCode(), vodAuth.getMessage()));
        }
        return vodAuth;
    }

    private String generateOpenAPIURL(Map<String, String> publicParams, Map<String, String> privateParams) {
        return generateURL(String.format("http://vod.%s.aliyuncs.com/", regionId), HTTP_METHOD, publicParams, privateParams);
    }
}
