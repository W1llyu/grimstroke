package com.ouresports.grimstroke.im.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ouresports.grimstroke.im.rbo.socket.LuxMessageRbo;
import com.ouresports.grimstroke.im.service.NotificationService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @author will
 * @date 2018/12/18
 */
@Service
public class AmqpNotificationServiceImpl implements NotificationService {
    private static final String ROUTING_KEY = "socket.message.listen";
    private static final String EXCHANGE_NAME = "lux_default";
    @Resource
    private AmqpTemplate template;

    @Override
    public void sendNotification(LuxMessageRbo rbo) {
        template.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, JSONObject.toJSONString(rbo));
    }
}
