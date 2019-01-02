package com.ouresports.grimstroke.lib.aliyun.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ouresports.grimstroke.lib.aliyun.entity.AliyunResponse;
import com.ouresports.grimstroke.lib.aliyun.entity.LiveStreamNotifyUrlResponse;
import com.ouresports.grimstroke.lib.exception.LibServiceException;
import com.ouresports.grimstroke.lib.util.OkHttpUtil;
import com.ouresports.grimstroke.lib.util.SecretUtil;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author will
 * @date 2018/12/19
 */
@Service
@ConfigurationProperties(prefix="aliyun.vod")
@Setter
public class AliyunLiveStreamService extends AliyunBaseService {
    private String liveStreamPushDomain;
    private String liveStreamPlayDomain;
    private String liveStreamKey;
    private static final String APP_NAME = "ouresports";
    private static final String HTTP_METHOD = "GET";
    private static final String API_BASE_URL = "http://live.aliyuncs.com/";

    public AliyunLiveStreamService() {
        this.apiVersion = "2016-11-01";
    }

    /**
     * 获取推流地址
     * @param name
     * @return
     */
    public String generateStreamPushUrl(String name) {
        long timestamp = (System.currentTimeMillis() / 1000) + 3600;
        String seed = String.format("/%s/%s-%d-0-0-%s", APP_NAME, name, timestamp, liveStreamKey);
        return String.format("rtmp://%s/%s/%s?vhost=%s&auth_key=%d-0-0-%s", liveStreamPushDomain, APP_NAME, name, liveStreamPlayDomain, timestamp, SecretUtil.md5(seed));
    }

    /**
     * 获取播放地址
     * @param name
     * @return
     */
    public String generateStreamPlayUrl(String name, String template) {
        long timestamp = (System.currentTimeMillis() / 1000) + 600;
        String seed = String.format("/%s/%s_%s.m3u8-%d-0-0-%s", APP_NAME, name, template, timestamp, liveStreamKey);
        return String.format("http://%s/%s/%s_%s.m3u8?auth_key=%d-0-0-%s", liveStreamPlayDomain, APP_NAME, name, template, timestamp, SecretUtil.md5(seed));
    }

    /**
     * 获取通知地址
     * @return
     * @throws LibServiceException
     */
    public LiveStreamNotifyUrlResponse getLiveStreamNotifyUrl() throws LibServiceException {
        Map<String, String> params = Maps.newHashMap();
        params.put("Action", "DescribeLiveStreamsNotifyUrlConfig");
        params.put("DomainName", liveStreamPlayDomain);
        String url = generateURL(API_BASE_URL, HTTP_METHOD, generatePublicParameters(), params);
        String body = OkHttpUtil.httpGet(url);
        return JSONObject.parseObject(body, LiveStreamNotifyUrlResponse.class);
    }

    /**
     * 设置通知地址
     * @param notifyUrl
     * @return
     * @throws LibServiceException
     */
    public boolean setLiveStreamNotifyUrl(String notifyUrl) throws LibServiceException {
        Map<String, String> params = Maps.newHashMap();
        params.put("Action", "SetLiveStreamsNotifyUrlConfig");
        params.put("DomainName", liveStreamPlayDomain);
        params.put("NotifyUrl", notifyUrl);
        String url = generateURL(API_BASE_URL, HTTP_METHOD, generatePublicParameters(), params);
        String body = OkHttpUtil.httpGet(url);
        return JSONObject.parseObject(body, AliyunResponse.class).getCode() == null;
    }

    /**
     * 禁止某条流的推送
     * @param name
     * @return
     * @throws LibServiceException
     */
    public boolean forbidLiveStream(String name) throws LibServiceException {
        Map<String, String> params = Maps.newHashMap();
        params.put("Action", "ForbidLiveStream");
        params.put("AppName", APP_NAME);
        params.put("DomainName", liveStreamPlayDomain);
        params.put("LiveStreamType", "publisher");
        params.put("StreamName", name);
        Date resumeTime = new Date(System.currentTimeMillis() + 86400000);
        params.put("ResumeTime", String.format("%sT%sZ",
                new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(resumeTime),
                new SimpleDateFormat("HH:mm:ss").format(resumeTime)
        ));
        String url = generateURL(API_BASE_URL, HTTP_METHOD, generatePublicParameters(), params);
        String body = OkHttpUtil.httpGet(url);
        return JSONObject.parseObject(body, AliyunResponse.class).getCode() == null;
    }

    /**
     * 恢复某条流的推送
     * @param name
     * @return
     * @throws LibServiceException
     */
    public boolean resumeLiveStream(String name) throws LibServiceException {
        Map<String, String> params = Maps.newHashMap();
        params.put("Action", "ResumeLiveStream");
        params.put("AppName", APP_NAME);
        params.put("DomainName", liveStreamPlayDomain);
        params.put("LiveStreamType", "publisher");
        params.put("StreamName", name);
        String url = generateURL(API_BASE_URL, HTTP_METHOD, generatePublicParameters(), params);
        String body = OkHttpUtil.httpGet(url);
        return JSONObject.parseObject(body, AliyunResponse.class).getCode() == null;
    }
}
