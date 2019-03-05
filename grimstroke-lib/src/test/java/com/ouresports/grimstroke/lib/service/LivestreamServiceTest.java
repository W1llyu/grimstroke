package com.ouresports.grimstroke.lib.service;

import com.ouresports.grimstroke.lib.GrimstrokeLibApplicationTest;
import com.ouresports.grimstroke.lib.aliyun.service.AliyunLiveStreamService;
import com.ouresports.grimstroke.lib.exception.LibServiceException;
import com.ouresports.grimstroke.lib.livestream.entity.LivestreamSyncRbo;
import com.ouresports.grimstroke.lib.livestream.service.LivestreamService;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

import static com.ouresports.grimstroke.lib.livestream.enums.ExternLivePlatform.Douyu;

/**
 * Created by will on 2018/12/22.
 */
public class LivestreamServiceTest extends GrimstrokeLibApplicationTest {
    @Resource
    private LivestreamService livestreamService;
    @Resource
    private AliyunLiveStreamService aliyunLiveStreamService;

    public void testCreateLivestreamSync() throws LibServiceException {
        LivestreamSyncRbo rbo = new LivestreamSyncRbo().setId("1").setPlatform(Douyu).setRoomId("288016").setRtmp(aliyunLiveStreamService.generateStreamPushUrl("1"));
        livestreamService.createLivestreamSync(rbo);
    }

    @Test
    public void testDeleteLivestreamSync() throws LibServiceException {
        livestreamService.deleteLivestreamSync("1");
    }
}
