package com.ouresports.grimstroke.info.service.impl;

import com.ouresports.grimstroke.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.info.entity.LiveStream;
import com.ouresports.grimstroke.info.enums.ExternLivePlatform;
import com.ouresports.grimstroke.info.mapper.LiveStreamMapper;
import com.ouresports.grimstroke.info.service.LiveStreamService;
import com.ouresports.grimstroke.lib.aliyun.service.AliyunLiveStreamService;
import com.ouresports.grimstroke.lib.aliyun.service.AliyunVodService;
import com.ouresports.grimstroke.lib.streamlink.Douyu;
import com.ouresports.grimstroke.lib.streamlink.Huya;
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

    @Override
    public void createOuresportsLiveStream(LiveStream liveStream) {
        if (liveStream.getType() != Ouresports) {
            return;
        }
        save(liveStream);
    }

    @Override
    public void createExternLiveStream(LiveStream liveStream, ExternLivePlatform platform, String roomId) throws Exception {
        if (liveStream.getType() != Extern) {
            return;
        }
        String pullUrl;
        switch (platform) {
            case Douyu:
                pullUrl = new Douyu(roomId).getStreamLink();
                break;
            case Huya:
                pullUrl = new Huya(roomId).getStreamLink();
                break;
            case Twitch:
                break;
            default:
                return;
        }
    }

    @Override
    public String getPushUrl(LiveStream liveStream) {
        return aliyunLiveStreamService.generateStreamPushUrl(liveStream.getId().toString());
    }

    @Override
    public String getPlayUrl(LiveStream liveStream) {
        return aliyunLiveStreamService.generateStreamPlayUrl(liveStream.getId().toString());
    }
}
