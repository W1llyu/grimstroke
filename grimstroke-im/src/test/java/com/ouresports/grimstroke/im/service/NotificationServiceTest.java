package com.ouresports.grimstroke.im.service;

import com.ouresports.grimstroke.im.GrimstrokeImApplicationTest;
import com.ouresports.grimstroke.im.rbo.socket.LuxMessageRbo;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by will on 2018/12/18.
 */
public class NotificationServiceTest extends GrimstrokeImApplicationTest {
    @Resource
    private NotificationService notificationService;

    @Test
    public void testNotify() {
        notificationService.sendNotification(new LuxMessageRbo().setChannel("test").setEvent("message").setData("aaa"));
    }
}
