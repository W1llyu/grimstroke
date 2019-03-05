package com.ouresports.grimstroke.im.service;

import com.ouresports.grimstroke.im.rbo.socket.LuxMessageRbo;

/**
 *
 * @author will
 * @date 2018/12/18
 */
public interface NotificationService {
    void sendNotification(LuxMessageRbo rbo);
}
