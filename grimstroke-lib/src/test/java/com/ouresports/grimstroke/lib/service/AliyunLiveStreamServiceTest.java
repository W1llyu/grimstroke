package com.ouresports.grimstroke.lib.service;

import com.ouresports.grimstroke.lib.GrimstrokeLibApplicationTest;
import com.ouresports.grimstroke.lib.aliyun.entity.LiveStreamNotifyUrlResponse;
import com.ouresports.grimstroke.lib.aliyun.service.AliyunLiveStreamService;
import com.ouresports.grimstroke.lib.exception.LibServiceException;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by will on 2018/12/19.
 */
public class AliyunLiveStreamServiceTest extends GrimstrokeLibApplicationTest {
    @Resource
    private AliyunLiveStreamService aliyunLiveStreamService;

    public void testGenerateStreamPushUrl() {
        String url = aliyunLiveStreamService.generateStreamPushUrl("lolol");
        System.out.println(url);
    }

    public void testGenerateStreamPlayUrl() {
        String url = aliyunLiveStreamService.generateFlvStreamPlayUrl("lolol", "lsd");
        System.out.println(url);
    }

    @Test
    public void testGetLiveStreamNotifyUrl() throws LibServiceException {
        LiveStreamNotifyUrlResponse res = aliyunLiveStreamService.getLiveStreamNotifyUrl();
    }

    public void testSetLiveStreamNotifyUrl() throws LibServiceException {
        boolean result = aliyunLiveStreamService.setLiveStreamNotifyUrl();
        Assert.assertTrue(result);
    }

    public void testForbidLiveStream() throws LibServiceException {
        boolean result = aliyunLiveStreamService.forbidLiveStream("1");
        Assert.assertTrue(result);
    }

    public void resumeLiveStream() throws LibServiceException {
        boolean result = aliyunLiveStreamService.resumeLiveStream("1");
        Assert.assertTrue(result);
    }
}
