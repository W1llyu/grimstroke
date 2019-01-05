package com.ouresports.grimstroke.info.service.impl;

import com.ouresports.grimstroke.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.info.entity.LiveStream;
import com.ouresports.grimstroke.info.enums.StreamTemplate;
import com.ouresports.grimstroke.lib.livestream.entity.LivestreamSyncRbo;
import com.ouresports.grimstroke.lib.livestream.enums.ExternLivePlatform;
import com.ouresports.grimstroke.info.mapper.LiveStreamMapper;
import com.ouresports.grimstroke.info.service.LiveStreamService;
import com.ouresports.grimstroke.lib.aliyun.service.AliyunLiveStreamService;
import com.ouresports.grimstroke.lib.livestream.service.LivestreamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ouresports.grimstroke.info.enums.LiveStreamType.*;

/**
 *
 * @author will
 * @date 2018/12/19
 */
@Service
public class LiveStreamServiceImpl extends BaseServiceImpl<LiveStreamMapper, LiveStream> implements LiveStreamService {
    @Resource
    private AliyunLiveStreamService aliyunLiveStreamService;
    @Resource
    private LivestreamService livestreamService;

    @Override
    public void enactiveOfficialLiveStream(LiveStream liveStream, ExternLivePlatform platform, String roomId) throws Exception {
        if (liveStream.getType() != Official) {
            return;
        }
        LivestreamSyncRbo rbo = new LivestreamSyncRbo()
                .setId(liveStream.getId().toString())
                .setRoomId(roomId)
                .setPlatform(platform)
                .setRtmp(getPushUrl(liveStream));
        if (livestreamService.createLivestreamSync(rbo)) {
            liveStream.setActive(true);
            updateById(liveStream);
        }
    }

    @Override
    public String getPushUrl(LiveStream liveStream) {
        return aliyunLiveStreamService.generateStreamPushUrl(liveStream.getId().toString());
    }

    @Override
    public String getPlayUrl(LiveStream liveStream, StreamTemplate template) {
        return aliyunLiveStreamService.generateStreamPlayUrl(liveStream.getId().toString(), template.toString());
    }
}
