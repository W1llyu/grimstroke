package com.ouresports.grimstroke.lib.livestream.service;

import com.alibaba.fastjson.JSONObject;
import com.ouresports.grimstroke.lib.exception.LibServiceException;
import com.ouresports.grimstroke.lib.livestream.entity.LivestreamSyncRbo;
import com.ouresports.grimstroke.lib.util.OkHttpUtil;
import lombok.Setter;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Base64;

/**
 *
 * @author will
 * @date 2018/12/22
 */
@Service
@ConfigurationProperties(prefix="livestream")
@Setter
public class LivestreamService {
    private String host;
    private String user;
    private String password;

    public void createLivestreamSync(LivestreamSyncRbo rbo) throws LibServiceException {
        String url = String.format("%s/stream/%s?platform=%s&room_id=%s", host, rbo.getId(), rbo.getPlatform(), rbo.getRoomId());
        Request request = new okhttp3.Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .header("Authorization", String.format("Basic %s", Base64.getEncoder().encodeToString((user + ":" + password).getBytes())))
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject.toJSONString(rbo)))
                .build();
        System.out.println();
        Response response = OkHttpUtil.execute(request);
        if (response == null || response.code() != 201) {
            throw new LibServiceException("启动推流失败");
        }
    }

    public void deleteLivestreamSync(String name) throws LibServiceException {
        String url = String.format("%s/stream/%s", host, name);
        Request request = new okhttp3.Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .header("Authorization", String.format("Basic %s", Base64.getEncoder().encodeToString((user + ":" + password).getBytes())))
                .delete()
                .build();
        Response response = OkHttpUtil.execute(request);
        if (response == null || response.code() != 202) {
            throw new LibServiceException("停止推流失败");
        }
    }
}
