package com.ouresports.grimstroke.lib.zeus.service;

import com.alibaba.fastjson.JSONObject;
import com.ouresports.grimstroke.lib.exception.LibServiceException;
import com.ouresports.grimstroke.lib.util.OkHttpUtil;
import com.ouresports.grimstroke.lib.zeus.entity.OperationResponse;
import com.ouresports.grimstroke.lib.zeus.entity.RewardRbo;
import lombok.Setter;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 *
 * @author will
 * @date 2019/1/10
 */
@Service
@ConfigurationProperties(prefix="zeus")
@Setter
public class ZeusService {
    private String host;

    public void createReward(String token, RewardRbo rbo) throws LibServiceException {
        String url = String.format("%s/api/users/%d/rewards", host, rbo.getReward().getReceiverId());
        Request request = new okhttp3.Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject.toJSONString(rbo)))
                .build();
        Response response = OkHttpUtil.execute(request);
        int code = response == null ? 400 : response.code();
        if (response != null) {
            response.close();
        }
        if (code > 299) {
            OperationResponse body = getErrorMessage(response);
            throw body == null ? new LibServiceException(null) : new LibServiceException(body.getErrorCode(), body.getErrorMessage());
        }
    }

    private OperationResponse getErrorMessage(Response response) {
        if (response == null) {
            return null;
        }
        try {
            return JSONObject.parseObject(response.body().string(), OperationResponse.class);
        } catch (IOException e) {
            return null;
        }
    }
}
