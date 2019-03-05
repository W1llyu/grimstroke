package com.ouresports.grimstroke.lib.service;

import com.ouresports.grimstroke.lib.GrimstrokeLibApplicationTest;
import com.ouresports.grimstroke.lib.aliyun.entity.VodDetailResponse;
import com.ouresports.grimstroke.lib.aliyun.entity.VodUploadInfo;
import com.ouresports.grimstroke.lib.aliyun.service.AliyunVodService;
import com.ouresports.grimstroke.lib.exception.LibServiceException;

import javax.annotation.Resource;

/**
 * Created by will on 2018/12/5.
 */
public class AliyunVodServiceTest extends GrimstrokeLibApplicationTest{
    @Resource
    private AliyunVodService aliyunVodService;

    public void testGetUploadVideoSts() throws LibServiceException {
        VodUploadInfo uploadVideo = new VodUploadInfo();
        uploadVideo.setFileName("test.mp4");
        uploadVideo.setTitle("test");
        aliyunVodService.getVideoUploadAuth(uploadVideo);
    }

    public void testGetVodDetail() throws LibServiceException {
        VodDetailResponse vodDetailResponse = aliyunVodService.getVideoDetail("aa80cb9ae57443c7b131ee15c94bcb2c");
    }
}
