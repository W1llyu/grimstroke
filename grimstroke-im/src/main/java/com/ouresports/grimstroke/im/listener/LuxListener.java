package com.ouresports.grimstroke.im.listener;

import com.alibaba.fastjson.JSONObject;
import com.ouresports.grimstroke.im.rbo.GrimConnectMessageRbo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author will
 * @date 2018/12/18
 */
@Component
public class LuxListener {
    @RabbitListener(queues="socket_connect")
    public void processConnect(String msg) {
        System.out.println(msg);
        try {
            JSONObject.parseObject(msg, GrimConnectMessageRbo.class);
        } catch (Exception ignored) {}
    }

    @RabbitListener(queues="socket_disconnect")
    public void processDisconnect(String msg) {
        System.out.println(msg);
        try {
            JSONObject.parseObject(msg, GrimConnectMessageRbo.class);
        } catch (Exception ignored) {}
    }
}
